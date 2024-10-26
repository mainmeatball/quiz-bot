package org.meatball.quiz.bot.categories.art.pictures.enums

import org.meatball.quiz.bot.categories.art.pictures.button.impl.PicturesShowAllButtonCommandHandler
import org.meatball.quiz.bot.categories.art.pictures.button.impl.PicturesShowTop100ButtonCommandHandler
import org.meatball.quiz.bot.commons.button.ButtonCommand
import org.meatball.quiz.bot.commons.button.ButtonCommandService

enum class PicturesShowingMode(
    override val key: String,
    val l10n: String,
    override val service: ButtonCommandService
) : ButtonCommand {
    TOP_100("top_100", "Топ 100", PicturesShowTop100ButtonCommandHandler()),
    ALL("pics_all", "Все", PicturesShowAllButtonCommandHandler());

    companion object {
        val map = entries.associateBy { it.key }
    }
}