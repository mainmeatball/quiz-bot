package org.meatball.quiz.bot.commons.dto

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import java.io.File

data class SendMessageComponents(
    val text: String?,
    val photo: File? = null,
    val keyboard: InlineKeyboardMarkup.InlineKeyboardMarkupBuilder? = null,
    val messageId: Int? = null
)