package org.meatball.quiz.bot.categories.chemistry.periodictable.button.impl

import org.meatball.quiz.bot.categories.chemistry.periodictable.button.PeriodicTableModeButtonCommandService
import org.meatball.quiz.bot.categories.chemistry.periodictable.enums.PeriodicTableMode

class PeriodicTableBySymbolModeButtonCommandHandler : PeriodicTableModeButtonCommandService() {

    override val periodicTableMode
        get() = PeriodicTableMode.BY_SYMBOL
}

class PeriodicTableByOrdinalModeButtonCommandHandler : PeriodicTableModeButtonCommandService() {

    override val periodicTableMode
        get() = PeriodicTableMode.BY_ORDINAL
}

class PeriodicTableByNameModeButtonCommandHandler : PeriodicTableModeButtonCommandService() {

    override val periodicTableMode
        get() = PeriodicTableMode.BY_NAME
}