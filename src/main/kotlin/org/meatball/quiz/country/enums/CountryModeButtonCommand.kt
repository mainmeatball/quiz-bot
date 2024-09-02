package org.meatball.quiz.country.enums

import org.meatball.quiz.bot.button.ButtonCommandService
import org.meatball.quiz.core.enums.Region
import org.meatball.quiz.country.button.impl.mode.AfricaCountriesButtonCommandHandler
import org.meatball.quiz.country.button.impl.mode.AmericaCountriesButtonCommandHandler
import org.meatball.quiz.country.button.impl.mode.AsiaAndOceaniaCountriesButtonCommandHandler
import org.meatball.quiz.country.button.impl.mode.AsiaCountriesButtonCommandHandler
import org.meatball.quiz.country.button.impl.mode.DependentCountriesButtonCommandHandler
import org.meatball.quiz.country.button.impl.mode.EuropeCountriesButtonCommandHandler
import org.meatball.quiz.country.button.impl.mode.OceaniaCountriesButtonCommandHandler
import org.meatball.quiz.country.button.impl.mode.WorldCountriesButtonCommandHandler
import org.meatball.quiz.country.button.impl.mode.WorldPlusCountriesButtonCommandHandler

enum class CountryModeButtonCommand(val key: String, val region: Region, val service: ButtonCommandService) {
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