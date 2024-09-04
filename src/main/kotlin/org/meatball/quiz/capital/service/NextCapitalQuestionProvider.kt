package org.meatball.quiz.capital.service

import org.meatball.quiz.bot.answer.dto.SendMessageComponents
import org.meatball.quiz.bot.enums.ButtonCommand
import org.meatball.quiz.bot.singletone.countryService
import org.meatball.quiz.core.service.updateUserCountry
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

class NextCapitalQuestionProvider {

    fun getNextCapitalQuestion(userId: String): SendMessageComponents {
        val nextCountry = countryService.getNextCountryInfo(userId)
        updateUserCountry(userId, nextCountry)
        return SendMessageComponents(
            text = nextCountry.capital,
            keyboard = getShowAnswerKeyboard()
        )
    }

    private fun getShowAnswerKeyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf(ButtonCommand.SHOW_CAPITAL_ANSWER.service.getButton()))
    }

}