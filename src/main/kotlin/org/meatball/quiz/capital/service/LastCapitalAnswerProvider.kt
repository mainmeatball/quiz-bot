package org.meatball.quiz.capital.service

import org.meatball.quiz.bot.answer.dto.SendMessageComponents
import org.meatball.quiz.core.service.getLastUserCountry

class LastCapitalAnswerProvider {

    fun getLastCapitalAnswer(userId: String): SendMessageComponents {
        val lastCountry = getLastUserCountry(userId)
        return SendMessageComponents(
            text = lastCountry?.name,
        )
    }
}