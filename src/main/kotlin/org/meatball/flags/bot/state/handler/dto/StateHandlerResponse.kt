package org.meatball.flags.bot.state.handler.dto

import org.meatball.flags.bot.state.TelegramBotState
import java.io.File

data class Content(val text: String? = null, val image: File? = null)

data class StateHandlerResponse(val content: Content?, val nextState: TelegramBotState)