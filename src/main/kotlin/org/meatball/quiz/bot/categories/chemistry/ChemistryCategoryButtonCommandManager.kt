package org.meatball.quiz.bot.categories.chemistry

import org.meatball.quiz.bot.categories.chemistry.periodictable.button.impl.PeriodicTableModeChosenHandler
import org.meatball.quiz.bot.categories.chemistry.periodictable.enums.PeriodicTableButtonCommand
import org.meatball.quiz.bot.commons.doHandle
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.Update

class ChemistryCategoryButtonCommandManager {

    fun handle(update: Update): SendMessageResponse? {
        val cbQuery = update.callbackQuery

        // Chemistry categories: Periodic Table
        doHandle(ChemistryMenuButton.PERIODIC_TABLE.service, cbQuery)?.let { return it }

        // Periodic Table button options
        doHandle(periodicTableModeChosenHandler, cbQuery)?.let { return it }
        doHandle(PeriodicTableButtonCommand.SHOW_ANSWER.service, cbQuery)?.let { return it }

        return null
    }

    private companion object {
        private val periodicTableModeChosenHandler = PeriodicTableModeChosenHandler()
    }
}