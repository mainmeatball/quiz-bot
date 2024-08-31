package org.meatball.quiz.bot

import org.meatball.quiz.bot.answer.dto.SendMessageOrPhoto
import org.meatball.quiz.bot.update.impl.DefaultOnUpdateReceivedHandler
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

    init {
        logger.info("Telegram bot is available")
    }

    override fun getBotUsername(): String = "Boris Pupils Quiz Bot"

    override fun onUpdateReceived(update: Update) {
        val sendMessage = getResponse(update)
        val msgIterator = sendMessage.listIterator()
        while (msgIterator.hasNext()) {
            val msg = msgIterator.next()
            try {
                when {
                    msg.sendPhoto != null -> execute(msg.sendPhoto)
                    msg.sendMessage != null -> execute(msg.sendMessage)
                }
            } catch (ex: TelegramApiException) {
                println(ex.message)
                throw RuntimeException(ex)
            }
        }
    }

    private fun getResponse(update: Update): List<SendMessageOrPhoto> {
        return defaultOnUpdateReceivedHandler.handle(update)
    }

    private companion object {
        private val defaultOnUpdateReceivedHandler = DefaultOnUpdateReceivedHandler()

        private val logger: Logger = LoggerFactory.getLogger(QuizTelegramBot::class.java)
    }
}