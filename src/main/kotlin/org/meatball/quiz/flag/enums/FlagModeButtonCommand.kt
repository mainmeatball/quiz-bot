package org.meatball.quiz.flag.enums

import org.meatball.quiz.bot.button.ButtonCommandService
import org.meatball.quiz.flag.button.impl.mode.AfricaFlagsButtonCommandHandler
import org.meatball.quiz.flag.button.impl.mode.AmericaFlagsButtonCommandHandler
import org.meatball.quiz.flag.button.impl.mode.AsiaAndOceaniaFlagsButtonCommandHandler
import org.meatball.quiz.flag.button.impl.mode.AsiaFlagsButtonCommandHandler
import org.meatball.quiz.flag.button.impl.mode.DependentFlagsButtonCommandHandler
import org.meatball.quiz.flag.button.impl.mode.EuropeFlagsButtonCommandHandler
import org.meatball.quiz.flag.button.impl.mode.OceaniaFlagsButtonCommandHandler
import org.meatball.quiz.flag.button.impl.mode.WorldFlagsButtonCommandHandler
import org.meatball.quiz.flag.button.impl.mode.WorldPlusFlagsButtonCommandHandler
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