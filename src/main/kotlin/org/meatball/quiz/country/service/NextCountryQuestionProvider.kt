package org.meatball.quiz.country.service

import org.meatball.quiz.bot.answer.dto.SendMessageComponents
import org.meatball.quiz.bot.enums.ButtonCommand
import org.meatball.quiz.bot.singletone.countryService
import org.meatball.quiz.core.service.updateUserCountry
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
            .keyboardRow(listOf(ButtonCommand.SHOW_COUNTRY_ANSWER.service.getButton()))
    }

}