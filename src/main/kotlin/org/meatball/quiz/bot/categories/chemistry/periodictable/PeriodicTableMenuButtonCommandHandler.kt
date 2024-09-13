package org.meatball.quiz.bot.categories.chemistry.periodictable

import org.meatball.quiz.bot.categories.chemistry.ChemistryMenuButton
import org.meatball.quiz.bot.categories.chemistry.periodictable.enums.PeriodicTableMode
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.periodicTableService
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

class PeriodicTableMenuButtonCommandHandler : ButtonCommandService {

    override val enum get() = ChemistryMenuButton.PERIODIC_TABLE
    override val buttonText get() = "Таблица Менделеева"

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val msg = SendMessageComponents("Выберите режим", keyboard = keyboard(), messageId = cbQuery.message.messageId)
        periodicTableService.clearUserState(cbQuery.from.id.toString())
        return SendMessageResponse.single(msg)
    }

    private fun keyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf(PeriodicTableMode.BY_ORDINAL_SEQ.service.getButton()))
            .keyboardRow(listOf(PeriodicTableMode.BY_ORDINAL_RANDOM.service.getButton()))
            .keyboardRow(listOf(PeriodicTableMode.BY_SYMBOL.service.getButton()))
            .keyboardRow(listOf(PeriodicTableMode.BY_NAME.service.getButton()))
    }
}