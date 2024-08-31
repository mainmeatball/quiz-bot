package org.meatball.quiz.flag.service

import org.meatball.quiz.bot.answer.dto.SendMessageComponents
import org.meatball.quiz.bot.enums.ButtonCommand
import org.meatball.quiz.bot.singletone.flagService
import org.meatball.quiz.crm.user.service.updateUserFlag
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

class NextFlagQuestionProvider {

    fun getNextFlagQuestion(userId: String): SendMessageComponents {
        val nextFlag = flagService.getNextFlag(userId)
        updateUserFlag(userId, nextFlag)
        return SendMessageComponents(
            text = null,
            photo = nextFlag.image,
            keyboard = getShowAnswerKeyboard()
        )
    }

    private fun getShowAnswerKeyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf(ButtonCommand.SHOW_ANSWER.service.getButton()))
    }

}