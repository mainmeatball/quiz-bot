package org.meatball.quiz.bot.categories.chemistry.periodictable.button.impl

import org.meatball.quiz.bot.categories.chemistry.periodictable.enums.PeriodicTableMode
import org.meatball.quiz.bot.commons.button.CommandService
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.periodicTableService
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

class PeriodicTableModeChosenHandler : CommandService {

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data in PeriodicTableMode.map
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val mode = PeriodicTableMode.map.getValue(cbQuery.data)

        periodicTableService.updateConfig(cbQuery.from.id.toString(), mode)

        val firstMessage = SendMessageComponents(
            text = "Будет отображаться таблица Менделеева: ${mode.l10n}",
            messageId = cbQuery.message.messageId
        )
        val components = listOf(firstMessage) + mode.service.getResponse(cbQuery).msgList
        return SendMessageResponse(components)
    }
}