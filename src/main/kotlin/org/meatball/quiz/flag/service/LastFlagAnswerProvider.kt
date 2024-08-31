package org.meatball.quiz.flag.service

import org.meatball.quiz.bot.answer.dto.SendMessageComponents
import org.meatball.quiz.bot.enums.ButtonCommand
import org.meatball.quiz.bot.singletone.flagService
import org.meatball.quiz.bot.state.handler.dto.Content
import org.meatball.quiz.bot.state.handler.dto.StateHandlerResponse
import org.meatball.quiz.crm.user.service.getLastUserFlag
import org.meatball.quiz.crm.user.service.updateUserFlag
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder
import java.awt.SystemColor.text

class LastFlagAnswerProvider {

    fun getLastFlagAnswer(userId: String): SendMessageComponents {
        val lastFlag = getLastUserFlag(userId)
        return SendMessageComponents(
            text = lastFlag?.name,
            photo = lastFlag?.geo,
        )
    }
}