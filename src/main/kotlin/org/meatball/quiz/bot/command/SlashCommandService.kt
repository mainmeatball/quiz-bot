package org.meatball.quiz.bot.command

import org.meatball.quiz.bot.answer.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

interface SlashCommandService {

    fun suitableFor(msg: Message): Boolean

    fun getResponse(msg: Message): SendMessageResponse

    fun getButton(vararg params: Any): InlineKeyboardButton
}