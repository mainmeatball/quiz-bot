package org.meatball.quiz.bot.categories.art.pictures.button

import org.meatball.quiz.bot.categories.art.pictures.enums.PicturesShowingMode
import org.meatball.quiz.bot.categories.art.pictures.service.getNextPictureQuestion
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

abstract class PicturesShowingModeButtonCommandService : ButtonCommandService {

    override val enum get() = picturesShowingMode
    override val buttonText get() = picturesShowingMode.l10n

    abstract val picturesShowingMode: PicturesShowingMode

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        // Get user state
        val nextPeriodicTableQuestion = getNextPictureQuestion(userId)
        return SendMessageResponse.single(nextPeriodicTableQuestion)
    }
}