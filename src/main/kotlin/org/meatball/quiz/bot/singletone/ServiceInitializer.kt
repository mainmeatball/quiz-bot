package org.meatball.quiz.bot.singletone

import org.meatball.quiz.bot.keyboard.KeyboardButtonFactory
import org.meatball.quiz.capital.service.LastCapitalAnswerProvider
import org.meatball.quiz.capital.service.NextCapitalQuestionProvider
import org.meatball.quiz.core.service.CountryService
import org.meatball.quiz.country.service.LastCountryAnswerProvider
import org.meatball.quiz.country.service.NextCountryQuestionProvider
import org.meatball.quiz.flag.service.LastFlagAnswerProvider
import org.meatball.quiz.flag.service.NextFlagQuestionProvider

// Services
val keyboardButtonFactory = KeyboardButtonFactory()

// Flag Services
val countryService = CountryService()
val nextFlagQuestionProvider = NextFlagQuestionProvider()
val lastFlagAnswerProvider = LastFlagAnswerProvider()

// Country Services
val nextCountryQuestionProvider = NextCountryQuestionProvider()
val lastCountryAnswerProvider = LastCountryAnswerProvider()

// Capital Services
val nextCapitalQuestionProvider = NextCapitalQuestionProvider()
val lastCapitalAnswerProvider = LastCapitalAnswerProvider()