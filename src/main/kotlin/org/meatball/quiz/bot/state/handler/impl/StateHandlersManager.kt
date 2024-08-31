package org.meatball.quiz.bot.state.handler.impl

import org.meatball.quiz.bot.state.TelegramBotState
import org.meatball.quiz.bot.state.handler.TelegramBotStateHandler
import java.util.concurrent.ConcurrentHashMap

class StateHandlersManager {

//    private val userStateMap = ConcurrentHashMap<String, TelegramBotState>()
//
//    fun getUserState(userId: String): TelegramBotState {
//        return userStateMap[userId] ?: TelegramBotState.WAITING_FOR_QUESTION
//    }
//
//    fun updateUserState(userId: String, newState: TelegramBotState) {
//        userStateMap[userId] = newState
//    }
//
//    fun getStateHandler(state: TelegramBotState): TelegramBotStateHandler {
//        return stateHandlers.getValue(state)
//    }
//
//    private companion object {
//        private val stateHandlers = listOf(
//            WaitingForQuestionStateHandler(),
//            WaitingForAnswerStateHandler(),
//        ).associateBy { it.state }
//    }
}