package org.meatball.quiz.bot.enums

import org.meatball.quiz.bot.button.ButtonCommandService
import org.meatball.quiz.bot.button.impl.FlagModeButtonCommandHandler
import org.meatball.quiz.bot.button.impl.FlagModesButtonCommandHandler
import org.meatball.quiz.bot.button.impl.MainMenuButtonCommandHandler
import org.meatball.quiz.bot.button.impl.ShowAnswerButtonCommandHandler

enum class ButtonCommand(val key: String, val service: ButtonCommandService) {
    MAIN_MENU("mm", MainMenuButtonCommandHandler()),
    FLAG_MODES("fm", FlagModesButtonCommandHandler()),
    FLAG_MODE("fm", FlagModeButtonCommandHandler()),
    SHOW_ANSWER("sa", ShowAnswerButtonCommandHandler()),
}