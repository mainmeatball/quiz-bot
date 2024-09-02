package org.meatball.quiz.flag.service

import org.meatball.quiz.bot.answer.dto.SendMessageComponents
import org.meatball.quiz.bot.enums.ButtonCommand
import org.meatball.quiz.bot.singletone.countryService
import org.meatball.quiz.core.service.updateUserCountry
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
            .keyboardRow(listOf(ButtonCommand.SHOW_FLAG_ANSWER.service.getButton()))
    }

}