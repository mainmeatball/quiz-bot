package org.meatball.quiz.country.service

import org.meatball.quiz.bot.answer.dto.SendMessageComponents
import org.meatball.quiz.core.service.getLastUserCountry

class LastCountryAnswerProvider {

    fun getLastCountryAnswer(userId: String): SendMessageComponents {
        val lastCountry = getLastUserCountry(userId)
        return SendMessageComponents(
            text = lastCountry?.name,
        )
    }
}