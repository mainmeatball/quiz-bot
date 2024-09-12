package org.meatball.quiz.bot.commons


import org.meatball.quiz.bot.categories.MainMenuCategoryButton
import org.meatball.quiz.bot.categories.geography.GeographyCategoryButton
import org.meatball.quiz.bot.categories.geography.capital.enums.CapitalButtonCommand
import org.meatball.quiz.bot.categories.geography.country.enums.CountryButtonCommand
import org.meatball.quiz.bot.categories.geography.flag.enums.FlagButtonCommand
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.update.OnUpdateReceivedHandler
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Update

class ButtonCommandManager : OnUpdateReceivedHandler {

    override fun doHandle(update: Update): SendMessageResponse {
        val cbQuery = update.callbackQuery

        // Main menu categories: Geography, Art, Chemistry, Questions
        handle(MainMenuCategoryButton.GEOGRAPHY.service, cbQuery)?.let { return it }
        handle(MainMenuCategoryButton.ART.service, cbQuery)?.let { return it }
        handle(MainMenuCategoryButton.CHEMISTRY.service, cbQuery)?.let { return it }
        handle(MainMenuCategoryButton.QUESTIONS.service, cbQuery)?.let { return it }

        // Geography categories: Flags, Countries, Capitals
        handle(GeographyCategoryButton.FLAG_REGION_MENU.service, cbQuery)?.let { return it }
        handle(GeographyCategoryButton.COUNTRY_REGION_MENU.service, cbQuery)?.let { return it }
        handle(GeographyCategoryButton.CAPITAL_REGION_MENU.service, cbQuery)?.let { return it }

        // Flag button options
        handle(FlagButtonCommand.REGION_CHOSEN.service, cbQuery)?.let { return it }
        handle(FlagButtonCommand.SHOW_ANSWER.service, cbQuery)?.let { return it }

        // Country button option
        handle(CountryButtonCommand.REGION_CHOSEN.service, cbQuery)?.let { return it }
        handle(CountryButtonCommand.SHOW_ANSWER.service, cbQuery)?.let { return it }

        // Capital button option
        handle(CapitalButtonCommand.REGION_CHOSEN.service, cbQuery)?.let { return it }
        handle(CapitalButtonCommand.SHOW_ANSWER.service, cbQuery)?.let { return it }

        return SendMessageResponse(emptyList())
    }

    fun handle(service: ButtonCommandService, cbQuery: CallbackQuery): SendMessageResponse? {
        if (service.suitableFor(cbQuery)) {
            return service.getResponse(cbQuery)
        }
        return null
    }
}