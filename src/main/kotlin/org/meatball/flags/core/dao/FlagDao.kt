package org.meatball.flags.core.dao

import org.meatball.flags.core.entity.Flag
import java.io.File

class FlagDao {

    private val flagByName: Map<String, File>
    private val flagFilenames: List<String>

    init {
        val flagsDirectory = File(this::class.java.getResource("/png1000px").toURI())
        val flagImages = flagsDirectory.listFiles()
        flagByName = flagImages?.associateBy { it.name } ?: error("Couldn't read /png1000px directory in resources")
        flagFilenames = flagByName.keys.toList()
    }

    fun getByFileName(fileName: String): Flag {
        val flagImage = flagByName.getValue(fileName)
        return Flag("Russia", flagImage)
    }

    fun getAllFileNames(): List<String> {
        return flagFilenames
    }

    fun getRandomFlagName(): String {
        return flagFilenames.random()
    }
}