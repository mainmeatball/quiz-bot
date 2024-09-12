package org.meatball.quiz.bot.categories.art

import org.meatball.quiz.bot.categories.geography.capital.button.impl.CapitalRegionMenuButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.country.button.impl.CountryRegionMenuButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.flag.button.impl.FlagRegionMenuButtonCommandHandler
import org.meatball.quiz.bot.commons.button.ButtonCommandService

enum class ArtCategoryButton(val key: String, val service: ButtonCommandService) {
    PICTURES("art_pictures", CapitalRegionMenuButtonCommandHandler()),
    MOVIES("art_movies", FlagRegionMenuButtonCommandHandler()),
    MUSIC("art_music", CountryRegionMenuButtonCommandHandler()),
}