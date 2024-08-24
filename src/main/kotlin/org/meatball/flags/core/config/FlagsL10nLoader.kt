package org.meatball.flags.core.config

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File

private const val FLAG_RU_L10N_FILE_NAME = "countries_ru.json"
private const val FLAG_EN_L10N_FILE_NAME = "countries_en.json"
private const val REGIONS_L10N_FILE_NAME = "regions.json"
private const val FLAG_RU_L10N_FILE_PATH = "/home/zorq/quiz-bot/resources/$FLAG_RU_L10N_FILE_NAME"
private const val FLAG_EN_L10N_FILE_PATH = "/home/zorq/quiz-bot/resources/$FLAG_EN_L10N_FILE_NAME"
private const val REGIONS_L10N_FILE_PATH = "/home/zorq/quiz-bot/resources/$REGIONS_L10N_FILE_NAME"

data class FlagName(val ru: String, val en: String)

fun getFlagL10n(): Map<String, FlagName> {
    val ruFile = File(FLAG_RU_L10N_FILE_PATH)
    val enFile = File(FLAG_EN_L10N_FILE_PATH)
    val ruJson = Json.decodeFromString<Map<String, String>>(ruFile.readText())
    val enJson = Json.decodeFromString<Map<String, String>>(enFile.readText())
    return ruJson.mapValues { FlagName(ru = it.value, en = enJson.getValue(it.key)) }
}

@Serializable
data class Country(
    val alpha2: String,
    val region: String,
    val subRegion: String,
    val regionCode: String,
    val subRegionCode: String
)

fun getCountryRegions(): Map<String, Map<String, Country>> {
    val file = File(REGIONS_L10N_FILE_PATH)
    val listOfInfo = Json.decodeFromString<List<Country>>(file.readText())
    return listOfInfo.groupBy { it.region }.mapValues { it.value.associateBy { it.alpha2 } }
}