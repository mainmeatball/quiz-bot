package org.meatball.quiz.bot.categories.geography.flag.button.impl

import org.meatball.quiz.bot.categories.geography.flag.enums.FlagButtonCommand
import org.meatball.quiz.bot.categories.geography.flag.service.getLastFlagAnswer
import org.meatball.quiz.bot.categories.geography.flag.service.getNextFlagQuestion
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

class FlagShowAnswerButtonCommandHandler : ButtonCommandService {

    override val enum get() = FlagButtonCommand.SHOW_ANSWER
    override val buttonText get() = "Ответ"

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        // Get last user flag answer
        val lastFlagAnswer = getLastFlagAnswer(userId)

        // Get next flag question
        val nextFlagQuestion = getNextFlagQuestion(userId)

        return SendMessageResponse(listOf(lastFlagAnswer, nextFlagQuestion))
    }
}