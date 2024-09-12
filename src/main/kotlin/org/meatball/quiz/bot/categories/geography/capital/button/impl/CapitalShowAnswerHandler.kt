package org.meatball.quiz.bot.categories.geography.capital.button.impl

import org.meatball.quiz.bot.categories.geography.capital.enums.CapitalButtonCommand
import org.meatball.quiz.bot.categories.geography.capital.service.getLastCapitalAnswer
import org.meatball.quiz.bot.categories.geography.capital.service.getNextCapitalQuestion
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

class CapitalShowAnswerHandler : ButtonCommandService {

    override val enum get() = CapitalButtonCommand.SHOW_ANSWER
    override val buttonText get() = "Ответ"

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        // Get last user capital answer
        val lastCapitalAnswer = getLastCapitalAnswer(userId)
            .copy(messageId = cbQuery.message.messageId)

        // Get next capital question
        val nextCapitalQuestion = getNextCapitalQuestion(userId)

        return SendMessageResponse(listOf(lastCapitalAnswer, nextCapitalQuestion))
    }
}