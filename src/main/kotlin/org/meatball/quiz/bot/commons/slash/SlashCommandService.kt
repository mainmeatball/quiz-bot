package org.meatball.quiz.bot.commons.slash

import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

interface SlashCommandService {

    fun suitableFor(msg: Message): Boolean

    fun getButton(vararg params: Any): InlineKeyboardButton

    fun getResponse(msg: Message): SendMessageResponse
}