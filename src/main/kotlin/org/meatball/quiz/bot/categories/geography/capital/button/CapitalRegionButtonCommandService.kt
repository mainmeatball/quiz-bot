package org.meatball.quiz.bot.categories.geography.capital.button

import org.meatball.quiz.bot.categories.geography.capital.enums.CapitalRegionButtonCommand
import org.meatball.quiz.bot.categories.geography.capital.service.getNextCapitalQuestion
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

abstract class CapitalRegionButtonCommandService : ButtonCommandService {

    override val enum get() = capitalRegionButtonCommand
    override val buttonText get() = capitalRegionButtonCommand.region.l10n

    abstract val capitalRegionButtonCommand: CapitalRegionButtonCommand

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        // Get user state
        val nextCapitalQuestion = getNextCapitalQuestion(userId)
        return SendMessageResponse.single(nextCapitalQuestion)
    }
}