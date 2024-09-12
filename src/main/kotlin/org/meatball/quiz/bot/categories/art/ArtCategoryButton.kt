package org.meatball.quiz.bot.categories.art

import org.meatball.quiz.bot.categories.geography.capital.CapitalRegionMenuButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.country.CountryRegionMenuButtonCommandHandler
import org.meatball.quiz.bot.categories.geography.flag.FlagRegionMenuButtonCommandHandler
import org.meatball.quiz.bot.commons.button.ButtonCommandService

enum class ArtCategoryButton(val key: String, val service: ButtonCommandService) {
    PICTURES("art_pictures", CapitalRegionMenuButtonCommandHandler()),
    MOVIES("art_movies", FlagRegionMenuButtonCommandHandler()),
    MUSIC("art_music", CountryRegionMenuButtonCommandHandler()),
}