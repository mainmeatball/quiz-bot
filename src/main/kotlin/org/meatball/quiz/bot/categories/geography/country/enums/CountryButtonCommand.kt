package org.meatball.quiz.bot.categories.geography.country.enums

import org.meatball.quiz.bot.categories.geography.country.button.impl.CountryRegionChosenButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.country.button.impl.CountryShowAnswerButtonCommandHandler
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.button.ButtonCommand

enum class CountryButtonCommand(override val key: String, override val service: ButtonCommandService): ButtonCommand {
    REGION_CHOSEN("crc", CountryRegionChosenButtonCommandHandler()),
    SHOW_ANSWER("csa", CountryShowAnswerButtonCommandHandler()),
}