package org.meatball.quiz.bot.categories.handler

import org.meatball.quiz.bot.categories.MainMenuCategoryButton
import org.meatball.quiz.bot.categories.geography.GeographyCategoryButton
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.keyboardButtonFactory
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class ArtCategoryButtonHandler : ButtonCommandService {

    override val enum get() = MainMenuCategoryButton.ART
    override val buttonText get() = "Искусство"

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        return getGeographyMenu(cbQuery.message.messageId)
    }

    fun getGeographyMenu(messageId: Int): SendMessageResponse {
        val msg = SendMessageComponents("Выберите режим", keyboard = keyboard(), messageId = messageId)
        return SendMessageResponse.single(msg)
    }

    private fun keyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf())
    }
}