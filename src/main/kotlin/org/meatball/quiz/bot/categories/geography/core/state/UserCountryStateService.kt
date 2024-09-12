package org.meatball.quiz.bot.categories.geography.core.state

import org.meatball.quiz.bot.categories.geography.country.entity.Country
import java.util.concurrent.ConcurrentHashMap

private val userSentCountry = ConcurrentHashMap<String, Country>()

fun updateUserCountry(userId: String, country: Country) {
    userSentCountry[userId] = country
}

fun getLastUserCountry(userId: String): Country? = userSentCountry[userId]