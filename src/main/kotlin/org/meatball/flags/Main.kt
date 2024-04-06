package org.meatball.flags

import org.meatball.flags.bot.LearnFlagsTelegramBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

fun main() {
    val tgBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
    val tgBot = LearnFlagsTelegramBot()
    tgBotsApi.registerBot(tgBot)
}