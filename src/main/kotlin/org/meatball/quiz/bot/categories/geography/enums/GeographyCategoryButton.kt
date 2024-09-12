package org.meatball.quiz.bot.categories.geography.enums

import org.meatball.quiz.bot.categories.geography.capital.CapitalRegionMenuButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.country.CountryRegionMenuButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.flag.FlagRegionMenuButtonCommandHandler
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.button.ButtonCommand

enum class GeographyCategoryButton(override val key: String, override val service: ButtonCommandService) : ButtonCommand {
    FLAG_REGION_MENU("frm", FlagRegionMenuButtonCommandHandler()),
    COUNTRY_REGION_MENU("crm", CountryRegionMenuButtonCommandHandler()),
    CAPITAL_REGION_MENU("cprm", CapitalRegionMenuButtonCommandHandler()),
}