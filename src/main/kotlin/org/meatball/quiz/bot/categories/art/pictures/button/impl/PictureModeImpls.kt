package org.meatball.quiz.bot.categories.art.pictures.button.impl

import org.meatball.quiz.bot.categories.art.pictures.button.PicturesShowingModeButtonCommandService
import org.meatball.quiz.bot.categories.art.pictures.enums.PicturesShowingMode

class PicturesShowAllButtonCommandHandler : PicturesShowingModeButtonCommandService() {

    override val picturesShowingMode
        get() = PicturesShowingMode.ALL
}