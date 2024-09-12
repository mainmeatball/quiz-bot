package org.meatball.quiz.bot.categories.chemistry.periodictable.service

import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.categories.geography.core.state.getLastUserCountry

class LastPeriodicTableAnswerProvider {

    fun getLastPeriodicTableAnswer(userId: String): SendMessageComponents {
        val lastCountry = getLastUserCountry(userId)
        return SendMessageComponents(
            text = lastCountry?.name,
        )
    }
}