package org.meatball.quiz.bot.categories.art.pictures.button.impl

import org.meatball.quiz.bot.categories.art.pictures.enums.PicturesShowingMode
import org.meatball.quiz.bot.commons.button.CommandService
import org.meatball.quiz.bot.commons.dto.SendMessageComponents
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.singletone.picturesService
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

class PictureModeChosenHandler : CommandService {

    override fun suitableFor(cbQuery: CallbackQuery): Boolean {
        return cbQuery.data in PicturesShowingMode.map
    }

    override fun getResponse(cbQuery: CallbackQuery): SendMessageResponse {
        val mode = PicturesShowingMode.map.getValue(cbQuery.data)

        picturesService.updateConfig(cbQuery.from.id.toString(), mode)

        val firstMessage = SendMessageComponents(
            text = "Будут отображаться картины: ${mode.l10n}",
            messageId = cbQuery.message.messageId
        )
        val components = listOf(firstMessage) + mode.service.getResponse(cbQuery).msgList
        return SendMessageResponse(components)
    }
}