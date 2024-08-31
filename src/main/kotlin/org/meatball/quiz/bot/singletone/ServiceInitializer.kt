package org.meatball.quiz.bot.singletone

import org.meatball.quiz.bot.keyboard.KeyboardButtonFactory
import org.meatball.quiz.bot.state.handler.impl.StateHandlersManager
import org.meatball.quiz.core.service.FlagService
import org.meatball.quiz.flag.service.LastFlagAnswerProvider
import org.meatball.quiz.flag.service.NextFlagQuestionProvider

// Services
val keyboardButtonFactory = KeyboardButtonFactory()
val stateHandlersManager = StateHandlersManager()

// Flag Services
val flagService = FlagService()
val nextFlagQuestionProvider = NextFlagQuestionProvider()
val lastFlagAnswerProvider = LastFlagAnswerProvider()