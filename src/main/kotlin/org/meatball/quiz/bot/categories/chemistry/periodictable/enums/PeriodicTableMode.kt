package org.meatball.quiz.bot.categories.chemistry.periodictable.enums

import org.meatball.quiz.bot.categories.chemistry.periodictable.button.impl.PeriodicTableByNameModeButtonCommandHandler
import org.meatball.quiz.bot.categories.chemistry.periodictable.button.impl.PeriodicTableByOrdinalModeButtonCommandHandler
import org.meatball.quiz.bot.categories.chemistry.periodictable.button.impl.PeriodicTableBySymbolModeButtonCommandHandler
import org.meatball.quiz.bot.commons.button.ButtonCommand
import org.meatball.quiz.bot.commons.button.ButtonCommandService

enum class PeriodicTableMode(
    override val key: String,
    val l10n: String,
    override val service: ButtonCommandService
): ButtonCommand {
    BY_SYMBOL("periodic_table_by_symbol", "По символу", PeriodicTableBySymbolModeButtonCommandHandler()),
    BY_ORDINAL("periodic_table_by_ordinal", "По порядковому номеру", PeriodicTableByOrdinalModeButtonCommandHandler()),
    BY_NAME("periodic_table_by_name", "По названию", PeriodicTableByNameModeButtonCommandHandler());

    companion object {
        val map = entries.associateBy { it.key }
    }
}