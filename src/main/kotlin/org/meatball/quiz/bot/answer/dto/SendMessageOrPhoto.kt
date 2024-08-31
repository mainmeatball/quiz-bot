package org.meatball.quiz.bot.answer.dto

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto

data class SendMessageOrPhoto(
    val sendMessage: SendMessage?,
    val sendPhoto: SendPhoto?,
) {

    companion object {
        fun photo(sendPhoto: SendPhoto) = SendMessageOrPhoto(null, sendPhoto)

        fun message(sendMessage: SendMessage) = SendMessageOrPhoto(sendMessage, null)
    }
}