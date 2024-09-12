package org.meatball.quiz.bot.commons.enums

import org.meatball.quiz.bot.commons.command.SlashCommandService
import org.meatball.quiz.bot.commons.command.impl.MainMenuSlashCommandHandler

enum class SlashCommand(val key: String, val service: SlashCommandService) {
    START("/start", MainMenuSlashCommandHandler()),
    MAIN_MENU("/mainmenu", MainMenuSlashCommandHandler()),
}