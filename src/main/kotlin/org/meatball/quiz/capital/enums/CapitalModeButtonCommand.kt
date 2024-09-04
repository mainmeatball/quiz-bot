package org.meatball.quiz.capital.enums

import org.meatball.quiz.bot.button.ButtonCommandService
import org.meatball.quiz.capital.button.impl.mode.AfricaCapitalsButtonCommandHandler
import org.meatball.quiz.capital.button.impl.mode.AmericaCapitalsButtonCommandHandler
import org.meatball.quiz.capital.button.impl.mode.AsiaAndOceaniaCapitalsButtonCommandHandler
import org.meatball.quiz.capital.button.impl.mode.AsiaCapitalsButtonCommandHandler
import org.meatball.quiz.capital.button.impl.mode.DependentCapitalsButtonCommandHandler
import org.meatball.quiz.capital.button.impl.mode.EuropeCapitalsButtonCommandHandler
import org.meatball.quiz.capital.button.impl.mode.OceaniaCapitalsButtonCommandHandler
import org.meatball.quiz.capital.button.impl.mode.WorldCapitalsButtonCommandHandler
import org.meatball.quiz.capital.button.impl.mode.WorldPlusCapitalsButtonCommandHandler
import org.meatball.quiz.core.enums.Region

enum class CapitalModeButtonCommand(val key: String, val region: Region, val service: ButtonCommandService) {
    WORLD("cp_w", Region.WORLD, WorldCapitalsButtonCommandHandler()),
    EUROPE("cp_eu", Region.EUROPE, EuropeCapitalsButtonCommandHandler()),
    ASIA("cp_asia", Region.ASIA, AsiaCapitalsButtonCommandHandler()),
    OCEANIA("cp_oceania", Region.OCEANIA, OceaniaCapitalsButtonCommandHandler()),
    ASIA_AND_OCEANIA("cp_a_oc", Region.ASIA_AND_OCEANIA, AsiaAndOceaniaCapitalsButtonCommandHandler()),
    AFRICA("cp_afr", Region.AFRICA, AfricaCapitalsButtonCommandHandler()),
    AMERICA("cp_am", Region.AMERICA, AmericaCapitalsButtonCommandHandler()),
    DEPENDENT("cp_dep", Region.DEPENDENT, DependentCapitalsButtonCommandHandler()),
    WORLD_PLUS("cp_w_pl", Region.WORLD_PLUS, WorldPlusCapitalsButtonCommandHandler());

    companion object {
        val map = entries.associateBy { it.key }
    }
}