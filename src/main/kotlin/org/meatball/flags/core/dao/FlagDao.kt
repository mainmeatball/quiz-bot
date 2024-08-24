package org.meatball.flags.core.dao

import java.io.File

class FlagDao {

    private val flagByName: Map<String, File>
    private val flagFilenames: List<String>

    private val geoByName: Map<String, File>
    private val geoFilenames: List<String>

    init {
        val flagsDirectory = File("/home/zorq/quiz-bot/resources/png1000px")
        val geoDirectory = File("/home/zorq/quiz-bot/resources/geo1000px")
        val flagImages = flagsDirectory.listFiles()
        val geoImages = geoDirectory.listFiles()
        flagByName = flagImages?.associateBy { it.name } ?: error("Couldn't read /png1000px directory in resources")
        geoByName = geoImages?.associateBy { it.name } ?: error("Couldn't read /geo1000px directory in resources")
        flagFilenames = flagByName.keys.toList()
        geoFilenames = geoByName.keys.toList()
    }

    fun getByAlpha2(alpha2: String): File {
        return flagByName.getValue("${alpha2.lowercase()}.png")
    }

    fun getAllFileNames(): List<String> {
        return flagFilenames
    }

    fun getRandomFlagName(): String {
        return flagFilenames.random()
    }
}