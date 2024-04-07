package org.meatball.flags.bot.state.handler.impl

import org.meatball.flags.bot.command.UserRegionConfigCommandHandler

class StateHandlersManager {

    val stateHandlers = listOf(
        WaitingForQuestionStateHandler(),
        WaitingForAnswerStateHandler(),
        UserRegionConfigCommandHandler()
    )
}