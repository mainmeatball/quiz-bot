package org.meatball.quiz.bot.enums

import org.meatball.quiz.bot.button.ButtonCommandService
import org.meatball.quiz.bot.button.impl.mode.*
import org.meatball.quiz.core.enums.Region

enum class FlagModeButtonCommand(val key: String, val region: Region, val service: ButtonCommandService) {
    WORLD("fl_w", Region.WORLD, WorldFlagsButtonCommandHandler()),
    EUROPE("fl_eu", Region.EUROPE, EuropeFlagsButtonCommandHandler()),
    ASIA("fl_asia", Region.ASIA, AsiaFlagsButtonCommandHandler()),
    OCEANIA("fl_oceania", Region.OCEANIA, OceaniaFlagsButtonCommandHandler()),
    ASIA_AND_OCEANIA("fl_a_oc", Region.ASIA_AND_OCEANIA, AsiaAndOceaniaFlagsButtonCommandHandler()),
    AFRICA("fl_afr", Region.AFRICA, AfricaFlagsButtonCommandHandler()),
    AMERICA("fl_am", Region.AMERICA, AmericaFlagsButtonCommandHandler()),
    DEPENDENT("fl_dep", Region.DEPENDENT, DependentFlagsButtonCommandHandler()),
    WORLD_PLUS("fl_w_pl", Region.WORLD_PLUS, WorldPlusFlagsButtonCommandHandler());

    companion object {
        val map = entries.associateBy { it.key }
    }
}