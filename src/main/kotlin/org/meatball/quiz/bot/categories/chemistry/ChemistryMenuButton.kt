package org.meatball.quiz.bot.categories.chemistry

import org.meatball.quiz.bot.categories.chemistry.periodictable.PeriodicTableMenuButtonCommandHandler
import org.meatball.quiz.bot.commons.button.ButtonCommand
import org.meatball.quiz.bot.commons.button.ButtonCommandService

enum class ChemistryMenuButton(
    override val key: String,
    override val service: ButtonCommandService
) : ButtonCommand {
    PERIODIC_TABLE("chemistry_periodic_table", PeriodicTableMenuButtonCommandHandler()),
}