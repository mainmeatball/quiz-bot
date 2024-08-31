package org.meatball.quiz.bot.state.handler

import org.meatball.quiz.bot.state.TelegramBotState
import org.meatball.quiz.bot.state.handler.dto.StateHandlerResponse
import org.telegram.telegrambots.meta.api.objects.Message

interface TelegramBotStateHandler {

    val state: TelegramBotState

    fun handle(userId: String): StateHandlerResponse
}