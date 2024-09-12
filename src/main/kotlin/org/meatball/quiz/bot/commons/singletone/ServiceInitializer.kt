package org.meatball.quiz.bot.commons.singletone

import org.meatball.quiz.bot.categories.chemistry.periodictable.service.PeriodicTableService
import org.meatball.quiz.bot.categories.geography.core.service.CountryService
import org.meatball.quiz.bot.commons.keyboard.KeyboardButtonFactory

// Helper
val keyboardButtonFactory = KeyboardButtonFactory()

// Geography Services
val countryService = CountryService()

// Chemistry Services
val periodicTableService = PeriodicTableService()