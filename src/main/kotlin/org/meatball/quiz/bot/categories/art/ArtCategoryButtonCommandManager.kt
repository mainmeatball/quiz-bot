package org.meatball.quiz.bot.categories.art

import org.meatball.quiz.bot.categories.art.pictures.button.impl.PictureModeChosenHandler
import org.meatball.quiz.bot.categories.art.pictures.enums.PicturesButtonCommand
import org.meatball.quiz.bot.categories.art.pictures.enums.PicturesShowingMode
import org.meatball.quiz.bot.commons.doHandle
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.Update

class ArtCategoryButtonCommandManager {

    fun handle(update: Update): SendMessageResponse? {
        val cbQuery = update.callbackQuery

        // Art categories: Pictures
        doHandle(ArtMenuButton.PICTURES.service, cbQuery)?.let { return it }

        // Pictures mode options
        doHandle(pictureModeChosenHandler, cbQuery)?.let { return it }
        doHandle(PicturesButtonCommand.SHOW_ANSWER.service, cbQuery)?.let { return it }

        return null
    }

    private companion object {
        private val pictureModeChosenHandler = PictureModeChosenHandler()
    }
}