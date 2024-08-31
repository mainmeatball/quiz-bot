package org.meatball.quiz.bot.state.handler.impl

import org.meatball.quiz.bot.state.TelegramBotState
import org.meatball.quiz.bot.state.handler.TelegramBotStateHandler
import org.meatball.quiz.bot.state.handler.dto.Content
import org.meatball.quiz.bot.state.handler.dto.StateHandlerResponse
import org.meatball.quiz.crm.user.service.getLastUserFlag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.telegram.telegrambots.meta.api.objects.Message

class WaitingForAnswerStateHandler : TelegramBotStateHandler {

    override val state = TelegramBotState.WAITING_FOR_ANSWER

    override fun handle(userId: String): StateHandlerResponse {
        val flag = getLastUserFlag(userId)
        return StateHandlerResponse(
            content = Content(
                text = flag?.name,
                image = flag?.geo,
                caption = flag?.name
            ),
//            nextState = TelegramBotState.WAITING_FOR_QUESTION
        )
    }

    private companion object {
        private val logger: Logger = LoggerFactory.getLogger(WaitingForAnswerStateHandler::class.java)
    }
}