package org.meatball.quiz.country.button.impl

import org.meatball.quiz.bot.answer.dto.SendMessageComponents
import org.meatball.quiz.bot.answer.dto.SendMessageResponse
import org.meatball.quiz.bot.button.ButtonCommandService
import org.meatball.quiz.country.enums.CountryModeButtonCommand
import org.meatball.quiz.flag.service.updateUserRegionConfig
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class CountryModeButtonCommandHandler : ButtonCommandService {

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data in CountryModeButtonCommand.map
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        return getCountryModeResponse(cbQuery)
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        throw UnsupportedOperationException()
    }

    private fun getCountryModeResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val countryMode = CountryModeButtonCommand.map.getValue(cbQuery.data)

        updateUserRegionConfig(cbQuery.from.id.toString(), countryMode.region)

        val firstMessage = SendMessageComponents(
            text = "Будут отображаться страны региона: ${countryMode.region.l10n}",
            messageId = cbQuery.message.messageId
        )
        val components = listOf(firstMessage) + countryMode.service.getResponse(cbQuery).msgList
        return SendMessageResponse(components)
    }
}