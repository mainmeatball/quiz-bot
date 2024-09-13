package org.meatball.quiz.bot.categories.chemistry.periodictable.service

import org.meatball.quiz.bot.categories.chemistry.periodictable.dao.PeriodicTableDao
import org.meatball.quiz.bot.categories.chemistry.periodictable.entity.PeriodicTable
import org.meatball.quiz.bot.categories.chemistry.periodictable.entity.PeriodicTableJson
import org.meatball.quiz.bot.categories.chemistry.periodictable.enums.PeriodicTableMode
import java.util.Optional
import java.util.concurrent.ConcurrentHashMap
import kotlin.jvm.optionals.getOrNull
import kotlin.math.max

class PeriodicTableService {

    private val periodicTable = periodicTableDao.getPeriodicTable()
    private val periodicTableBySymbol = periodicTable.associateBy { it.symbol }
    private val periodicTableByNumber = periodicTable.associateBy { it.number }
    private val periodicTableByRuName = periodicTable.associateBy { it.ruName }
    private val userStateMap = ConcurrentHashMap<String, Optional<UserState>>()

    fun getNext(userId: String): PeriodicTable {
        val nextUserState = getUserState(userId, next = true)
        return constructPeriodicTable(nextUserState)
    }

    fun getCurrent(userId: String): PeriodicTable {
        return constructPeriodicTable(getUserState(userId))
    }

    fun getCurrentMode(userId: String): PeriodicTableMode {
        return getUserState(userId).mode
    }

    fun updateConfig(userId: String, mode: PeriodicTableMode) {
        reshuffleUserCollection(userId, mode)
    }

    fun clearUserState(userId: String) {
        userStateMap[userId] = Optional.empty()
    }

    private fun getUserState(userId: String, next: Boolean = false): UserState {
        val currentUserState = userStateMap[userId]?.getOrNull()
        val defaultUserState = currentUserState == null
        var userState = userStateMap[userId]?.getOrNull() ?: defaultUserState()
        when {
            defaultUserState ->
                userStateMap[userId] = Optional.of(userState)
            userState.isLastElement() ->
                reshuffleUserCollection(userId, userState.mode)
        }
        if (next) {
            userState.index++
        }
        return userState
    }

    private fun reshuffleUserCollection(userId: String, mode: PeriodicTableMode): UserState {
        val elements = when (mode) {
            PeriodicTableMode.BY_ORDINAL -> periodicTableByNumber
            PeriodicTableMode.BY_SYMBOL -> periodicTableBySymbol
            PeriodicTableMode.BY_NAME -> periodicTableByRuName
        }
        val userState = UserState(
            elements = elements.values.toList(),
            index = -1,
            mode = mode
        )
        userStateMap[userId] = Optional.of(userState)
        return userState
    }

    private fun defaultUserState() = UserState(
        elements = periodicTable.shuffled(),
        index = -1,
        mode = PeriodicTableMode.BY_ORDINAL
    )

    private fun constructPeriodicTable(userState: UserState): PeriodicTable {
        val currentElement = userState.currentElement()
        return PeriodicTable(
            currentElement.symbol,
            currentElement.number,
            currentElement.ruName,
            currentElement.enName,
            constructTextAnswer(currentElement, userState)
        )
    }

    private fun constructTextAnswer(element: PeriodicTableJson, userState: UserState): String {
        val counter = "${userState.index + 1}/${userState.elements.lastIndex + 1}"
        return """
            ${element.number}
            ${element.symbol}
            ${element.ruName}
            ${element.enName}
            $counter
        """.trimIndent()
    }

    private data class UserState(
        val elements: List<PeriodicTableJson>,
        var index: Int,
        val mode: PeriodicTableMode
    ) {
        fun isLastElement() = elements.lastIndex == index

        fun currentElement() = elements[max(index, 0)]
    }

    private companion object {
        private val periodicTableDao = PeriodicTableDao()
    }
}