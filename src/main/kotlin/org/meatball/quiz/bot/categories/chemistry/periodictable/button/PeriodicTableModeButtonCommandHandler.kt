package org.meatball.quiz.bot.categories.chemistry.periodictable.button

import org.meatball.quiz.bot.categories.chemistry.periodictable.enums.PeriodicTableModeButtonCommand

class PeriodicTableBySymbolModeButtonCommandHandler : PeriodicTableModeButtonCommandService() {

    override val periodicTableModeButtonCommand
        get() = PeriodicTableModeButtonCommand.BY_SYMBOL
}

class PeriodicTableByOrdinalModeButtonCommandHandler : PeriodicTableModeButtonCommandService() {

    override val periodicTableModeButtonCommand
        get() = PeriodicTableModeButtonCommand.BY_ORDINAL
}

class PeriodicTableByNameModeButtonCommandHandler : PeriodicTableModeButtonCommandService() {

    override val periodicTableModeButtonCommand
        get() = PeriodicTableModeButtonCommand.BY_NAME
}