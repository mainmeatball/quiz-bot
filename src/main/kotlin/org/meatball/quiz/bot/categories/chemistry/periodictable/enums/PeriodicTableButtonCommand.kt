package org.meatball.quiz.bot.categories.chemistry.periodictable.enums

import org.meatball.quiz.bot.categories.chemistry.periodictable.button.impl.PeriodicTableShowAnswerHandler
import org.meatball.quiz.bot.commons.button.ButtonCommand
import org.meatball.quiz.bot.commons.button.ButtonCommandService

enum class PeriodicTableButtonCommand(
    override val key: String,
    override val service: ButtonCommandService
) : ButtonCommand {
    SHOW_ANSWER("periodic_table_sa", PeriodicTableShowAnswerHandler()),
}