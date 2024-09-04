package org.meatball.quiz.capital.button.impl

import org.meatball.quiz.bot.answer.dto.SendMessageComponents
import org.meatball.quiz.bot.answer.dto.SendMessageResponse
import org.meatball.quiz.bot.button.ButtonCommandService
import org.meatball.quiz.capital.enums.CapitalModeButtonCommand
import org.meatball.quiz.flag.service.updateUserRegionConfig
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class CapitalModeButtonCommandHandler : ButtonCommandService {

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data in CapitalModeButtonCommand.map
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        return getCapitalModeResponse(cbQuery)
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        throw UnsupportedOperationException()
    }

    private fun getCapitalModeResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val capitalMode = CapitalModeButtonCommand.map.getValue(cbQuery.data)

        updateUserRegionConfig(cbQuery.from.id.toString(), capitalMode.region)

        val firstMessage = SendMessageComponents(
            text = "Будут отображаться столицы региона: ${capitalMode.region.l10n}",
            messageId = cbQuery.message.messageId
        )
        val components = listOf(firstMessage) + capitalMode.service.getResponse(cbQuery).msgList
        return SendMessageResponse(components)
    }
}