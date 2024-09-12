package org.meatball.quiz.bot.categories.geography

import org.meatball.quiz.bot.categories.geography.capital.enums.CapitalButtonCommand
import org.meatball.quiz.bot.categories.geography.country.enums.CountryButtonCommand
import org.meatball.quiz.bot.categories.geography.enums.GeographyCategoryButton
import org.meatball.quiz.bot.categories.geography.flag.enums.FlagButtonCommand
import org.meatball.quiz.bot.commons.doHandle
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.Update

class GeographyCategoryButtonCommandManager {

    fun handle(update: Update): SendMessageResponse? {
        val cbQuery = update.callbackQuery

        // Geography categories: Flags, Countries, Capitals
        doHandle(GeographyCategoryButton.FLAG_REGION_MENU.service, cbQuery)?.let { return it }
        doHandle(GeographyCategoryButton.COUNTRY_REGION_MENU.service, cbQuery)?.let { return it }
        doHandle(GeographyCategoryButton.CAPITAL_REGION_MENU.service, cbQuery)?.let { return it }

        // Flag button options
        doHandle(FlagButtonCommand.REGION_CHOSEN.service, cbQuery)?.let { return it }
        doHandle(FlagButtonCommand.SHOW_ANSWER.service, cbQuery)?.let { return it }

        // Country button option
        doHandle(CountryButtonCommand.REGION_CHOSEN.service, cbQuery)?.let { return it }
        doHandle(CountryButtonCommand.SHOW_ANSWER.service, cbQuery)?.let { return it }

        // Capital button option
        doHandle(CapitalButtonCommand.REGION_CHOSEN.service, cbQuery)?.let { return it }
        doHandle(CapitalButtonCommand.SHOW_ANSWER.service, cbQuery)?.let { return it }

        return null
    }
}