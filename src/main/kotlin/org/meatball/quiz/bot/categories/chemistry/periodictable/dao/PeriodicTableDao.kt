package org.meatball.quiz.bot.categories.chemistry.periodictable.dao

import kotlinx.serialization.json.Json
import org.meatball.quiz.bot.categories.chemistry.periodictable.entity.PeriodicTable
import org.meatball.quiz.bot.config.PATH_TO_RESOURCES
import java.io.File

class PeriodicTableDao {

    fun getPeriodicTable(): List<PeriodicTable> {
        val periodicTable = File(PERIODIC_TABLE_JSON_FILE_PATH)
        val periodicTableJson = json.decodeFromString<List<PeriodicTable>>(periodicTable.readText())
        return periodicTableJson
    }

    private companion object {
        private const val PERIODIC_TABLE_JSON_FILE_PATH = "${PATH_TO_RESOURCES}/periodic_table.json"
        private val json = Json { ignoreUnknownKeys = true }
    }
}