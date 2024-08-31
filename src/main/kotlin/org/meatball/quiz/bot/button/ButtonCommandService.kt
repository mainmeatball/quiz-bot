package org.meatball.quiz.bot.button

import org.meatball.quiz.bot.answer.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

interface ButtonCommandService {

    fun suitableFor(cbQuery: CallbackQuery): Boolean

    fun getResponse(cbQuery: CallbackQuery): SendMessageResponse

    fun getButton(vararg params: Any): InlineKeyboardButton
}