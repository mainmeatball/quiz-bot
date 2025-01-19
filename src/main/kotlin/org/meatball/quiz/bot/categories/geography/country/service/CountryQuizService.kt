package org.meatball.quiz.bot.categories.geography.country.service

import org.meatball.quiz.bot.categories.geography.country.entity.Country
import org.meatball.quiz.bot.categories.geography.country.enums.CountryButtonCommand
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.singletone.countryService
import org.meatball.quiz.bot.commons.singletone.keyboardButtonFactory
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

fun getLastCountryAnswer(userId: String, isCorrect: Boolean, cbQuery: CallbackQuery): SendMessageComponents {
    val lastCountry = countryService.getCurrent(userId)
    if (isCorrect) {
        return SendMessageComponents(
            caption = "✅ ${lastCountry.answerName}",
        )
    }

    val keyboard = cbQuery.message.replyMarkup.keyboard.onEach { row ->
        row.filter { it.callbackData == cbQuery.data }.forEach { it.text = "\uD83D\uDEAB ${it.text}" }
    }
    return SendMessageComponents(
        text = null,
        photo = lastCountry.geo,
        keyboard = InlineKeyboardMarkup.builder()
            .keyboard(keyboard)
    )
}

fun getNextCountryQuestionWithShowAnswerButton(userId: String): SendMessageComponents {
    val nextCountry = countryService.getNext(userId)
    return SendMessageComponents(
        text = null,
        photo = nextCountry.geo,
        keyboard = getShowAnswerKeyboard()
    )
}

fun getNextCountryQuestionWithFourChoices(userId: String): SendMessageComponents {
    val nextCountry = countryService.getNext(userId)
    return SendMessageComponents(
        text = null,
        photo = nextCountry.geo,
        keyboard = getFourChoicesKeyboard(nextCountry)
    )
}

private fun getShowAnswerKeyboard(): InlineKeyboardMarkupBuilder {
    return InlineKeyboardMarkup.builder()
        .keyboardRow(listOf(CountryButtonCommand.SHOW_ANSWER.service.getButton()))
}

private fun getFourChoicesKeyboard(country: Country): InlineKeyboardMarkupBuilder {
    val (choices, correctAnswerIndex) = countryService.getFourChoices(country)
    fun getKey(index: Int) = if (index == correctAnswerIndex)
            "${CountryButtonCommand.FOUR_CHOICES.key}_ok_${choices[index].iso2a}"
        else
            "${CountryButtonCommand.FOUR_CHOICES.key}_wa_${choices[index].iso2a}"
    return InlineKeyboardMarkup.builder()
        .keyboardRow(listOf(keyboardButtonFactory.button(choices[0].nameRu, getKey(0))))
        .keyboardRow(listOf(keyboardButtonFactory.button(choices[1].nameRu, getKey(1))))
        .keyboardRow(listOf(keyboardButtonFactory.button(choices[2].nameRu, getKey(2))))
        .keyboardRow(listOf(keyboardButtonFactory.button(choices[3].nameRu, getKey(3))))
}
