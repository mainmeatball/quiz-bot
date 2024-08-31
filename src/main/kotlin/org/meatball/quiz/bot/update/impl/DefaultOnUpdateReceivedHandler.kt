package org.meatball.quiz.bot.update.impl

import org.meatball.quiz.bot.update.OnUpdateReceivedHandler
import org.meatball.quiz.bot.answer.dto.SendMessageResponse
import org.meatball.quiz.bot.button.manager.ButtonCommandManager
import org.meatball.quiz.bot.command.manager.SlashCommandManager
import org.telegram.telegrambots.meta.api.objects.Update

class DefaultOnUpdateReceivedHandler : OnUpdateReceivedHandler {

    override fun doHandle(update: Update): SendMessageResponse = when {
        update.hasCallbackQuery() -> buttonCommandManager.doHandle(update)
        else -> slashCommandManager.doHandle(update)
    }

    private companion object {
        private val buttonCommandManager = ButtonCommandManager()
        private val slashCommandManager = SlashCommandManager()
    }
}