package org.meatball.quiz.bot.button.manager


import org.meatball.quiz.bot.answer.dto.SendMessageResponse
import org.meatball.quiz.bot.enums.ButtonCommand
import org.meatball.quiz.bot.update.OnUpdateReceivedHandler
import org.telegram.telegrambots.meta.api.objects.Update

class ButtonCommandManager : OnUpdateReceivedHandler {

    override fun doHandle(update: Update): SendMessageResponse {
        val cbQuery = update.callbackQuery

        return when {
            // Main menu options:
            // Flags
            ButtonCommand.MAIN_MENU.service.suitableFor(cbQuery) -> ButtonCommand.MAIN_MENU.service.getResponse(cbQuery)

            // Flag menu options:
            // World, Europe, Asia, ...
            ButtonCommand.FLAG_MODES.service.suitableFor(cbQuery) -> ButtonCommand.FLAG_MODES.service.getResponse(cbQuery)

            // Flag mode:
            // Sends photo of the flag, handles state
            ButtonCommand.FLAG_MODE.service.suitableFor(cbQuery) -> ButtonCommand.FLAG_MODE.service.getResponse(cbQuery)

            // Flag mode:
            // Sends photo of the flag, handles state
            ButtonCommand.SHOW_ANSWER.service.suitableFor(cbQuery) -> ButtonCommand.SHOW_ANSWER.service.getResponse(cbQuery)


            else -> SendMessageResponse(emptyList())
        }
    }
}