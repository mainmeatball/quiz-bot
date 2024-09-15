package org.meatball.quiz.bot.categories.art

import org.meatball.quiz.bot.categories.art.pictures.PicturesMenuButtonCommandHandler
import org.meatball.quiz.bot.commons.button.ButtonCommand
import org.meatball.quiz.bot.commons.button.ButtonCommandService

enum class ArtMenuButton(
    override val key: String,
    override val service: ButtonCommandService
) : ButtonCommand {
    PICTURES("art_pictures", PicturesMenuButtonCommandHandler()),
}