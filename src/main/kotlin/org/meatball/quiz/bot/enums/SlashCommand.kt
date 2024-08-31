package org.meatball.quiz.bot.enums

import org.meatball.quiz.bot.command.SlashCommandService
import org.meatball.quiz.bot.command.impl.MainMenuSlashCommandHandler

enum class SlashCommand(val key: String, val service: SlashCommandService) {
    START("/start", MainMenuSlashCommandHandler()),
    MAIN_MENU("/main_menu", MainMenuSlashCommandHandler()),
}