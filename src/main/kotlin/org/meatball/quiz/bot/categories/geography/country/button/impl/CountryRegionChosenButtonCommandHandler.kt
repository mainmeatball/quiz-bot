package org.meatball.quiz.bot.categories.geography.country.button.impl

import org.meatball.quiz.bot.categories.geography.country.enums.CountryRegionButtonCommand
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.countryService
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class CountryRegionChosenButtonCommandHandler : ButtonCommandService {

    override val enum get() = null
    override val buttonText get() = null

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data in CountryRegionButtonCommand.map
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        return getCountryModeResponse(cbQuery)
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        throw UnsupportedOperationException()
    }

    private fun getCountryModeResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val countryMode = CountryRegionButtonCommand.map.getValue(cbQuery.data)

        countryService.updateRegion(cbQuery.from.id.toString(), countryMode.region)

        val firstMessage = SendMessageComponents(
            text = "Будут отображаться страны региона: ${countryMode.region.l10n}",
            messageId = cbQuery.message.messageId
        )
        val components = listOf(firstMessage) + countryMode.service.getResponse(cbQuery).msgList
        return SendMessageResponse(components)
    }
}