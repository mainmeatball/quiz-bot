package org.meatball.quiz.bot.categories.geography.flag.enums

import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.categories.geography.flag.button.impl.AfricaFlagsButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.flag.button.impl.AmericaFlagsButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.flag.button.impl.AsiaAndOceaniaFlagsButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.flag.button.impl.AsiaFlagsButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.flag.button.impl.DependentFlagsButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.flag.button.impl.EuropeFlagsButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.flag.button.impl.OceaniaFlagsButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.flag.button.impl.WorldFlagsButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.flag.button.impl.WorldPlusFlagsButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.core.enums.Region
import org.meatball.quiz.bot.commons.button.ButtonCommand

enum class FlagRegionButtonCommand(
    override val key: String,
    val region: Region,
    override val service: ButtonCommandService
) : ButtonCommand {
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