package org.meatball.quiz.bot.categories.art.pictures.service

import org.meatball.quiz.bot.categories.art.pictures.enums.PicturesButtonCommand
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.singletone.picturesService
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup.InlineKeyboardMarkupBuilder

fun getLastPictureAnswer(userId: String): SendMessageComponents {
    val lastElement = picturesService.getCurrent(userId)
    return SendMessageComponents(
        caption = lastElement.l10n,
    )
}

fun getNextPictureQuestion(userId: String): SendMessageComponents {
    val nextElement = picturesService.getNext(userId)
    return SendMessageComponents(
        text = null,
        photo = nextElement.picture,
        keyboard = getShowAnswerKeyboard()
    )
}

private fun getShowAnswerKeyboard(): InlineKeyboardMarkupBuilder {
    return InlineKeyboardMarkup.builder()
        .keyboardRow(listOf(PicturesButtonCommand.SHOW_ANSWER.service.getButton()))
}