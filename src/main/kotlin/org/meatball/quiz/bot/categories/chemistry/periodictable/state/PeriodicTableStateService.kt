package org.meatball.quiz.bot.categories.chemistry.periodictable.state

import org.meatball.quiz.bot.categories.chemistry.periodictable.entity.PeriodicTable
import java.util.concurrent.ConcurrentHashMap

private val userSentPeriodicTable = ConcurrentHashMap<String, PeriodicTable>()

fun updateUserPeriodicTable(userId: String, periodicTable: PeriodicTable) {
    userSentPeriodicTable[userId] = periodicTable
}

fun getLastUserPeriodicTable(userId: String): PeriodicTable? = userSentPeriodicTable[userId]