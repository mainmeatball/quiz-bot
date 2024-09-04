package org.meatball.quiz.capital.button.impl

import org.meatball.quiz.bot.answer.dto.SendMessageComponents
import org.meatball.quiz.bot.answer.dto.SendMessageResponse
import org.meatball.quiz.bot.button.ButtonCommandService
import org.meatball.quiz.bot.enums.ButtonCommand
import org.meatball.quiz.bot.singletone.keyboardButtonFactory
import org.meatball.quiz.capital.enums.CapitalModeButtonCommand
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class CapitalModesButtonCommandHandler : ButtonCommandService {

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data == ButtonCommand.CAPITAL_MODES.key
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        return getCapitalModes(cbQuery.message.messageId)
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        return keyboardButtonFactory.button("Столицы", ButtonCommand.CAPITAL_MODES.key)
    }

    private fun getCapitalModes(messageId: Int): SendMessageResponse {
        val msg = SendMessageComponents("Выберите режим", keyboard = keyboard(), messageId = messageId)
        return SendMessageResponse.single(msg)
    }

    private fun keyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf(CapitalModeButtonCommand.WORLD.service.getButton(), CapitalModeButtonCommand.EUROPE.service.getButton()))
            .keyboardRow(listOf(CapitalModeButtonCommand.ASIA.service.getButton(), CapitalModeButtonCommand.OCEANIA.service.getButton()))
            .keyboardRow(listOf(CapitalModeButtonCommand.AFRICA.service.getButton(), CapitalModeButtonCommand.AMERICA.service.getButton()))
            .keyboardRow(listOf(CapitalModeButtonCommand.DEPENDENT.service.getButton(), CapitalModeButtonCommand.WORLD_PLUS.service.getButton()))
    }
}