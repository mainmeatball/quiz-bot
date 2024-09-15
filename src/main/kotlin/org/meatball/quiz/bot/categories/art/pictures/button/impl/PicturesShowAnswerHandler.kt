package org.meatball.quiz.bot.categories.art.pictures.button.impl

import org.meatball.quiz.bot.categories.art.pictures.enums.PicturesButtonCommand
import org.meatball.quiz.bot.categories.art.pictures.service.getLastPictureAnswer
import org.meatball.quiz.bot.categories.art.pictures.service.getNextPictureQuestion
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

class PicturesShowAnswerHandler : ButtonCommandService {

    override val enum get() = PicturesButtonCommand.SHOW_ANSWER
    override val buttonText get() = "Ответ"

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val userId = cbQuery.from.id.toString()

        val lastAnswer = getLastPictureAnswer(userId)
            .copy(messageId = cbQuery.message.messageId)

        val nextQuestion = getNextPictureQuestion(userId)

        return SendMessageResponse(listOf(lastAnswer, nextQuestion))
    }
}