package org.meatball.quiz.country.button.impl

import org.meatball.quiz.bot.answer.dto.SendMessageComponents
import org.meatball.quiz.bot.answer.dto.SendMessageResponse
import org.meatball.quiz.bot.button.ButtonCommandService
import org.meatball.quiz.bot.enums.ButtonCommand
import org.meatball.quiz.bot.singletone.keyboardButtonFactory
import org.meatball.quiz.country.enums.CountryModeButtonCommand
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class CountryModesButtonCommandHandler : ButtonCommandService {

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data == ButtonCommand.COUNTRY_MODES.key
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        return getFlagsModes()
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        return keyboardButtonFactory.button("Страны", ButtonCommand.COUNTRY_MODES.key)
    }

    private fun getFlagsModes(): SendMessageResponse {
        val msg = SendMessageComponents("Выберите режим", keyboard = keyboard())
        return SendMessageResponse.single(msg)
    }

    private fun keyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf(CountryModeButtonCommand.WORLD.service.getButton(), CountryModeButtonCommand.EUROPE.service.getButton()))
            .keyboardRow(listOf(CountryModeButtonCommand.ASIA.service.getButton(), CountryModeButtonCommand.OCEANIA.service.getButton()))
            .keyboardRow(listOf(CountryModeButtonCommand.AFRICA.service.getButton(), CountryModeButtonCommand.AMERICA.service.getButton()))
            .keyboardRow(listOf(CountryModeButtonCommand.DEPENDENT.service.getButton(), CountryModeButtonCommand.WORLD_PLUS.service.getButton()))
    }
}