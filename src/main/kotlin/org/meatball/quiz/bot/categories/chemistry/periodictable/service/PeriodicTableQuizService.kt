package org.meatball.quiz.bot.categories.chemistry.periodictable.service

import org.meatball.quiz.bot.categories.chemistry.periodictable.enums.PeriodicTableButtonCommand
import org.meatball.quiz.bot.categories.chemistry.periodictable.enums.PeriodicTableMode
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.singletone.periodicTableService
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

fun getLastPeriodicTableAnswer(userId: String): SendMessageComponents {
    val lastElement = periodicTableService.getCurrent(userId)
    return SendMessageComponents(
        text = lastElement.l10n,
    )
}

fun getNextPeriodicTableQuestion(userId: String, periodicTableMode: PeriodicTableMode): SendMessageComponents {
    val nextElement = periodicTableService.getNext(userId)
    val text = when (periodicTableMode) {
        PeriodicTableMode.BY_SYMBOL -> nextElement.symbol
        PeriodicTableMode.BY_ORDINAL -> nextElement.number.toString()
        PeriodicTableMode.BY_NAME -> nextElement.ruName
    }
    return SendMessageComponents(
        text = text,
        keyboard = getShowAnswerKeyboard()
    )
}

private fun getShowAnswerKeyboard(): InlineKeyboardMarkupBuilder {
    return InlineKeyboardMarkup.builder()
        .keyboardRow(listOf(PeriodicTableButtonCommand.SHOW_ANSWER.service.getButton()))
}