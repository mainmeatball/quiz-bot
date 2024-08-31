package org.meatball.quiz.bot.update

import org.meatball.quiz.bot.answer.dto.SendMessageOrPhoto
import org.meatball.quiz.bot.answer.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.methods.ParseMode
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update

interface OnUpdateReceivedHandler {

    fun handle(update: Update): List<SendMessageOrPhoto> {
        val msgList = doHandle(update).msgList
        return msgList.mapNotNull { (text, photo, keyboard) ->
            if (photo != null) {
                val inputFile = InputFile(photo)
                val builder = SendPhoto.builder()
                    .parseMode(ParseMode.MARKDOWNV2)
                if (keyboard != null) {
                    builder.replyMarkup(keyboard.build())
                }
                builder
                    .chatId(update.callbackQuery?.message?.chatId ?: update.message.chatId)
                val sendPhotoRequest = builder
                    .photo(inputFile)
                    .caption(text?.replace(REGEX_MARKDOWN_V2_ESCAPE, "\\\\$1"))
                    .build()
                return@mapNotNull SendMessageOrPhoto.photo(sendPhotoRequest)
            }
            if (text != null) {
                val builder = SendMessage.builder()
                    .parseMode(ParseMode.MARKDOWNV2)
                if (keyboard != null) {
                    builder.replyMarkup(keyboard.build())
                }
                builder
                    .chatId(update.callbackQuery?.message?.chatId ?: update.message.chatId)
                val message = builder
                    .text(text.replace(REGEX_MARKDOWN_V2_ESCAPE, "\\\\$1"))
                    .build()
                return@mapNotNull SendMessageOrPhoto.message(message)
            }
            return@mapNotNull null
        }
    }

    fun doHandle(update: Update): SendMessageResponse

    companion object {
        val REGEX_MARKDOWN_V2_ESCAPE = Regex("([|{\\[\\]_~}+)(#>!=\\-.])")
    }
}