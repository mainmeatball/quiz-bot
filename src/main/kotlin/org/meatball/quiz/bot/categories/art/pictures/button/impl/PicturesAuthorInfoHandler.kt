package org.meatball.quiz.bot.categories.art.pictures.button.impl

import org.meatball.quiz.bot.categories.art.pictures.enums.PicturesButtonCommand
import org.meatball.quiz.bot.commons.button.ButtonCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.keyboardButtonFactory
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class PicturesAuthorInfoHandler : ButtonCommandService {

    override val enum get() = PicturesButtonCommand.AUTHOR_INFO
    override val buttonText get() = "Об авторе"

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        return SendMessageResponse(listOf())
    }
    
    override fun getButton(vararg params: Any): InlineKeyboardButton {
        val url = params.first() as String
        return keyboardButtonFactory.buttonWithUrl(buttonText, url)
    }
}