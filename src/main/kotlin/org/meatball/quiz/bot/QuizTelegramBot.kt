package org.meatball.quiz.bot

import org.meatball.quiz.bot.state.TelegramBotState
import org.meatball.quiz.bot.state.handler.TelegramBotStateHandler
import org.meatball.quiz.bot.state.handler.dto.StateHandlerResponse
import org.meatball.quiz.bot.state.handler.impl.StateHandlersManager
import org.meatball.quiz.bot.token.getTelegramBotToken
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.ParseMode
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.io.File
import java.util.concurrent.ConcurrentHashMap


private val TG_BOT_TOKEN = getTelegramBotToken()

class QuizTelegramBot : TelegramLongPollingBot(TG_BOT_TOKEN) {

    private val userStateMap = ConcurrentHashMap<String, TelegramBotState>()

    init {
        logger.info("Telegram bot is available")
    }

    override fun getBotUsername(): String = "Boris Pupils Bot"

    override fun onUpdateReceived(update: Update) {
        val msg = update.message
        val userId = msg.from.id.toString()

        // Getting user state
        val userState = if (msg.isCommand) {
            TelegramBotState.RECEIVE_COMMAND
        } else {
            userStateMap.getOrDefault(userId, TelegramBotState.WAITING_FOR_QUESTION)
        }

        // Obtaining state handler
        val stateHandler = stateHandlerMap.getValue(userState)
        val responses = handleMessage(stateHandler, userId, msg)

        // Handling state handler response
        handleStateResponses(userId, responses)
    }

    private fun handleMessage(stateHandler: TelegramBotStateHandler, userId: String, msg: Message): List<StateHandlerResponse> {
        val response = stateHandler.handle(userId, msg)
        if (!sendNext(response)) {
            return listOf(response)
        }

        // Obtaining next state handler
        val nextStateHandler = stateHandlerMap.getValue(response.nextState)
        val nextResponse = nextStateHandler.handle(userId, msg)
        return listOf(response, nextResponse)
    }

    private fun handleStateResponses(userId: String, responses: Collection<StateHandlerResponse>) {
        // Memorizing user state
        userStateMap[userId] = responses.last().nextState

        // Sending text
        for (response in responses) {
            sendResponse(userId, response)
        }
    }

    private fun sendResponse(userId: String, response: StateHandlerResponse) {
        when {
            response.content?.image != null -> sendPhoto(userId, response.content.image, response.content.caption)
            !response.content?.text.isNullOrBlank() -> sendText(userId, response.content!!.text!!)
        }
    }

    private fun sendPhoto(userId: String, image: File, caption: String?) {
        val inputFile = InputFile(image)
        val sendPhotoRequest = SendPhoto.builder()
            .chatId(userId)
            .photo(inputFile)
            .caption(caption)
            .replyMarkup(constructKeyboard())
            .build()

        try {
            execute(sendPhotoRequest)
        } catch (ex: TelegramApiException) {
            println(ex.message)
            throw RuntimeException(ex)
        }
    }

    private fun sendText(userId: String, text: String) {
        println(text)
        val smBuilder = SendMessage.builder()
            .chatId(userId)
            .text(text)
            .parseMode(ParseMode.MARKDOWNV2)

        val sm = smBuilder.build()

        try {
            execute(sm)
        } catch (ex: TelegramApiException) {
            println(ex.message)
            throw RuntimeException(ex)
        }
    }

    private fun sendNext(response: StateHandlerResponse): Boolean {
        return response.nextState === TelegramBotState.WAITING_FOR_QUESTION
    }

    private fun constructKeyboard(): ReplyKeyboard {
        return ReplyKeyboardMarkup.builder()
            .resizeKeyboard(true)
            .isPersistent(true)
            .keyboardRow(KeyboardRow(listOf(constructNextFlagButton())))
            .build()
    }

    private fun constructNextFlagButton(): KeyboardButton {
        return KeyboardButton.builder()
            .text("Ответ")
            .build()
    }

    private companion object {
        // State handlers
        private val stateHandlerMap = StateHandlersManager().stateHandlers.associateBy { it.state }

        private val logger: Logger = LoggerFactory.getLogger(QuizTelegramBot::class.java)
    }
}