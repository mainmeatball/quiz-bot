package org.meatball.quiz.bot.commons.update

import org.meatball.quiz.bot.commons.dto.SendMessageOrPhoto
import org.meatball.quiz.bot.commons.dto.SendMessageOrPhoto.Companion.photo
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.telegram.telegrambots.meta.api.methods.ParseMode
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove

interface OnUpdateReceivedHandler {

    fun handle(update: Update): List<SendMessageOrPhoto> {
        val msgList = doHandle(update).msgList
        return msgList.mapNotNull { msg ->
            if (msg.caption != null) {
                val builder = EditMessageCaption.builder()
                    .parseMode(ParseMode.MARKDOWNV2)
                if (msg.keyboard != null) {
                    builder.replyMarkup(msg.keyboard.build())
                }
                builder
                    .chatId(update.callbackQuery?.message?.chatId ?: update.message.chatId)
                    .messageId(msg.messageId)
                val message = builder
                    .caption(msg.caption.replace(REGEX_MARKDOWN_V2_ESCAPE, "\\\\$1"))
                    .build()
                return@mapNotNull SendMessageOrPhoto.editCaption(message)
            }
            if (msg.photo != null) {
                val inputFile = InputFile(msg.photo)
                val builder = SendPhoto.builder()
                    .parseMode(ParseMode.MARKDOWNV2)
                if (msg.keyboard != null) {
                    builder.replyMarkup(msg.keyboard.build())
                } else {
                    builder.replyMarkup(ReplyKeyboardRemove.builder().removeKeyboard(true).build())
                }
                builder
                    .chatId(update.callbackQuery?.message?.chatId ?: update.message.chatId)
                val sendPhotoRequest = builder
                    .photo(inputFile)
                    .caption(msg.text?.replace(REGEX_MARKDOWN_V2_ESCAPE, "\\\\$1"))
                    .build()
                return@mapNotNull photo(sendPhotoRequest)
            }
            if (msg.text != null) {
                if (msg.messageId != null) {
                    val builder = EditMessageText.builder()
                        .parseMode(ParseMode.MARKDOWNV2)
                    if (msg.keyboard != null) {
                        builder.replyMarkup(msg.keyboard.build())
                    }
                    builder
                        .chatId(update.callbackQuery?.message?.chatId ?: update.message.chatId)
                        .messageId(msg.messageId)
                    val message = builder
                        .text(msg.text.replace(REGEX_MARKDOWN_V2_ESCAPE, "\\\\$1"))
                        .build()
                    return@mapNotNull SendMessageOrPhoto.editText(message)
                }
                val builder = SendMessage.builder()
                    .parseMode(ParseMode.MARKDOWNV2)
                if (msg.keyboard != null) {
                    builder.replyMarkup(msg.keyboard.build())
                } else {
                    builder.replyMarkup(ReplyKeyboardRemove.builder().removeKeyboard(true).build())
                }
                builder
                    .chatId(update.callbackQuery?.message?.chatId ?: update.message.chatId)
                val message = builder
                    .text(msg.text.replace(REGEX_MARKDOWN_V2_ESCAPE, "\\\\$1"))
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