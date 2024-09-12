package org.meatball.quiz.bot.categories.questions

import org.meatball.quiz.bot.categories.MainMenuCategoryButton
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

class QuestionsCategoryButtonHandler : ButtonCommandService {

    override val enum get() = MainMenuCategoryButton.QUESTIONS
    override val buttonText get() = "Вопросы"

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        return getGeographyMenu(cbQuery.message.messageId)
    }

    fun getGeographyMenu(messageId: Int): SendMessageResponse {
        val msg = SendMessageComponents("Выберите режим", keyboard = keyboard(), messageId = messageId)
        return SendMessageResponse.single(msg)
    }

    private fun keyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf())
    }
}