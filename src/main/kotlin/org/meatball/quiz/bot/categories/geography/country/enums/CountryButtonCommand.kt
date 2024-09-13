package org.meatball.quiz.bot.categories.geography.country.enums

import org.meatball.quiz.bot.categories.geography.country.button.impl.CountryShowAnswerButtonCommandHandler
import org.meatball.quiz.bot.commons.button.ButtonCommand
import org.meatball.quiz.bot.commons.button.ButtonCommandService

enum class CountryButtonCommand(override val key: String, override val service: ButtonCommandService): ButtonCommand {
    SHOW_ANSWER("csa", CountryShowAnswerButtonCommandHandler()),
}