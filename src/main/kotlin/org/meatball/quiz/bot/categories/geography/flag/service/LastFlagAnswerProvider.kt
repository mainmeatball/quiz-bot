package org.meatball.quiz.bot.categories.geography.flag.service

import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.categories.geography.core.state.getLastUserCountry

class LastFlagAnswerProvider {

    fun getLastFlagAnswer(userId: String): SendMessageComponents {
        val lastCountryInfo = getLastUserCountry(userId)
        return SendMessageComponents(
            text = lastCountryInfo?.name,
            photo = lastCountryInfo?.geo,
        )
    }
}