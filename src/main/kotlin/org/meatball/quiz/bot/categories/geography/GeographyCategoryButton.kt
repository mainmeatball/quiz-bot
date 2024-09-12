package org.meatball.quiz.bot.categories.geography

import org.meatball.quiz.bot.categories.geography.capital.button.impl.CapitalRegionMenuButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.country.button.impl.CountryRegionMenuButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.flag.button.impl.FlagRegionMenuButtonCommandHandler
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.enums.ButtonCommand

enum class GeographyCategoryButton(override val key: String, override val service: ButtonCommandService) : ButtonCommand {
    FLAG_REGION_MENU("frm", FlagRegionMenuButtonCommandHandler()),
    COUNTRY_REGION_MENU("crm", CountryRegionMenuButtonCommandHandler()),
    CAPITAL_REGION_MENU("cprm", CapitalRegionMenuButtonCommandHandler()),
}