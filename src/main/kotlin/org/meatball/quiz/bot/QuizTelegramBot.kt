package org.meatball.quiz.bot

import org.meatball.quiz.bot.commons.dto.SendMessageOrPhoto
import org.meatball.quiz.bot.config.tgtoken.getTelegramBotToken
import org.meatball.quiz.bot.commons.update.impl.DefaultOnUpdateReceivedHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException


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
                    msg.editText != null -> execute(msg.editText)
                    msg.editCaption != null -> execute(msg.editCaption)
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