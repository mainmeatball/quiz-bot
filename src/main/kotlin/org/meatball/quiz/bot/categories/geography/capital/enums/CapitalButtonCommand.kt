package org.meatball.quiz.bot.categories.geography.capital.enums

import org.meatball.quiz.bot.categories.geography.capital.button.impl.CapitalRegionChosenHandler
import org.meatball.quiz.bot.categories.geography.capital.button.impl.CapitalShowAnswerHandler
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.button.ButtonCommand

enum class CapitalButtonCommand(
    override val key: String,
    override val service: ButtonCommandService
) : ButtonCommand {
    REGION_CHOSEN("cprc", CapitalRegionChosenHandler()),
    SHOW_ANSWER("cpsa", CapitalShowAnswerHandler()),
}