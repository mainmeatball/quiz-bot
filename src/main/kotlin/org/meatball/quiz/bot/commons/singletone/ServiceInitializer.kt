package org.meatball.quiz.bot.commons.singletone

import org.meatball.quiz.bot.categories.art.pictures.service.PicturesService
import org.meatball.quiz.bot.categories.chemistry.periodictable.service.PeriodicTableService
import org.meatball.quiz.bot.categories.geography.core.service.CountryService
import org.meatball.quiz.bot.commons.keyboard.KeyboardButtonFactory

// Helper
val keyboardButtonFactory = KeyboardButtonFactory()

// Services
val countryService = CountryService()
val periodicTableService = PeriodicTableService()
val picturesService = PicturesService()