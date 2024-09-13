package org.meatball.quiz.bot.categories.geography.country.service

import org.meatball.quiz.bot.categories.geography.country.enums.CountryButtonCommand
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.singletone.countryService
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

fun getLastCountryAnswer(userId: String): SendMessageComponents {
    val lastCountry = countryService.getCurrent(userId)
    return SendMessageComponents(
        caption = lastCountry.name,
    )
}

fun getNextCountryQuestion(userId: String): SendMessageComponents {
    val nextCountry = countryService.getNext(userId)
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