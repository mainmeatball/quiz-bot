package org.meatball.quiz.bot.categories.geography.capital.enums

import org.meatball.quiz.bot.categories.geography.capital.button.impl.CapitalShowAnswerHandler
import org.meatball.quiz.bot.commons.button.ButtonCommand
import org.meatball.quiz.bot.commons.button.ButtonCommandService

enum class CapitalButtonCommand(override val key: String, override val service: ButtonCommandService) : ButtonCommand {
    SHOW_ANSWER("cpsa", CapitalShowAnswerHandler()),
}