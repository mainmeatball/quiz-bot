package org.meatball.quiz.bot.categories.chemistry.periodictable.button.impl

import org.meatball.quiz.bot.categories.chemistry.periodictable.button.PeriodicTableModeButtonCommandService
import org.meatball.quiz.bot.categories.chemistry.periodictable.enums.PeriodicTableMode

class PeriodicTableBySymbolModeButtonCommandHandler : PeriodicTableModeButtonCommandService() {

    override val periodicTableMode
        get() = PeriodicTableMode.BY_SYMBOL
}

class PeriodicTableByOrdinalSeqModeButtonCommandHandler : PeriodicTableModeButtonCommandService() {

    override val periodicTableMode
        get() = PeriodicTableMode.BY_ORDINAL_SEQ
}

class PeriodicTableByOrdinalRandomModeButtonCommandHandler : PeriodicTableModeButtonCommandService() {

    override val periodicTableMode
        get() = PeriodicTableMode.BY_ORDINAL_RANDOM
}

class PeriodicTableByNameModeButtonCommandHandler : PeriodicTableModeButtonCommandService() {

    override val periodicTableMode
        get() = PeriodicTableMode.BY_NAME
}