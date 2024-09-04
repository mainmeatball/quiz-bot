package org.meatball.quiz.bot.button.impl

import org.meatball.quiz.bot.answer.dto.SendMessageComponents
import org.meatball.quiz.bot.answer.dto.SendMessageResponse
import org.meatball.quiz.bot.button.ButtonCommandService
import org.meatball.quiz.bot.enums.ButtonCommand
import org.meatball.quiz.bot.singletone.keyboardButtonFactory
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class MainMenuButtonCommandHandler : ButtonCommandService {

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data == ButtonCommand.MAIN_MENU.key
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        return getMenu()
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        return keyboardButtonFactory.button("В главное меню", ButtonCommand.MAIN_MENU.key)
    }

    private fun getMenu(): SendMessageResponse {
        val msg = SendMessageComponents("Выберите категорию", keyboard = keyboard())
        return SendMessageResponse.single(msg)
    }

    private fun keyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf(ButtonCommand.FLAG_MODES.service.getButton(), ButtonCommand.COUNTRY_MODES.service.getButton()))
            .keyboardRow(listOf(ButtonCommand.CAPITAL_MODES.service.getButton()))
    }
}