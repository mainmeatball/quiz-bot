package org.meatball.quiz.bot.commons.slash

import org.meatball.quiz.bot.commons.slash.impl.MainMenuSlashCommandHandler

enum class SlashCommand(val key: String, val service: SlashCommandService) {
    START("/start", MainMenuSlashCommandHandler()),
    MAIN_MENU("/mainmenu", MainMenuSlashCommandHandler()),
}