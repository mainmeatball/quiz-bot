package org.meatball.quiz.bot.categories.geography.flag.enums

import org.meatball.quiz.bot.categories.geography.flag.button.impl.FlagRegionChosenButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.flag.button.impl.FlagShowAnswerButtonCommandHandler
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.enums.ButtonCommand

enum class FlagButtonCommand(override val key: String, override val service: ButtonCommandService) : ButtonCommand {
    REGION_CHOSEN("frc", FlagRegionChosenButtonCommandHandler()),
    SHOW_ANSWER("fsa", FlagShowAnswerButtonCommandHandler()),
}