package org.meatball.quiz.bot.categories.geography.capital.button.impl

import org.meatball.quiz.bot.categories.geography.capital.enums.CapitalRegionButtonCommand
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.countryService
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class CapitalRegionChosenHandler : ButtonCommandService {

    override val enum get() = null
    override val buttonText get() = null

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data in CapitalRegionButtonCommand.map
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        return getCapitalModeResponse(cbQuery)
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        throw UnsupportedOperationException()
    }

    private fun getCapitalModeResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val capitalMode = CapitalRegionButtonCommand.map.getValue(cbQuery.data)

        countryService.updateRegion(cbQuery.from.id.toString(), capitalMode.region)

        val firstMessage = SendMessageComponents(
            text = "Будут отображаться столицы региона: ${capitalMode.region.l10n}",
            messageId = cbQuery.message.messageId
        )
        val components = listOf(firstMessage) + capitalMode.service.getResponse(cbQuery).msgList
        return SendMessageResponse(components)
    }
}