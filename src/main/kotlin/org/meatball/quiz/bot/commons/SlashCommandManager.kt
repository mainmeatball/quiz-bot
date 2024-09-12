package org.meatball.quiz.bot.commons


import org.meatball.quiz.bot.commons.update.OnUpdateReceivedHandler
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.enums.SlashCommand
import org.telegram.telegrambots.meta.api.objects.Update

class SlashCommandManager : OnUpdateReceivedHandler {

    override fun doHandle(update: Update): SendMessageResponse {
        val msg = update.message

        return when {
            // Main menu options
            SlashCommand.MAIN_MENU.service.suitableFor(msg) -> SlashCommand.MAIN_MENU.service.getResponse(msg)

            else -> SendMessageResponse(emptyList())
        }
    }
}