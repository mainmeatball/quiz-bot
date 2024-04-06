package org.meatball.flags.bot.state.handler

import org.meatball.flags.bot.state.TelegramBotState
import org.meatball.flags.bot.state.handler.dto.StateHandlerResponse
import org.telegram.telegrambots.meta.api.objects.Message

interface TelegramBotStateHandler {

    val state: TelegramBotState

    fun handle(userId: String, msg: Message): StateHandlerResponse
}