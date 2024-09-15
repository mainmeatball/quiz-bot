package org.meatball.quiz.bot.categories.art.pictures

import org.meatball.quiz.bot.categories.art.ArtMenuButton
import org.meatball.quiz.bot.categories.art.pictures.enums.PicturesShowingMode
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.picturesService
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

class PicturesMenuButtonCommandHandler : ButtonCommandService {

    override val enum get() = ArtMenuButton.PICTURES
    override val buttonText get() = "Картины"

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val msg = SendMessageComponents("Выберите режим", keyboard = keyboard(), messageId = cbQuery.message.messageId)
        picturesService.clearUserState(cbQuery.from.id.toString())
        return SendMessageResponse.single(msg)
    }

    private fun keyboard(): InlineKeyboardMarkupBuilder {
        return InlineKeyboardMarkup.builder()
            .keyboardRow(listOf(PicturesShowingMode.ALL.service.getButton()))
    }
}