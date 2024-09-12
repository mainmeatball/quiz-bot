package org.meatball.quiz.bot.categories.geography.country.button

import org.meatball.quiz.bot.categories.geography.country.enums.CountryRegionButtonCommand
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.keyboardButtonFactory
import org.meatball.quiz.bot.commons.singletone.nextCountryQuestionProvider
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

abstract class CountryRegionButtonCommandService : ButtonCommandService {

    override val enum get() = null
    override val buttonText get() = null

    abstract val countryRegionButtonCommand: CountryRegionButtonCommand

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data == countryRegionButtonCommand.key
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        // Get user state
        val nextFlagQuestion = nextCountryQuestionProvider.getNextCountryQuestion(userId)
        return SendMessageResponse.single(nextFlagQuestion)
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        return keyboardButtonFactory.button(countryRegionButtonCommand.region.l10n, countryRegionButtonCommand.key)
    }
}