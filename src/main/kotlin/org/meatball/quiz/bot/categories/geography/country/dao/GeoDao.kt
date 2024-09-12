package org.meatball.quiz.bot.categories.geography.country.dao

import org.meatball.quiz.bot.config.PATH_TO_RESOURCES
import java.io.File

class GeoDao {

    fun getByAlpha2(alpha2: String): File {
        val flagImagePath = "$GEO_PATH/${alpha2.lowercase()}.png"
        return File(flagImagePath)
    }

    companion object {
        const val GEO_PATH = "$PATH_TO_RESOURCES/geo1000px"
    }
}