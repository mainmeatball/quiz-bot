package org.meatball.quiz.bot.commons


import org.meatball.quiz.bot.commons.slash.SlashCommandService
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.slash.SlashCommand
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

class SlashCommandManager {

    fun handle(update: Update): SendMessageResponse {
        val msg = update.message

        // Main menu
        handle(SlashCommand.MAIN_MENU.service, msg)?.let { return it }

        return SendMessageResponse(emptyList())
    }

    fun handle(service: SlashCommandService, msg: Message): SendMessageResponse? {
        if (service.suitableFor(msg)) {
            return service.getResponse(msg)
        }
        return null
    }
}