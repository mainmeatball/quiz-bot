package org.meatball.quiz.country.button

import org.meatball.quiz.bot.answer.dto.SendMessageResponse
import org.meatball.quiz.bot.button.ButtonCommandService
import org.meatball.quiz.bot.singletone.keyboardButtonFactory
import org.meatball.quiz.bot.singletone.nextCountryQuestionProvider
import org.meatball.quiz.bot.singletone.nextFlagQuestionProvider
import org.meatball.quiz.country.enums.CountryModeButtonCommand
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

interface CountryModeButtonCommandService : ButtonCommandService {

    val countryModeButtonCommand: CountryModeButtonCommand

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data == countryModeButtonCommand.key
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        // Get user state
        val nextFlagQuestion = nextCountryQuestionProvider.getNextCountryQuestion(userId)
        return SendMessageResponse.single(nextFlagQuestion)
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        return keyboardButtonFactory.button(countryModeButtonCommand.region.l10n, countryModeButtonCommand.key)
    }
}