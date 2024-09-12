package org.meatball.quiz.bot.categories.geography.country

import org.meatball.quiz.bot.categories.geography.country.enums.CountryRegionButtonCommand
import org.meatball.quiz.bot.categories.geography.enums.GeographyCategoryButton
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.countryService
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

class CountryRegionMenuButtonCommandHandler : ButtonCommandService {

    override val enum get() = GeographyCategoryButton.COUNTRY_REGION_MENU
    override val buttonText get() = "Страны на карте"

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val msg = SendMessageComponents("Выберите режим", keyboard = keyboard(), messageId = cbQuery.message.messageId)
        countryService.clearUserState(cbQuery.from.id.toString())
        return SendMessageResponse.single(msg)
    }

    private fun keyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf(CountryRegionButtonCommand.WORLD.service.getButton(), CountryRegionButtonCommand.EUROPE.service.getButton()))
            .keyboardRow(listOf(CountryRegionButtonCommand.ASIA.service.getButton(), CountryRegionButtonCommand.OCEANIA.service.getButton()))
            .keyboardRow(listOf(CountryRegionButtonCommand.AFRICA.service.getButton(), CountryRegionButtonCommand.AMERICA.service.getButton()))
            .keyboardRow(listOf(CountryRegionButtonCommand.DEPENDENT.service.getButton(), CountryRegionButtonCommand.WORLD_PLUS.service.getButton()))
    }
}