package org.meatball.quiz.bot.keyboard

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class KeyboardButtonFactory {

    fun button(text: String, cb: String, inline: Boolean = false): InlineKeyboardButton {
        val builder = InlineKeyboardButton.builder()
            .text(text)
        when {
            inline -> builder.switchInlineQueryCurrentChat(cb)
            else -> builder.callbackData(cb)
        }
        return builder.build()
    }

    fun row(text: String, cb: String, inline: Boolean = false): List<InlineKeyboardButton> {
        return listOf(button(text, cb, inline))
    }
}