package org.meatball.quiz.bot.categories.geography.flag.dao

import org.meatball.quiz.bot.config.PATH_TO_RESOURCES
import java.io.File

class FlagDao {

    fun getByAlpha2(alpha2: String): File {
        val flagImagePath = "$FLAGS_PATH/${alpha2.lowercase()}.png"
        return File(flagImagePath)
    }

    companion object {
        const val FLAGS_PATH = "$PATH_TO_RESOURCES/png1000px"
    }
}