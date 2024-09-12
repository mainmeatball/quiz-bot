package org.meatball.quiz.bot.categories.chemistry.periodictable.button

import org.meatball.quiz.bot.categories.chemistry.periodictable.enums.PeriodicTableModeButtonCommand
import org.meatball.quiz.bot.categories.chemistry.periodictable.service.getNextPeriodicTableQuestion
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.singletone.keyboardButtonFactory
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

abstract class PeriodicTableModeButtonCommandService : ButtonCommandService {

    override val enum get() = null
    override val buttonText get() = null

    abstract val periodicTableModeButtonCommand: PeriodicTableModeButtonCommand

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        // Get user state
        val nextPeriodicTableQuestion = getNextPeriodicTableQuestion(userId)
        return SendMessageResponse.single(nextPeriodicTableQuestion)
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        return keyboardButtonFactory.button(periodicTableModeButtonCommand.l10n, periodicTableModeButtonCommand.key)
    }
}