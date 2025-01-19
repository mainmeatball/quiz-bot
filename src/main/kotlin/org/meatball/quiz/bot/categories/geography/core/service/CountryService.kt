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
import kotlin.random.Random
import kotlin.random.nextInt

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

    fun getNext(userId: String): Country {
        val nextUserState = getUserState(userId, next = true)
        return constructCountry(nextUserState)
    }

    fun getCurrent(userId: String): Country {
        return constructCountry(getUserState(userId))
    }

    fun updateMode(userId: String, mode: Region) {
        reshuffleUserCollection(userId, mode)
    }

    fun clearUserState(userId: String) {
        userStateMap[userId] = Optional.empty()
    }

    fun getFourChoices(country: Country): Pair<List<Country>, Int> {
        val region = country.region
        val countriesOfRegion = regionMap.getValue(region)
        val missingAnswersN = 3
        val result = countriesOfRegion
            .filter { it != country.iso2a }
            .shuffled()
            .take(missingAnswersN)
            .map(::constructCountry)
            .toMutableList()
        val randomIndex = Random.nextInt(0..result.size)
        result.add(randomIndex, country)
        return result to randomIndex
    }

    private fun getUserState(userId: String, next: Boolean = false): UserState {
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
            countryInfo.nameRu,
            constructTextAnswer(countryInfo, userState),
            Region.smartValueOf(countryInfo.region),
            countryInfo.capitalRu,
            flagFile,
            geoFile
        )
    }

    private fun constructCountry(alpha2: String): Country {
        val flagFile = flagDao.getByAlpha2(alpha2)
        val geoFile = geoDao.getByAlpha2(alpha2)
        val countryInfo = countriesByAlpha2.getValue(alpha2)
        return Country(
            alpha2,
            countryInfo.nameRu,
            constructTextAnswer(countryInfo),
            Region.smartValueOf(countryInfo.region),
            countryInfo.capitalRu,
            flagFile,
            geoFile
        )
    }

    private fun constructTextAnswer(jsonCountry: JsonCountry, userState: UserState): String {
        val counter = "${userState.index + 1}/${userState.countries.lastIndex + 1}"
        return "${jsonCountry.nameRu} - ${jsonCountry.capitalRu} ($counter)"
    }

    private fun constructTextAnswer(jsonCountry: JsonCountry): String {
        return "${jsonCountry.nameRu} - ${jsonCountry.capitalRu}"
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