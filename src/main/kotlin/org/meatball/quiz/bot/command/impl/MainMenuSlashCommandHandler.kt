package org.meatball.quiz.bot.command.impl

import org.meatball.quiz.bot.answer.dto.SendMessageComponents
import org.meatball.quiz.bot.answer.dto.SendMessageResponse
import org.meatball.quiz.bot.button.ButtonCommandService
import org.meatball.quiz.bot.command.SlashCommandService
import org.meatball.quiz.bot.enums.ButtonCommand
import org.meatball.quiz.bot.enums.SlashCommand
import org.meatball.quiz.bot.singletone.keyboardButtonFactory
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class MainMenuSlashCommandHandler : SlashCommandService {

    override fun suitableFor(msg: Message): Boolean {
        return msg.entities[0].text == SlashCommand.MAIN_MENU.key ||
            msg.entities[0].text == SlashCommand.START.key
    }

    override fun getResponse(msg: Message): SendMessageResponse {
        return getMenu()
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        return keyboardButtonFactory.button("В главное меню", SlashCommand.MAIN_MENU.key)
    }

    private fun getMenu(): SendMessageResponse {
        val msg = SendMessageComponents("Выберите категорию", keyboard = keyboard())
        return SendMessageResponse.single(msg)
    }

    private fun keyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf(ButtonCommand.FLAG_MODES.service.getButton()))
    }
}