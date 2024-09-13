package org.meatball.quiz.bot.categories.chemistry.periodictable.button.impl

import org.meatball.quiz.bot.categories.chemistry.periodictable.enums.PeriodicTableButtonCommand
import org.meatball.quiz.bot.categories.chemistry.periodictable.service.getLastPeriodicTableAnswer
import org.meatball.quiz.bot.categories.chemistry.periodictable.service.getNextPeriodicTableQuestion
import org.meatball.quiz.bot.categories.geography.capital.service.getNextCapitalQuestion
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.periodicTableService
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

class PeriodicTableShowAnswerHandler : ButtonCommandService {

    override val enum get() = PeriodicTableButtonCommand.SHOW_ANSWER
    override val buttonText get() = "Ответ"

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        val lastAnswer = getLastPeriodicTableAnswer(userId)
            .copy(messageId = cbQuery.message.messageId)

        val nextQuestion = getNextPeriodicTableQuestion(userId, periodicTableService.getCurrentMode(userId))

        return SendMessageResponse(listOf(lastAnswer, nextQuestion))
    }
}