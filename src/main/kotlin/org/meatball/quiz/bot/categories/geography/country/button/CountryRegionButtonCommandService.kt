package org.meatball.quiz.bot.categories.geography.country.button

import org.meatball.quiz.bot.categories.geography.country.enums.CountryRegionButtonCommand
import org.meatball.quiz.bot.categories.geography.country.service.getNextCountryQuestion
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

abstract class CountryRegionButtonCommandService : ButtonCommandService {

    override val enum get() = countryRegionButtonCommand
    override val buttonText get() = countryRegionButtonCommand.region.l10n

    abstract val countryRegionButtonCommand: CountryRegionButtonCommand

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        // Get user state
        val nextFlagQuestion = getNextCountryQuestion(userId)
        return SendMessageResponse.single(nextFlagQuestion)
    }
}