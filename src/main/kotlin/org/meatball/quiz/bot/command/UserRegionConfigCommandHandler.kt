package org.meatball.quiz.bot.command

import org.meatball.quiz.bot.state.TelegramBotState
import org.meatball.quiz.bot.state.handler.TelegramBotStateHandler
import org.meatball.quiz.bot.state.handler.dto.Content
import org.meatball.quiz.bot.state.handler.dto.StateHandlerResponse
import org.meatball.quiz.core.enums.Region
import org.meatball.quiz.crm.user.service.updateUserRegionConfig
import org.telegram.telegrambots.meta.api.objects.Message

class UserRegionConfigCommandHandler : TelegramBotStateHandler {

    override val state: TelegramBotState = TelegramBotState.RECEIVE_COMMAND

    override fun handle(userId: String, msg: Message): StateHandlerResponse {
        val region = Region.smartValueOf(msg.text.drop(1)) ?: return StateHandlerResponse(
            content = Content(text = "Некорректная команда \"${msg.text}\". Список доступных команд: ${Region.entries.joinToString { "/${it.humanName}" }}"),
            nextState = TelegramBotState.WAITING_FOR_QUESTION
        )
        updateUserRegionConfig(userId, region)
        return StateHandlerResponse(
            content = Content(text = "Будут отображаться флаги региона: ${region.l10n}"),
            nextState = TelegramBotState.WAITING_FOR_QUESTION
        )
    }
}