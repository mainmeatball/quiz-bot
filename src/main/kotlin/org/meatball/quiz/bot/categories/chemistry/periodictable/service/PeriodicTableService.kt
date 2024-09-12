package org.meatball.quiz.bot.categories.chemistry.periodictable.service

import org.meatball.quiz.bot.categories.chemistry.periodictable.entity.PeriodicTable
import org.meatball.quiz.bot.categories.chemistry.periodictable.enums.PeriodicTableModeButtonCommand
import org.meatball.quiz.bot.categories.chemistry.periodictable.state.getPeriodicTableMode
import org.meatball.quiz.bot.commons.singletone.periodicTableDao

class PeriodicTableService {

    private val periodicTable = periodicTableDao.getPeriodicTable()
    private val periodicTableBySymbol = periodicTable.associateBy { it.symbol }
    private val periodicTableByNumber = periodicTable.associateBy { it.number }
    private val periodicTableByRuName = periodicTable.associateBy { it.ruName }
    private val periodicTableByEnName = periodicTable.associateBy { it.enName }
    private val userStateMap = hashMapOf<String, UserState>()

    fun getNextElementInfo(userId: String): PeriodicTable {
        val nextElementUserState = getNextElementUserState(userId)
        val nextElement = nextElementUserState.currentElement()
        return nextElement
    }

    private fun getNextElementUserState(userId: String): UserState {
        var userState = userStateMap[userId] ?: defaultUserState()
        val userPeriodicTableConfig = getPeriodicTableMode(userId)
        if (userPeriodicTableConfig !== userState.periodicTableMode.key || userState.isLastElement()) {
            val mode = PeriodicTableModeButtonCommand.mapByKey.getValue(userPeriodicTableConfig)
            userState = reshuffleUserCollection(userId, mode)
        }
        userState.index++
        return userState
    }

    private fun reshuffleUserCollection(userId: String, mode: PeriodicTableModeButtonCommand): UserState {
        val elements = when (mode) {
            PeriodicTableModeButtonCommand.BY_ORDINAL -> periodicTableByNumber
            PeriodicTableModeButtonCommand.BY_SYMBOL -> periodicTableBySymbol
            PeriodicTableModeButtonCommand.BY_NAME -> periodicTableByRuName
        }
        val userState = UserState(
            elements = elements.values.toList(),
            index = -1,
            periodicTableMode = mode
        )
        userStateMap[userId] = userState
        return userState
    }

    private fun defaultUserState() = UserState(
        elements = periodicTable.shuffled(),
        index = -1,
        periodicTableMode = PeriodicTableModeButtonCommand.BY_ORDINAL
    )

    private fun constructElementAnswer(element: PeriodicTable, userState: UserState): String {
        val counter = "${userState.index + 1}/${userState.elements.lastIndex + 1}"
        return "${element.symbol} ${element.ruName} ${element.enName} - ($counter)"
    }

    private data class UserState(
        val elements: List<PeriodicTable>,
        var index: Int,
        val periodicTableMode: PeriodicTableModeButtonCommand
    ) {
        fun isLastElement() = elements.lastIndex == index

        fun currentElement() = elements[index]
    }
}