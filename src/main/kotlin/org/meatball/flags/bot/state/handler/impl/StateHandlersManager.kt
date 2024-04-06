package org.meatball.flags.bot.state.handler.impl

class StateHandlersManager {

    val stateHandlers = listOf(
        WaitingForQuestionStateHandler(),
        WaitingForAnswerStateHandler()
    )
}