package org.meatball.quiz.core.dao

import org.meatball.quiz.core.config.PATH_TO_RESOURCES
import java.io.File

class GeoDao {

    private val geoByName: Map<String, File>
    private val geoFilenames: List<String>

    init {
        val geoDirectory = File("$PATH_TO_RESOURCES/geo1000px")
        val geoImages = geoDirectory.listFiles()
        geoByName = geoImages?.associateBy { it.name } ?: error("Couldn't read /geo1000px directory in resources")
        geoFilenames = geoByName.keys.toList()
    }

    fun getByAlpha2(alpha2: String): File? {
        return geoByName["${alpha2.lowercase()}.png"]
    }

    fun getAllFileNames(): List<String> {
        return geoFilenames
    }

    fun getRandomFlagName(): String {
        return geoFilenames.random()
    }
}