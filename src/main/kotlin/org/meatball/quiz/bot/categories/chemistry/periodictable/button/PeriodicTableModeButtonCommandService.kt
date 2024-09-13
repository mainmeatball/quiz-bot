package org.meatball.quiz.bot.categories.chemistry.periodictable.button

import org.meatball.quiz.bot.categories.chemistry.periodictable.enums.PeriodicTableMode
import org.meatball.quiz.bot.categories.chemistry.periodictable.service.getNextPeriodicTableQuestion
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

abstract class PeriodicTableModeButtonCommandService : ButtonCommandService {

    override val enum get() = periodicTableMode
    override val buttonText get() = periodicTableMode.l10n

    abstract val periodicTableMode: PeriodicTableMode

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        // Get user state
        val nextPeriodicTableQuestion = getNextPeriodicTableQuestion(userId, periodicTableMode)
        return SendMessageResponse.single(nextPeriodicTableQuestion)
    }
}