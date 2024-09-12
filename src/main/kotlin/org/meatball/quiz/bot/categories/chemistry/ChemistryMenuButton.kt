package org.meatball.quiz.bot.categories.chemistry

import org.meatball.quiz.bot.categories.geography.capital.button.impl.CapitalRegionMenuButtonCommandHandler
import org.meatball.quiz.bot.commons.button.ButtonCommandService

enum class ChemistryMenuButton(val key: String, val service: ButtonCommandService) {
    PERIODIC_TABLE("chemistry_periodic_table", CapitalRegionMenuButtonCommandHandler()),
}