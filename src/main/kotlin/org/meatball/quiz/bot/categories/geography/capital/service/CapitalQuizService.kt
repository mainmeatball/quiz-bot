package org.meatball.quiz.bot.categories.geography.capital.service

import org.meatball.quiz.bot.categories.geography.capital.enums.CapitalButtonCommand
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.singletone.countryService
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

fun getLastCapitalAnswer(userId: String): SendMessageComponents {
    val lastCountry = countryService.getCurrentCountry(userId)
    return SendMessageComponents(
        text = lastCountry.name,
    )
}

fun getNextCapitalQuestion(userId: String): SendMessageComponents {
    val nextCountry = countryService.getNextCountry(userId)
    return SendMessageComponents(
        text = nextCountry.capital,
        keyboard = getShowAnswerKeyboard()
    )
}

private fun getShowAnswerKeyboard(): InlineKeyboardMarkupBuilder {
    return InlineKeyboardMarkup.builder()
        .keyboardRow(listOf(CapitalButtonCommand.SHOW_ANSWER.service.getButton()))
}