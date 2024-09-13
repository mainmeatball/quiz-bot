package org.meatball.quiz.bot.categories.geography.capital.enums

import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.categories.geography.capital.button.impl.AfricaCapitalsButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.capital.button.impl.AmericaCapitalsButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.capital.button.impl.AsiaAndOceaniaCapitalsButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.capital.button.impl.AsiaCapitalsButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.capital.button.impl.DependentCapitalsButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.capital.button.impl.EuropeCapitalsButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.capital.button.impl.OceaniaCapitalsButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.capital.button.impl.WorldCapitalsButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.capital.button.impl.WorldPlusCapitalsButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.core.enums.Region
import org.meatball.quiz.bot.commons.button.ButtonCommand

enum class CapitalRegionButtonCommand(
    override val key: String,
    val region: Region,
    override val service: ButtonCommandService
) : ButtonCommand {
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