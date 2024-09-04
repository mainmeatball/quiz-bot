package org.meatball.quiz.capital.button.impl

import org.meatball.quiz.bot.answer.dto.SendMessageResponse
import org.meatball.quiz.bot.button.ButtonCommandService
import org.meatball.quiz.bot.enums.ButtonCommand
import org.meatball.quiz.bot.singletone.keyboardButtonFactory
import org.meatball.quiz.bot.singletone.lastCapitalAnswerProvider
import org.meatball.quiz.bot.singletone.nextCapitalQuestionProvider
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class ShowCapitalAnswerButtonCommandHandler : ButtonCommandService {

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data == ButtonCommand.SHOW_CAPITAL_ANSWER.key
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        return getShowAnswer(cbQuery)
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        return keyboardButtonFactory.button("Ответ", ButtonCommand.SHOW_CAPITAL_ANSWER.key)
    }

    private fun getShowAnswer(cbQuery: CallbackQuery): SendMessageResponse {
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