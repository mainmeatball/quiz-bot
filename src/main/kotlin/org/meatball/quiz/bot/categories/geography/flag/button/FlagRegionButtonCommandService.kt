package org.meatball.quiz.bot.categories.geography.flag.button

import org.meatball.quiz.bot.categories.geography.flag.enums.FlagRegionButtonCommand
import org.meatball.quiz.bot.categories.geography.flag.service.getNextFlagQuestion
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.keyboardButtonFactory
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

abstract class FlagRegionButtonCommandService : ButtonCommandService {

    override val enum get() = null
    override val buttonText get() = null

    abstract val flagRegionButtonCommand: FlagRegionButtonCommand

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data == flagRegionButtonCommand.key
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        // Get user state
        val nextFlagQuestion = getNextFlagQuestion(userId)
        return SendMessageResponse.single(nextFlagQuestion)
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        return keyboardButtonFactory.button(flagRegionButtonCommand.region.l10n, flagRegionButtonCommand.key)
    }
}