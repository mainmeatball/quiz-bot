package org.meatball.quiz.bot.commons.update.impl

import org.meatball.quiz.bot.commons.update.OnUpdateReceivedHandler
import org.meatball.quiz.bot.commons.dto.SendMessageResponse
import org.meatball.quiz.bot.commons.ButtonCommandManager
import org.meatball.quiz.bot.commons.SlashCommandManager
import org.telegram.telegrambots.meta.api.objects.Update

class DefaultOnUpdateReceivedHandler : OnUpdateReceivedHandler {

    override fun doHandle(update: Update): SendMessageResponse = when {
        update.hasCallbackQuery() -> buttonCommandManager.handle(update)
        else -> slashCommandManager.handle(update)
    }

    private companion object {
        private val buttonCommandManager = ButtonCommandManager()
        private val slashCommandManager = SlashCommandManager()
    }
}