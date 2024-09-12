package org.meatball.quiz.bot.categories.geography.country.service

import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.categories.geography.core.state.getLastUserCountry

class LastCountryAnswerProvider {

    fun getLastCountryAnswer(userId: String): SendMessageComponents {
        val lastCountry = getLastUserCountry(userId)
        return SendMessageComponents(
            text = lastCountry?.name,
        )
    }
}