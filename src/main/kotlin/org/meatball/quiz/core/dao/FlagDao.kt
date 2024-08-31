package org.meatball.quiz.core.dao

import org.meatball.quiz.core.config.PATH_TO_RESOURCES
import java.io.File

class FlagDao {

    private val flagByName: Map<String, File>
    private val flagFilenames: List<String>

    init {
        val flagsDirectory = File("$PATH_TO_RESOURCES/png1000px")
        val flagImages = flagsDirectory.listFiles()
        flagByName = flagImages?.associateBy { it.name } ?: error("Couldn't read /png1000px directory in resources")
        flagFilenames = flagByName.keys.toList()
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