package org.meatball.quiz.bot.categories.geography.core.service

import org.meatball.quiz.bot.categories.geography.core.enums.Region
import org.meatball.quiz.bot.categories.geography.core.util.JsonCountry
import org.meatball.quiz.bot.categories.geography.core.util.loadCounties
import org.meatball.quiz.bot.categories.geography.country.dao.GeoDao
import org.meatball.quiz.bot.categories.geography.country.entity.Country
import org.meatball.quiz.bot.categories.geography.flag.dao.FlagDao
import java.util.Optional
import java.util.concurrent.ConcurrentHashMap
import kotlin.jvm.optionals.getOrNull
import kotlin.math.max

class CountryService {

    private val flagDao = FlagDao()
    private val geoDao = GeoDao()
    private val countriesByAlpha2 = loadCounties().associateBy { it.alpha2 }
    private val regions = countriesByAlpha2.values.groupBy { it.region }
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
    private val userStateMap = ConcurrentHashMap<String, Optional<UserState>>()

    fun getNextCountry(userId: String): Country {
        val nextCountryUserState = getCountryUserState(userId, next = true)
        return constructCountry(nextCountryUserState)
    }

    fun getCurrentCountry(userId: String): Country {
        return constructCountry(getCountryUserState(userId))
    }

    fun updateRegion(userId: String, region: Region) {
        reshuffleUserCollection(userId, region)
    }

    fun clearUserState(userId: String) {
        userStateMap[userId] = Optional.empty()
    }

    private fun getCountryUserState(userId: String, next: Boolean = false): UserState {
        val currentUserState = userStateMap[userId]?.getOrNull()
        val defaultUserState = currentUserState == null
        var userState = userStateMap[userId]?.getOrNull() ?: defaultUserState()
        when {
            defaultUserState ->
                userStateMap[userId] = Optional.of(userState)
            userState.isLastCountry() ->
                reshuffleUserCollection(userId, userState.region)
        }
        if (next) {
            userState.index++
        }
        return userState
    }

    private fun reshuffleUserCollection(userId: String, region: Region): UserState {
        val userState = UserState(
            countries = regionMap.getValue(region).shuffled(),
            index = -1,
            region = region
        )
        userStateMap[userId] = Optional.of(userState)
        return userState
    }

    private fun defaultUserState() = UserState(
        countries = world.shuffled(),
        index = -1,
        region = Region.WORLD
    )

    private fun constructCountry(userState: UserState): Country {
        val iso2 = userState.currentCountry()
        val flagFile = flagDao.getByAlpha2(iso2)
        val geoFile = geoDao.getByAlpha2(iso2)
        val countryInfo = countriesByAlpha2.getValue(iso2)
        return Country(
            iso2,
            constructCountryNameAnswer(countryInfo, userState),
            countryInfo.capitalRu,
            flagFile,
            geoFile
        )
    }

    private fun constructCountryNameAnswer(jsonCountry: JsonCountry, userState: UserState): String {
        val counter = "${userState.index + 1}/${userState.countries.lastIndex + 1}"
        return "${jsonCountry.nameRu} - ${jsonCountry.capitalRu} ($counter)"
    }

    private data class UserState(
        val countries: List<String>,
        var index: Int,
        val region: Region
    ) {
        fun isLastCountry() = countries.lastIndex == index

        fun currentCountry() = countries[max(index, 0)]
    }
}