package org.meatball.quiz.bot.categories.geography

import org.meatball.quiz.bot.categories.MainMenuCategoryButton
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

class GeographyCategoryButtonHandler : ButtonCommandService {

    override val enum get() = MainMenuCategoryButton.GEOGRAPHY
    override val buttonText get() = "География"

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        return getGeographyMenu(cbQuery.message.messageId)
    }

    fun getGeographyMenu(messageId: Int): SendMessageResponse {
        val msg = SendMessageComponents("Выберите режим", keyboard = keyboard(), messageId = messageId)
        return SendMessageResponse.single(msg)
    }

    private fun keyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf(GeographyMenuButton.FLAG_REGION_MENU.service.getButton(), GeographyMenuButton.COUNTRY_REGION_MENU.service.getButton()))
            .keyboardRow(listOf(GeographyMenuButton.CAPITAL_REGION_MENU.service.getButton()))
    }
}