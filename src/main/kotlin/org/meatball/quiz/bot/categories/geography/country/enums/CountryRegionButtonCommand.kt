package org.meatball.quiz.bot.categories.geography.country.enums

import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.categories.geography.core.enums.Region
import org.meatball.quiz.bot.categories.geography.country.button.impl.AfricaCountriesButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.country.button.impl.AmericaCountriesButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.country.button.impl.AsiaAndOceaniaCountriesButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.country.button.impl.AsiaCountriesButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.country.button.impl.DependentCountriesButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.country.button.impl.EuropeCountriesButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.country.button.impl.OceaniaCountriesButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.country.button.impl.WorldCountriesButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.country.button.impl.WorldPlusCountriesButtonCommandHandler

enum class CountryRegionButtonCommand(val key: String, val region: Region, val service: ButtonCommandService) {
    WORLD("cu_w", Region.WORLD, WorldCountriesButtonCommandHandler()),
    EUROPE("cu_eu", Region.EUROPE, EuropeCountriesButtonCommandHandler()),
    ASIA("cu_asia", Region.ASIA, AsiaCountriesButtonCommandHandler()),
    OCEANIA("cu_oceania", Region.OCEANIA, OceaniaCountriesButtonCommandHandler()),
    ASIA_AND_OCEANIA("cu_a_oc", Region.ASIA_AND_OCEANIA, AsiaAndOceaniaCountriesButtonCommandHandler()),
    AFRICA("cu_afr", Region.AFRICA, AfricaCountriesButtonCommandHandler()),
    AMERICA("cu_am", Region.AMERICA, AmericaCountriesButtonCommandHandler()),
    DEPENDENT("cu_dep", Region.DEPENDENT, DependentCountriesButtonCommandHandler()),
    WORLD_PLUS("cu_w_pl", Region.WORLD_PLUS, WorldPlusCountriesButtonCommandHandler());

    companion object {
        val map = entries.associateBy { it.key }
    }
}