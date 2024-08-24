package org.meatball.quiz.bot.state.handler.dto

import org.meatball.quiz.bot.state.TelegramBotState
import java.io.File

data class Content(val text: String? = null, val image: File? = null, val caption: String? = null)

data class StateHandlerResponse(val content: Content?, val nextState: TelegramBotState)