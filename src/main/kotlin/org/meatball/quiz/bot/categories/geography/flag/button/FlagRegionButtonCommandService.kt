package org.meatball.quiz.bot.categories.geography.flag.button

import org.meatball.quiz.bot.categories.geography.flag.enums.FlagRegionButtonCommand
import org.meatball.quiz.bot.categories.geography.flag.service.getNextFlagQuestion
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

abstract class FlagRegionButtonCommandService : ButtonCommandService {

    override val enum get() = flagRegionButtonCommand
    override val buttonText get() = flagRegionButtonCommand.region.l10n

    abstract val flagRegionButtonCommand: FlagRegionButtonCommand

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        // Get user state
        val nextFlagQuestion = getNextFlagQuestion(userId)
        return SendMessageResponse.single(nextFlagQuestion)
    }
}