package org.meatball.quiz.bot.commons.button

import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.keyboardButtonFactory
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

interface ButtonCommandService : CommandService {

    val enum: ButtonCommand
    val buttonText: String

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return enum.key == cbQuery.data
    }

    fun getButton(vararg params: Any): InlineKeyboardButton {
        return keyboardButtonFactory.button(buttonText, enum.key)
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse
}

interface CommandService {

    fun suitableFor(cbQuery: CallbackQuery): Boolean

    fun getResponse(cbQuery: CallbackQuery): SendMessageResponse
}

interface ButtonAwareService