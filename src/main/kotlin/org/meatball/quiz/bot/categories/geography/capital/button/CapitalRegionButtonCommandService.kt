package org.meatball.quiz.bot.categories.geography.capital.button

import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.singletone.keyboardButtonFactory
import org.meatball.quiz.bot.commons.singletone.nextCapitalQuestionProvider
import org.meatball.quiz.bot.categories.geography.capital.enums.CapitalRegionButtonCommand
import org.meatball.quiz.bot.commons.enums.ButtonCommand
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

abstract class CapitalRegionButtonCommandService : ButtonCommandService {

    override val enum get() = null
    override val buttonText get() = null

    abstract val capitalRegionButtonCommand: CapitalRegionButtonCommand

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        // Get user state
        val nextCapitalQuestion = nextCapitalQuestionProvider.getNextCapitalQuestion(userId)
        return SendMessageResponse.single(nextCapitalQuestion)
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        return keyboardButtonFactory.button(capitalRegionButtonCommand.region.l10n, capitalRegionButtonCommand.key)
    }
}