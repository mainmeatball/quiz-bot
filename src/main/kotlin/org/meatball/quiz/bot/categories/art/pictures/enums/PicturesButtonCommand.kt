package org.meatball.quiz.bot.categories.art.pictures.enums

import org.meatball.quiz.bot.categories.art.pictures.button.impl.PicturesAuthorInfoHandler
import org.meatball.quiz.bot.categories.art.pictures.button.impl.PicturesPictureInfoHandler
import org.meatball.quiz.bot.categories.art.pictures.button.impl.PicturesShowAnswerHandler
import org.meatball.quiz.bot.commons.button.ButtonCommand
import org.meatball.quiz.bot.commons.button.ButtonCommandService

enum class PicturesButtonCommand(
    override val key: String,
    override val service: ButtonCommandService
) : ButtonCommand {
    SHOW_ANSWER("pictures_sa", PicturesShowAnswerHandler()),
    AUTHOR_INFO("pictures_ai", PicturesAuthorInfoHandler()),
    PICTURE_INFO("pictures_pi", PicturesPictureInfoHandler()),
}