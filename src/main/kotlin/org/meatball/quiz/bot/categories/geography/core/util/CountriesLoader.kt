package org.meatball.quiz.bot.categories.geography.core.util

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.meatball.quiz.bot.config.PATH_TO_RESOURCES
import java.io.File

private const val COUNTRIES_JSON_FILE_PATH = "${PATH_TO_RESOURCES}/countries.json"

private val json = Json { ignoreUnknownKeys = true }

@Serializable
data class JsonCountry(
    val alpha2: String,
    val nameRu: String,
    val nameEn: String,
    val capitalRu: String,
    val capitalEn: String,
    val region: String
)

fun loadCounties(): List<JsonCountry> {
    val countries = File(COUNTRIES_JSON_FILE_PATH)
    val countriesJson = json.decodeFromString<List<JsonCountry>>(countries.readText())
    return countriesJson
}