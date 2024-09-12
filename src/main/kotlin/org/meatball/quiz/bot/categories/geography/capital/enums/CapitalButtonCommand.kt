package org.meatball.quiz.bot.categories.geography.capital.enums

import org.meatball.quiz.bot.categories.geography.capital.button.impl.CapitalRegionChosenButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.capital.button.impl.CapitalShowAnswerButtonCommandHandler
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.enums.ButtonCommand

enum class CapitalButtonCommand(
    override val key: String,
    override val service: ButtonCommandService
) : ButtonCommand {
    REGION_CHOSEN("cprc", CapitalRegionChosenButtonCommandHandler()),
    SHOW_ANSWER("cpsa", CapitalShowAnswerButtonCommandHandler()),
}