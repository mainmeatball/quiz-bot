package org.meatball.quiz.bot.enums

import org.meatball.quiz.bot.button.ButtonCommandService
import org.meatball.quiz.flag.button.impl.FlagModeButtonCommandHandler
import org.meatball.quiz.flag.button.impl.FlagModesButtonCommandHandler
import org.meatball.quiz.bot.button.impl.MainMenuButtonCommandHandler
import org.meatball.quiz.flag.button.impl.ShowFlagAnswerButtonCommandHandler
import org.meatball.quiz.country.button.impl.CountryModeButtonCommandHandler
import org.meatball.quiz.country.button.impl.CountryModesButtonCommandHandler
import org.meatball.quiz.country.button.impl.ShowCountryAnswerButtonCommandHandler

enum class ButtonCommand(val key: String, val service: ButtonCommandService) {
    MAIN_MENU("mm", MainMenuButtonCommandHandler()),

    // Flags
    FLAG_MODES("fm", FlagModesButtonCommandHandler()),
    FLAG_MODE("fm", FlagModeButtonCommandHandler()),
    SHOW_FLAG_ANSWER("f_sa", ShowFlagAnswerButtonCommandHandler()),

    // Countries
    COUNTRY_MODES("cm", CountryModesButtonCommandHandler()),
    COUNTRY_MODE("cm", CountryModeButtonCommandHandler()),
    SHOW_COUNTRY_ANSWER("c_sa", ShowCountryAnswerButtonCommandHandler()),
}