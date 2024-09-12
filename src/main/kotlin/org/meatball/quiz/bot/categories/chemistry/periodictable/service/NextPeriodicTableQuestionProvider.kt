package org.meatball.quiz.bot.categories.chemistry.periodictable.service

import org.meatball.quiz.bot.categories.geography.capital.enums.CapitalButtonCommand
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.singletone.countryService
import org.meatball.quiz.bot.categories.geography.core.state.updateUserCountry
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

class NextPeriodicTableQuestionProvider {

    fun getNextPeriodicTableQuestion(userId: String): SendMessageComponents {
        val nextCountry = countryService.getNextCountryInfo(userId)
        updateUserCountry(userId, nextCountry)
        return SendMessageComponents(
            text = nextCountry.capital,
            keyboard = getShowAnswerKeyboard()
        )
    }

    private fun getShowAnswerKeyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf(CapitalButtonCommand.SHOW_ANSWER.service.getButton()))
    }

}