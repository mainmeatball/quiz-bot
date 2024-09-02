package org.meatball.quiz.flag.service

import org.meatball.quiz.bot.answer.dto.SendMessageComponents
import org.meatball.quiz.core.service.getLastUserCountry

class LastFlagAnswerProvider {

    fun getLastFlagAnswer(userId: String): SendMessageComponents {
        val lastCountryInfo = getLastUserCountry(userId)
        return SendMessageComponents(
            text = lastCountryInfo?.name,
            photo = lastCountryInfo?.geo,
        )
    }
}