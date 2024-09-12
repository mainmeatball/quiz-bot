package org.meatball.quiz.bot.categories.geography.country.service

import org.meatball.quiz.bot.categories.geography.core.state.updateUserCountry
import org.meatball.quiz.bot.categories.geography.country.enums.CountryButtonCommand
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.singletone.countryService
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

class NextCountryQuestionProvider {

    fun getNextCountryQuestion(userId: String): SendMessageComponents {
        val nextCountry = countryService.getNextCountryInfo(userId)
        updateUserCountry(userId, nextCountry)
        return SendMessageComponents(
            text = null,
            photo = nextCountry.geo,
            keyboard = getShowAnswerKeyboard()
        )
    }

    private fun getShowAnswerKeyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf(CountryButtonCommand.SHOW_ANSWER.service.getButton()))
    }

}