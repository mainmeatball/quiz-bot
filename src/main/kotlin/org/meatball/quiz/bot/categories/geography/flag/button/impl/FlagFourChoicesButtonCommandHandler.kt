package org.meatball.quiz.bot.categories.geography.flag.button.impl

import org.meatball.quiz.bot.categories.geography.flag.enums.FlagButtonCommand
import org.meatball.quiz.bot.categories.geography.flag.service.getLastFlagAnswer
import org.meatball.quiz.bot.categories.geography.flag.service.getNextFlagQuestionWithFourChoices
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

class FlagFourChoicesButtonCommandHandler : ButtonCommandService {

    override val enum get() = FlagButtonCommand.FOUR_CHOICES
    override val buttonText get() = "..."

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data.startsWith(enum.key)
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        return getShowAnswer(cbQuery)
    }

    private fun getShowAnswer(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()
        val (isCorrectAnswer, alpha2) = parseCallbackData(cbQuery)

        // Get last user country answer
        val lastFlagAnswer = getLastFlagAnswer(userId, isCorrectAnswer, cbQuery)
            .copy(messageId = cbQuery.message.messageId)

        // Check if the correct answer was chosen
        if (isCorrectAnswer) {
            // Get next flag question
            val nextFlagQuestion = getNextFlagQuestionWithFourChoices(userId)
            return SendMessageResponse(listOf(lastFlagAnswer, nextFlagQuestion))
        } else {
            return SendMessageResponse(listOf(lastFlagAnswer))
        }
    }

    private fun parseCallbackData(cbQuery: CallbackQuery): Pair<Boolean, String> {
        val dataWithoutKey = cbQuery.data.drop(enum.key.length + 1)
        val isCorrectAnswer = dataWithoutKey.startsWith("ok")
        val alpha2 = dataWithoutKey.drop(3)
        return isCorrectAnswer to alpha2
    }
}