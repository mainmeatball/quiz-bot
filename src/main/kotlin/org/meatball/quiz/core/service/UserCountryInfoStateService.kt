package org.meatball.quiz.core.service

import org.meatball.quiz.core.entity.CountryInfo
import org.meatball.quiz.country.entity.Country
import java.util.concurrent.ConcurrentHashMap

private val userSentCountry = ConcurrentHashMap<String, CountryInfo>()

fun updateUserCountry(userId: String, country: CountryInfo) {
    userSentCountry[userId] = country
}

fun getLastUserCountry(userId: String): CountryInfo? = userSentCountry[userId]