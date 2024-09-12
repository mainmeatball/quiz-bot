package org.meatball.quiz.bot.categories.chemistry.periodictable.state

import org.meatball.quiz.bot.categories.chemistry.periodictable.enums.PeriodicTableModeButtonCommand
import java.util.concurrent.ConcurrentHashMap

private val periodicTableConfig = ConcurrentHashMap<String, String>()

fun updatePeriodicTableConfig(userId: String, mode: String) {
    periodicTableConfig[userId] = mode
}

fun getPeriodicTableMode(userId: String): String = periodicTableConfig[userId] ?: PeriodicTableModeButtonCommand.BY_SYMBOL.key