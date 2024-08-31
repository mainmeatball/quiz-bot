package org.meatball.quiz.bot.command.manager


import org.meatball.quiz.bot.update.OnUpdateReceivedHandler
import org.meatball.quiz.bot.answer.dto.SendMessageResponse
import org.meatball.quiz.bot.enums.ButtonCommand
import org.meatball.quiz.bot.enums.SlashCommand
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