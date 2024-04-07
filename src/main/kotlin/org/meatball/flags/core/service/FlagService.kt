package org.meatball.flags.core.service

import org.meatball.flags.core.config.FlagName
import org.meatball.flags.core.config.getCountryRegions
import org.meatball.flags.core.config.getFlagL10n
import org.meatball.flags.core.dao.FlagDao
import org.meatball.flags.core.entity.Flag
import org.meatball.flags.core.enums.Region
import org.meatball.flags.crm.user.service.getUserRegion

class FlagService {

    private val flagDao = FlagDao()
    private val flagL10n = getFlagL10n()
    private val regions = getCountryRegions()
    private val europe = regions.getValue("Europe").map { it.key }
    private val asia = regions.getValue("Asia").map { it.key }
    private val oceania = regions.getValue("Oceania").map { it.key }
    private val asiaAndOceania = asia + oceania
    private val africa = regions.getValue("Africa").map { it.key }
    private val america = regions.getValue("Americas").map { it.key }
    private val world = europe + asiaAndOceania + africa + america + "AQ"
    private val regionMap: Map<Region, Collection<String>> = mapOf(
        Region.WORLD to world,
        Region.EUROPE to europe,
        Region.ASIA to asia,
        Region.OCEANIA to oceania,
        Region.ASIA_AND_OCEANIA to asiaAndOceania,
        Region.AFRICA to africa,
        Region.AMERICA to america
    )
    private val userStateMap = hashMapOf<String, UserState>()

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
        var userState = userStateMap[userId] ?: defaultUserState()
        val userRegionConfig = getUserRegion(userId)
        if (userRegionConfig !== userState.region || !userState.iterator.hasNext()) {
            userState = reshuffleUserCollection(userId, userRegionConfig)
        }
        return userState.iterator.next()
    }

    private fun reshuffleUserCollection(userId: String, region: Region): UserState {
        val userState = UserState(
            iterator = regionMap.getValue(region).shuffled().iterator(),
            region = region
        )
        userStateMap[userId] = userState
        return userState
    }

    private fun defaultUserState() = UserState(
        iterator = world.shuffled().listIterator(),
        region = Region.WORLD
    )

    private data class UserState(
        val iterator: Iterator<String>,
        val region: Region
    )

    private companion object {
        private val REGEX_MARKDOWN_V2_ESCAPE = Regex("([|{\\[\\]_~}+)(#>!=\\-.])")
    }
}