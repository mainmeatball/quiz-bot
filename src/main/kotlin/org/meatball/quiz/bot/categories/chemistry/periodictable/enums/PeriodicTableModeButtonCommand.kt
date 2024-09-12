package org.meatball.quiz.bot.categories.chemistry.periodictable.enums

import org.meatball.quiz.bot.categories.chemistry.periodictable.button.PeriodicTableByNameModeButtonCommandHandler
import org.meatball.quiz.bot.categories.chemistry.periodictable.button.PeriodicTableByOrdinalModeButtonCommandHandler
import org.meatball.quiz.bot.categories.chemistry.periodictable.button.PeriodicTableBySymbolModeButtonCommandHandler
import org.meatball.quiz.bot.commons.button.ButtonCommandService

enum class PeriodicTableModeButtonCommand(val key: String, val l10n: String, val service: ButtonCommandService) {
    BY_SYMBOL("periodic_table_by_symbol", "По символу", PeriodicTableBySymbolModeButtonCommandHandler()),
    BY_ORDINAL("periodic_table_by_ordinal", "По порядковому номеру", PeriodicTableByOrdinalModeButtonCommandHandler()),
    BY_NAME("periodic_table_by_name", "По названию", PeriodicTableByNameModeButtonCommandHandler());

    companion object {
        val mapByKey = entries.associateBy { it.key }
    }
}