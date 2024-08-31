package org.meatball.quiz.core.config

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File

private const val COUNTRIES_INFO_L10N_FILE_PATH = "$PATH_TO_RESOURCES/countries.json"

private val json = Json { ignoreUnknownKeys = true }

@Serializable
data class Country(
    val alpha2: String,
    val rul10n: String,
    val enl10n: String,
    val region: String
)

fun getCountryInfoMapByAlpha2(): Map<String, Country> {
    val countries = File(COUNTRIES_INFO_L10N_FILE_PATH)
    val countriesJson = json.decodeFromString<List<Country>>(countries.readText())
    return countriesJson.associateBy { it.alpha2 }
}