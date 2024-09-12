package org.meatball.quiz.bot.commons.singletone

import org.meatball.quiz.bot.categories.chemistry.periodictable.dao.PeriodicTableDao
import org.meatball.quiz.bot.categories.chemistry.periodictable.service.LastPeriodicTableAnswerProvider
import org.meatball.quiz.bot.categories.chemistry.periodictable.service.NextPeriodicTableQuestionProvider
import org.meatball.quiz.bot.categories.chemistry.periodictable.service.PeriodicTableService
import org.meatball.quiz.bot.commons.keyboard.KeyboardButtonFactory
import org.meatball.quiz.bot.categories.geography.capital.service.LastCapitalAnswerProvider
import org.meatball.quiz.bot.categories.geography.capital.service.NextCapitalQuestionProvider
import org.meatball.quiz.bot.categories.geography.core.service.CountryService
import org.meatball.quiz.bot.categories.geography.country.service.LastCountryAnswerProvider
import org.meatball.quiz.bot.categories.geography.country.service.NextCountryQuestionProvider
import org.meatball.quiz.bot.categories.geography.flag.service.LastFlagAnswerProvider
import org.meatball.quiz.bot.categories.geography.flag.service.NextFlagQuestionProvider

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

// Periodic Table Services
val nextPeriodicTableQuestionProvider = NextPeriodicTableQuestionProvider()
val lastPeriodicTableAnswerProvider = LastPeriodicTableAnswerProvider()
val periodicTableDao = PeriodicTableDao()
val periodicTableService = PeriodicTableService()