package org.meatball.quiz.bot.categories.geography.capital.button.impl

import org.meatball.quiz.bot.categories.geography.capital.enums.CapitalButtonCommand
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.lastCapitalAnswerProvider
import org.meatball.quiz.bot.commons.singletone.nextCapitalQuestionProvider
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

class CapitalShowAnswerButtonCommandHandler : ButtonCommandService {

    override val enum get() = CapitalButtonCommand.SHOW_ANSWER
    override val buttonText get() = "Ответ"

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        // Get last user capital answer
        val lastCapitalAnswer = lastCapitalAnswerProvider
            .getLastCapitalAnswer(userId)
            .copy(messageId = cbQuery.message.messageId)

        // Get next capital question
        val nextCapitalQuestion = nextCapitalQuestionProvider.getNextCapitalQuestion(userId)

        return SendMessageResponse(listOf(lastCapitalAnswer, nextCapitalQuestion))
    }
}