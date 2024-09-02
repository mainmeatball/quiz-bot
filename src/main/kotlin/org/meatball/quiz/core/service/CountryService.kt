package org.meatball.quiz.core.service

import org.meatball.quiz.core.config.Country
import org.meatball.quiz.core.config.getCountryInfoMapByAlpha2
import org.meatball.quiz.core.dao.FlagDao
import org.meatball.quiz.core.dao.GeoDao
import org.meatball.quiz.core.entity.CountryInfo
import org.meatball.quiz.core.enums.Region
import org.meatball.quiz.flag.service.getUserRegion

class CountryService {

    private val flagDao = FlagDao()
    private val geoDao = GeoDao()
    private val countryInfoMap = getCountryInfoMapByAlpha2()
    private val regions = countryInfoMap.values.groupBy { it.region }
    private val europe = regions.getValue(Region.EUROPE.humanName.replaceFirstChar { it.uppercase() }).map { it.alpha2 }
    private val asia = regions.getValue(Region.ASIA.humanName.replaceFirstChar { it.uppercase() }).map { it.alpha2 }
    private val oceania = regions.getValue(Region.OCEANIA.humanName.replaceFirstChar { it.uppercase() }).map { it.alpha2 }
    private val asiaAndOceania = asia + oceania
    private val africa = regions.getValue(Region.AFRICA.humanName.replaceFirstChar { it.uppercase() }).map { it.alpha2 }
    private val america = regions.getValue(Region.AMERICA.humanName.replaceFirstChar { it.uppercase() }).map { it.alpha2 }
    private val dependent = regions.getValue(Region.DEPENDENT.humanName.replaceFirstChar { it.uppercase() }).map { it.alpha2 }
    private val world = europe + asiaAndOceania + africa + america
    private val worldPlus = world + dependent
    private val regionMap: Map<Region, Collection<String>> = mapOf(
        Region.WORLD to world,
        Region.EUROPE to europe,
        Region.ASIA to asia,
        Region.OCEANIA to oceania,
        Region.ASIA_AND_OCEANIA to asiaAndOceania,
        Region.AFRICA to africa,
        Region.AMERICA to america,
        Region.DEPENDENT to dependent,
        Region.WORLD_PLUS to worldPlus
    )
    private val userStateMap = hashMapOf<String, UserState>()

    fun getNextCountryInfo(userId: String): CountryInfo {
        val nextCountryUserState = getNextCountryUserState(userId)
        val nextCountryAlpha2 = nextCountryUserState.currentCountry()
        val flagFile = flagDao.getByAlpha2(nextCountryAlpha2)
        val geoFile = geoDao.getByAlpha2(nextCountryAlpha2)
        val countryName = countryInfoMap.getValue(nextCountryAlpha2)
        return CountryInfo(
            constructCountryNameAnswer(countryName, nextCountryUserState),
            nextCountryAlpha2,
            flagFile,
            geoFile
        )
    }

    private fun getNextCountryUserState(userId: String): UserState {
        var userState = userStateMap[userId] ?: defaultUserState()
        val userRegionConfig = getUserRegion(userId)
        if (userRegionConfig !== userState.region || userState.isLastCountry()) {
            userState = reshuffleUserCollection(userId, userRegionConfig)
        }
        userState.index++
        return userState
    }

    private fun reshuffleUserCollection(userId: String, region: Region): UserState {
        val userState = UserState(
            countries = regionMap.getValue(region).shuffled(),
            index = -1,
            region = region
        )
        userStateMap[userId] = userState
        return userState
    }

    private fun defaultUserState() = UserState(
        countries = world.shuffled(),
        index = -1,
        region = Region.WORLD
    )

    private fun constructCountryNameAnswer(countryName: Country, userState: UserState): String {
        val counter = "${userState.index + 1}/${userState.countries.lastIndex + 1}"
        return "${countryName.rul10n} - ${countryName.enl10n} ($counter)"
    }

    private data class UserState(
        val countries: List<String>,
        var index: Int,
        val region: Region
    ) {
        fun isLastCountry() = countries.lastIndex == index

        fun currentCountry() = countries[index]
    }
}