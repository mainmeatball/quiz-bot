package org.meatball.quiz.bot.button.impl

import org.meatball.quiz.bot.answer.dto.SendMessageComponents
import org.meatball.quiz.bot.answer.dto.SendMessageResponse
import org.meatball.quiz.bot.button.ButtonCommandService
import org.meatball.quiz.bot.enums.ButtonCommand
import org.meatball.quiz.bot.enums.FlagModeButtonCommand
import org.meatball.quiz.bot.singletone.keyboardButtonFactory
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class FlagModesButtonCommandHandler : ButtonCommandService {

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data == ButtonCommand.FLAG_MODES.key
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        return getFlagsModes()
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        return keyboardButtonFactory.button("Флаги", ButtonCommand.FLAG_MODES.key)
    }

    private fun getFlagsModes(): SendMessageResponse {
        val msg = SendMessageComponents("Выберите режим", keyboard = keyboard())
        return SendMessageResponse.single(msg)
    }

    private fun keyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf(FlagModeButtonCommand.WORLD.service.getButton(), FlagModeButtonCommand.EUROPE.service.getButton()))
            .keyboardRow(listOf(FlagModeButtonCommand.ASIA.service.getButton(), FlagModeButtonCommand.OCEANIA.service.getButton()))
            .keyboardRow(listOf(FlagModeButtonCommand.AFRICA.service.getButton(), FlagModeButtonCommand.AMERICA.service.getButton()))
            .keyboardRow(listOf(FlagModeButtonCommand.DEPENDENT.service.getButton(), FlagModeButtonCommand.WORLD_PLUS.service.getButton()))
    }
}