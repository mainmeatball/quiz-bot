package org.meatball.flags.bot.state.handler.impl

import org.meatball.flags.bot.state.TelegramBotState
import org.meatball.flags.bot.state.handler.TelegramBotStateHandler
import org.meatball.flags.bot.state.handler.dto.Content
import org.meatball.flags.bot.state.handler.dto.StateHandlerResponse
import org.meatball.flags.bot.user.getLastUserFlag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.telegram.telegrambots.meta.api.objects.Message

class WaitingForAnswerStateHandler : TelegramBotStateHandler {

    override val state = TelegramBotState.WAITING_FOR_ANSWER

    override fun handle(userId: String, msg: Message): StateHandlerResponse {
        return StateHandlerResponse(
            content = Content(
                text = getLastUserFlag(userId)
            ),
            nextState = TelegramBotState.WAITING_FOR_QUESTION
        )
    }

    private companion object {
        private val logger: Logger = LoggerFactory.getLogger(WaitingForAnswerStateHandler::class.java)
    }
}