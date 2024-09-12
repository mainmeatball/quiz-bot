package org.meatball.quiz.bot.categories.geography.flag

import org.meatball.quiz.bot.categories.geography.enums.GeographyCategoryButton
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.categories.geography.flag.enums.FlagRegionButtonCommand
import org.meatball.quiz.bot.commons.singletone.countryService
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

class FlagRegionMenuButtonCommandHandler : ButtonCommandService {

    override val enum get() = GeographyCategoryButton.FLAG_REGION_MENU
    override val buttonText get() = "Флаги"

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val msg = SendMessageComponents("Выберите режим", keyboard = keyboard(), messageId = cbQuery.message.messageId)
        countryService.clearUserState(cbQuery.from.id.toString())
        return SendMessageResponse.single(msg)
    }

    private fun keyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf(FlagRegionButtonCommand.WORLD.service.getButton(), FlagRegionButtonCommand.EUROPE.service.getButton()))
            .keyboardRow(listOf(FlagRegionButtonCommand.ASIA.service.getButton(), FlagRegionButtonCommand.OCEANIA.service.getButton()))
            .keyboardRow(listOf(FlagRegionButtonCommand.AFRICA.service.getButton(), FlagRegionButtonCommand.AMERICA.service.getButton()))
            .keyboardRow(listOf(FlagRegionButtonCommand.DEPENDENT.service.getButton(), FlagRegionButtonCommand.WORLD_PLUS.service.getButton()))
    }
}