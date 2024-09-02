package org.meatball.quiz.flag.button.impl

import org.meatball.quiz.bot.answer.dto.SendMessageResponse
import org.meatball.quiz.bot.button.ButtonCommandService
import org.meatball.quiz.bot.enums.ButtonCommand
import org.meatball.quiz.bot.singletone.keyboardButtonFactory
import org.meatball.quiz.bot.singletone.lastFlagAnswerProvider
import org.meatball.quiz.bot.singletone.nextFlagQuestionProvider
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class ShowFlagAnswerButtonCommandHandler : ButtonCommandService {

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data == ButtonCommand.SHOW_FLAG_ANSWER.key
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        return getShowAnswer(cbQuery)
    }

    override fun getButton(vararg params: Any): InlineKeyboardButton {
        return keyboardButtonFactory.button("Ответ", ButtonCommand.SHOW_FLAG_ANSWER.key)
    }

    private fun getShowAnswer(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        // Get last user flag answer
        val lastFlagAnswer = lastFlagAnswerProvider.getLastFlagAnswer(userId)

        // Get next flag question
        val nextFlagQuestion = nextFlagQuestionProvider.getNextFlagQuestion(userId)

        return SendMessageResponse(listOf(lastFlagAnswer, nextFlagQuestion))
    }
}