package org.meatball.quiz.bot.commons.slash.impl

import org.meatball.quiz.bot.categories.MainMenuCategoryButton
import org.meatball.quiz.bot.commons.slash.SlashCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.countryService
import org.meatball.quiz.bot.commons.slash.SlashCommand
import org.meatball.quiz.bot.commons.singletone.keyboardButtonFactory
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class MainMenuSlashCommandHandler : SlashCommandService {

    override fun suitableFor(msg: Message): Boolean {
        return msg.entities[0].text == SlashCommand.MAIN_MENU.key ||
            msg.entities[0].text == SlashCommand.START.key
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        return keyboardButtonFactory.button("В главное меню", SlashCommand.MAIN_MENU.key)
    }

    override fun getResponse(msg: Message): SendMessageResponse {
        val messageComponents = SendMessageComponents("Выберите категорию", keyboard = keyboard())
        countryService.clearUserState(msg.from.id.toString())
        return SendMessageResponse.single(messageComponents)
    }

    private fun keyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf(MainMenuCategoryButton.GEOGRAPHY.service.getButton()))
    }
}