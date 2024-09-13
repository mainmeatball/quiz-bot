package org.meatball.quiz.bot.categories.geography

import org.meatball.quiz.bot.categories.geography.capital.button.impl.CapitalRegionChosenHandler
import org.meatball.quiz.bot.categories.geography.capital.enums.CapitalButtonCommand
import org.meatball.quiz.bot.categories.geography.country.button.impl.CountryRegionChosenHandler
import org.meatball.quiz.bot.categories.geography.country.enums.CountryButtonCommand
import org.meatball.quiz.bot.categories.geography.flag.button.impl.FlagRegionChosenHandler
import org.meatball.quiz.bot.categories.geography.flag.enums.FlagButtonCommand
import org.meatball.quiz.bot.commons.doHandle
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.Update

class GeographyCategoryButtonCommandManager {

    fun handle(update: Update): SendMessageResponse? {
        val cbQuery = update.callbackQuery

        // Geography categories: Flags, Countries, Capitals
        doHandle(GeographyMenuButton.FLAG_REGION_MENU.service, cbQuery)?.let { return it }
        doHandle(GeographyMenuButton.COUNTRY_REGION_MENU.service, cbQuery)?.let { return it }
        doHandle(GeographyMenuButton.CAPITAL_REGION_MENU.service, cbQuery)?.let { return it }

        // Flag button options
        doHandle(flagRegionChosenHandler, cbQuery)?.let { return it }
        doHandle(FlagButtonCommand.SHOW_ANSWER.service, cbQuery)?.let { return it }

        // Country button option
        doHandle(countryRegionChosenHandler, cbQuery)?.let { return it }
        doHandle(CountryButtonCommand.SHOW_ANSWER.service, cbQuery)?.let { return it }

        // Capital button option
        doHandle(capitalRegionChosenHandler, cbQuery)?.let { return it }
        doHandle(CapitalButtonCommand.SHOW_ANSWER.service, cbQuery)?.let { return it }

        return null
    }

    private companion object {
        private val flagRegionChosenHandler = FlagRegionChosenHandler()
        private val countryRegionChosenHandler = CountryRegionChosenHandler()
        private val capitalRegionChosenHandler = CapitalRegionChosenHandler()
    }
}