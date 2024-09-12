package org.meatball.quiz.bot.categories.geography.flag.button.impl

import org.meatball.quiz.bot.categories.geography.flag.enums.FlagRegionButtonCommand
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.countryService
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class FlagRegionChosenButtonCommandHandler : ButtonCommandService {

    override val enum get() = null
    override val buttonText get() = null

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data in FlagRegionButtonCommand.map
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        return getFlagsModeResponse(cbQuery)
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        throw UnsupportedOperationException()
    }

    private fun getFlagsModeResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val flagMode = FlagRegionButtonCommand.map.getValue(cbQuery.data)

        countryService.updateRegion(cbQuery.from.id.toString(), flagMode.region)

        val firstMessage = SendMessageComponents(
            text = "Будут отображаться флаги региона: ${flagMode.region.l10n}",
            messageId = cbQuery.message.messageId
        )
        val components = listOf(firstMessage) + flagMode.service.getResponse(cbQuery).msgList
        return SendMessageResponse(components)
    }
}