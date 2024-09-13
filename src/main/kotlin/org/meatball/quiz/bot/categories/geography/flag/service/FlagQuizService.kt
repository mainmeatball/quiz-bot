package org.meatball.quiz.bot.categories.geography.flag.service

import org.meatball.quiz.bot.categories.geography.flag.enums.FlagButtonCommand
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.singletone.countryService
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

fun getLastFlagAnswer(userId: String): SendMessageComponents {
    val lastCountryInfo = countryService.getCurrent(userId)
    return SendMessageComponents(
        text = lastCountryInfo.name,
        photo = lastCountryInfo.geo,
    )
}

fun getNextFlagQuestion(userId: String): SendMessageComponents {
    val nextCountryInfo = countryService.getNext(userId)
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