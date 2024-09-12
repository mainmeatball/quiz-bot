package org.meatball.quiz.bot.categories.geography.flag.service

import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.singletone.countryService
import org.meatball.quiz.bot.categories.geography.core.state.updateUserCountry
import org.meatball.quiz.bot.categories.geography.flag.enums.FlagButtonCommand
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

class NextFlagQuestionProvider {

    fun getNextFlagQuestion(userId: String): SendMessageComponents {
        val nextCountryInfo = countryService.getNextCountryInfo(userId)
        updateUserCountry(userId, nextCountryInfo)
        return SendMessageComponents(
            text = null,
            photo = nextCountryInfo.flag,
            keyboard = getShowAnswerKeyboard()
        )
    }

    private fun getShowAnswerKeyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf(FlagButtonCommand.SHOW_ANSWER.service.getButton()))
    }

}