package org.meatball.quiz.bot.categories.geography.flag.service

import org.meatball.quiz.bot.categories.geography.country.entity.Country
import org.meatball.quiz.bot.categories.geography.country.enums.CountryButtonCommand
import org.meatball.quiz.bot.categories.geography.flag.enums.FlagButtonCommand
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.singletone.countryService
import org.meatball.quiz.bot.commons.singletone.keyboardButtonFactory
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

fun getLastFlagAnswer(userId: String, isCorrect: Boolean, cbQuery: CallbackQuery): SendMessageComponents {
    val lastCountryInfo = countryService.getCurrent(userId)
    if (isCorrect) {
        return SendMessageComponents(
            caption = "✅ ${lastCountryInfo.answerName}",
            photo = lastCountryInfo.geo,
        )
    }

    val keyboard = cbQuery.message.replyMarkup.keyboard.onEach { row ->
        row.filter { it.callbackData == cbQuery.data }.forEach { it.text = "\uD83D\uDEAB ${it.text}" }
    }
    return SendMessageComponents(
        caption = null,
        photo = lastCountryInfo.geo,
        keyboard = InlineKeyboardMarkup.builder()
            .keyboard(keyboard)
    )
}

fun getNextFlagQuestion(userId: String): SendMessageComponents {
    val nextCountryInfo = countryService.getNext(userId)
    return SendMessageComponents(
        caption = null,
        photo = nextCountryInfo.flag,
        keyboard = getShowAnswerKeyboard()
    )
}

fun getNextFlagQuestionWithFourChoices(userId: String): SendMessageComponents {
    val nextCountryInfo = countryService.getNext(userId)
    return SendMessageComponents(
        caption = null,
        photo = nextCountryInfo.flag,
        keyboard = getFourChoicesKeyboard(nextCountryInfo)
    )
}

private fun getShowAnswerKeyboard(): InlineKeyboardMarkupBuilder {
    return InlineKeyboardMarkup.builder()
        .keyboardRow(listOf(FlagButtonCommand.SHOW_ANSWER.service.getButton()))
}

private fun getFourChoicesKeyboard(country: Country): InlineKeyboardMarkupBuilder {
    val (choices, correctAnswerIndex) = countryService.getFourChoices(country)
    fun getKey(index: Int) = if (index == correctAnswerIndex)
        "${FlagButtonCommand.FOUR_CHOICES.key}_ok_${choices[index].iso2a}"
    else
        "${FlagButtonCommand.FOUR_CHOICES.key}_wa_${choices[index].iso2a}"
    return InlineKeyboardMarkup.builder()
        .keyboardRow(listOf(keyboardButtonFactory.button(choices[0].nameRu, getKey(0))))
        .keyboardRow(listOf(keyboardButtonFactory.button(choices[1].nameRu, getKey(1))))
        .keyboardRow(listOf(keyboardButtonFactory.button(choices[2].nameRu, getKey(2))))
        .keyboardRow(listOf(keyboardButtonFactory.button(choices[3].nameRu, getKey(3))))
}