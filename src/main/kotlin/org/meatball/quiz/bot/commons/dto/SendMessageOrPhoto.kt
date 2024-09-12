package org.meatball.quiz.bot.commons.dto

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText

data class SendMessageOrPhoto(
    val sendMessage: SendMessage? = null,
    val sendPhoto: SendPhoto? = null,
    val editText: EditMessageText? = null,
    val editCaption: EditMessageCaption? = null
) {

    companion object {
        fun photo(sendPhoto: SendPhoto) = SendMessageOrPhoto(sendPhoto = sendPhoto)

        fun message(sendMessage: SendMessage) = SendMessageOrPhoto(sendMessage = sendMessage)

        fun editText(editText: EditMessageText) = SendMessageOrPhoto(editText = editText)

        fun editCaption(editCaption: EditMessageCaption) = SendMessageOrPhoto(editCaption = editCaption)
    }
}