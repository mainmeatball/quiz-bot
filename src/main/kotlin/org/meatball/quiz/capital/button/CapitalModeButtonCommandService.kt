package org.meatball.quiz.capital.button

import org.meatball.quiz.bot.answer.dto.SendMessageResponse
import org.meatball.quiz.bot.button.ButtonCommandService
import org.meatball.quiz.bot.singletone.keyboardButtonFactory
import org.meatball.quiz.bot.singletone.nextCapitalQuestionProvider
import org.meatball.quiz.capital.enums.CapitalModeButtonCommand
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

interface CapitalModeButtonCommandService : ButtonCommandService {

    val capitalModeButtonCommand: CapitalModeButtonCommand

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data == capitalModeButtonCommand.key
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        // Get user state
        val nextCapitalQuestion = nextCapitalQuestionProvider.getNextCapitalQuestion(userId)
        return SendMessageResponse.single(nextCapitalQuestion)
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        return keyboardButtonFactory.button(capitalModeButtonCommand.region.l10n, capitalModeButtonCommand.key)
    }
}