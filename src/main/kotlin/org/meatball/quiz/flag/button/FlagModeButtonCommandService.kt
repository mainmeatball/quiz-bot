package org.meatball.quiz.flag.button

import org.meatball.quiz.bot.answer.dto.SendMessageResponse
import org.meatball.quiz.bot.button.ButtonCommandService
import org.meatball.quiz.flag.enums.FlagModeButtonCommand
import org.meatball.quiz.bot.singletone.nextFlagQuestionProvider
import org.meatball.quiz.bot.singletone.keyboardButtonFactory
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

interface FlagModeButtonCommandService : ButtonCommandService {

    val flagModeButtonCommand: FlagModeButtonCommand

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data == flagModeButtonCommand.key
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        // Get user state
        val nextFlagQuestion = nextFlagQuestionProvider.getNextFlagQuestion(userId)
        return SendMessageResponse.single(nextFlagQuestion)
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        return keyboardButtonFactory.button(flagModeButtonCommand.region.l10n, flagModeButtonCommand.key)
    }
}