package org.meatball.quiz.bot.commons.dto

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import java.io.File

data class SendMessageResponse(
    val msgList: List<SendMessageComponents>
) {

    operator fun plus(other: SendMessageResponse): SendMessageResponse {
        return SendMessageResponse(this.msgList + other.msgList)
    }

    companion object {
        fun single(msg: SendMessageComponents) = SendMessageResponse(listOf(msg))

        fun single(text: String?, photo: File?, keyboard: InlineKeyboardMarkup.InlineKeyboardMarkupBuilder? = null) = SendMessageResponse(
            listOf(SendMessageComponents(text, null, photo, keyboard))
        )
    }
}