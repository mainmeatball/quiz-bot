package org.meatball.flags.core.service

import org.meatball.flags.core.config.FlagName
import org.meatball.flags.core.config.getCountryRegions
import org.meatball.flags.core.config.getFlagL10n
import org.meatball.flags.core.dao.FlagDao
import org.meatball.flags.core.entity.Flag
import org.meatball.flags.core.enums.Region
import org.meatball.flags.crm.user.service.getUserRegionConfig

class FlagService {

    private val flagDao = FlagDao()
    private val flagL10n = getFlagL10n()
    private val regions = getCountryRegions()
    private val europe = regions.getValue("Europe").map { it.key }.toHashSet()
    private val asia = regions.getValue("Asia").map { it.key }.toHashSet()
    private val oceania = regions.getValue("Oceania").map { it.key }.toHashSet()
    private val asiaAndOceania = asia + oceania
    private val africa = regions.getValue("Africa").map { it.key }.toHashSet()
    private val america = regions.getValue("Americas").map { it.key }.toHashSet()
    private val world = europe + asiaAndOceania + africa + america + "AQ"
    private val regionMap = mapOf(
        Region.WORLD to world,
        Region.EUROPE to europe,
        Region.ASIA to asia,
        Region.OCEANIA to oceania,
        Region.ASIA_AND_OCEANIA to asiaAndOceania,
        Region.AFRICA to africa,
        Region.AMERICA to america
    )

    fun getNextFlag(userId: String): Flag {
        val nextCountryAlpha2 = getNextCountryAlpha2(userId)
        val flagFile = flagDao.getByAlpha2(nextCountryAlpha2)
        val flagCountryName = flagL10n.getValue(nextCountryAlpha2)
        return Flag(constructFlagNameAnswer(flagCountryName), flagFile)
    }

    private fun constructFlagNameAnswer(flagName: FlagName): String {
        return "${flagName.ru.escape()} \\(${flagName.en.escape()}\\)"
    }

    private fun String.escape(): String {
        return replace(REGEX_MARKDOWN_V2_ESCAPE, "\\\\$1")
    }

    private fun getNextCountryAlpha2(userId: String): String {
        val userRegion = getUserRegionConfig(userId)
        return regionMap.getValue(userRegion).random()
    }

    private companion object {
        private val REGEX_MARKDOWN_V2_ESCAPE = Regex("([|{\\[\\]_~}+)(#>!=\\-.])")
    }
}