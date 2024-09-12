package org.meatball.quiz.bot.categories.geography.capital.service

import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.categories.geography.core.state.getLastUserCountry

class LastCapitalAnswerProvider {

    fun getLastCapitalAnswer(userId: String): SendMessageComponents {
        val lastCountry = getLastUserCountry(userId)
        return SendMessageComponents(
            text = lastCountry?.name,
        )
    }
}