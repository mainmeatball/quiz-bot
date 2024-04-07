package org.meatball.flags.bot.state.handler.impl

import org.meatball.flags.bot.state.TelegramBotState
import org.meatball.flags.bot.state.handler.TelegramBotStateHandler
import org.meatball.flags.bot.state.handler.dto.Content
import org.meatball.flags.bot.state.handler.dto.StateHandlerResponse
import org.meatball.flags.bot.user.updateUserFlag
import org.meatball.flags.core.service.FlagService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.telegram.telegrambots.meta.api.objects.Message

class WaitingForQuestionStateHandler : TelegramBotStateHandler {

    private val flagService = FlagService()

    override val state = TelegramBotState.WAITING_FOR_QUESTION

    override fun handle(userId: String, msg: Message): StateHandlerResponse {
        val flag = flagService.getNextFlag(userId)
        updateUserFlag(userId, flag.name)
        return StateHandlerResponse(
            content = Content(
                text = flag.name,
                image = flag.image
            ),
            nextState = TelegramBotState.WAITING_FOR_ANSWER
        )
    }

    private companion object {
        private val logger: Logger = LoggerFactory.getLogger(WaitingForQuestionStateHandler::class.java)
    }
}