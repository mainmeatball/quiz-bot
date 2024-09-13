package org.meatball.quiz.bot.categories.geography.capital

import org.meatball.quiz.bot.categories.geography.capital.enums.CapitalRegionButtonCommand
import org.meatball.quiz.bot.categories.geography.GeographyMenuButton
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.countryService
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

class CapitalRegionMenuButtonCommandHandler : ButtonCommandService {

    override val enum get() = GeographyMenuButton.CAPITAL_REGION_MENU
    override val buttonText get() = "Столицы"

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val msg = SendMessageComponents("Выберите режим", keyboard = keyboard(), messageId = cbQuery.message.messageId)
        countryService.clearUserState(cbQuery.from.id.toString())
        return SendMessageResponse.single(msg)
    }

    private fun keyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf(CapitalRegionButtonCommand.WORLD.service.getButton(), CapitalRegionButtonCommand.EUROPE.service.getButton()))
            .keyboardRow(listOf(CapitalRegionButtonCommand.ASIA.service.getButton(), CapitalRegionButtonCommand.OCEANIA.service.getButton()))
            .keyboardRow(listOf(CapitalRegionButtonCommand.AFRICA.service.getButton(), CapitalRegionButtonCommand.AMERICA.service.getButton()))
            .keyboardRow(listOf(CapitalRegionButtonCommand.DEPENDENT.service.getButton(), CapitalRegionButtonCommand.WORLD_PLUS.service.getButton()))
    }
}