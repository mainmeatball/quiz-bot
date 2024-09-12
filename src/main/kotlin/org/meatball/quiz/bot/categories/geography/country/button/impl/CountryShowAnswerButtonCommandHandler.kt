package org.meatball.quiz.bot.categories.geography.country.button.impl

import org.meatball.quiz.bot.categories.geography.country.enums.CountryButtonCommand
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.keyboardButtonFactory
import org.meatball.quiz.bot.commons.singletone.lastCountryAnswerProvider
import org.meatball.quiz.bot.commons.singletone.nextCountryQuestionProvider
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class CountryShowAnswerButtonCommandHandler : ButtonCommandService {

    override val enum get() = CountryButtonCommand.SHOW_ANSWER
    override val buttonText get() = "Ответ"

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        return getShowAnswer(cbQuery)
    }

    private fun getShowAnswer(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        // Get last user flag answer
        val lastFlagAnswer = lastCountryAnswerProvider.getLastCountryAnswer(userId)

        // Get next flag question
        val nextFlagQuestion = nextCountryQuestionProvider.getNextCountryQuestion(userId)

        return SendMessageResponse(listOf(lastFlagAnswer, nextFlagQuestion))
    }
}