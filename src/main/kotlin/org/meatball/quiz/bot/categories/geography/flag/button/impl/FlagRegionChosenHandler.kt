package org.meatball.quiz.bot.categories.geography.flag.button.impl

import org.meatball.quiz.bot.categories.geography.flag.enums.FlagRegionButtonCommand
import org.meatball.quiz.bot.commons.button.CommandService
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.countryService
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

class FlagRegionChosenHandler : CommandService {

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data in FlagRegionButtonCommand.map
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        return getFlagsModeResponse(cbQuery)
    }

    private fun getFlagsModeResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val flagMode = FlagRegionButtonCommand.map.getValue(cbQuery.data)

        countryService.updateMode(cbQuery.from.id.toString(), flagMode.region)

        val firstMessage = SendMessageComponents(
            text = "Будут отображаться флаги региона: ${flagMode.region.l10n}",
            messageId = cbQuery.message.messageId
        )
        val components = listOf(firstMessage) + flagMode.service.getResponse(cbQuery).msgList
        return SendMessageResponse(components)
    }
}