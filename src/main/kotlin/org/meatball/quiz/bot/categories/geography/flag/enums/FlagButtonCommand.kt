package org.meatball.quiz.bot.categories.geography.flag.enums

import org.meatball.quiz.bot.categories.geography.flag.button.impl.FlagShowAnswerButtonCommandHandler
import org.meatball.quiz.bot.commons.button.ButtonCommand
import org.meatball.quiz.bot.commons.button.ButtonCommandService

enum class FlagButtonCommand(
    override val key: String,
    override val service: ButtonCommandService
) : ButtonCommand {
    SHOW_ANSWER("fsa", FlagShowAnswerButtonCommandHandler()),
}