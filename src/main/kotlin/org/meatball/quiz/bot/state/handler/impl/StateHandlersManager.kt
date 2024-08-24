package org.meatball.quiz.bot.state.handler.impl

import org.meatball.quiz.bot.command.UserRegionConfigCommandHandler

class StateHandlersManager {

    val stateHandlers = listOf(
        WaitingForQuestionStateHandler(),
        WaitingForAnswerStateHandler(),
        UserRegionConfigCommandHandler()
    )
}