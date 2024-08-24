package org.meatball.quiz

import org.meatball.quiz.bot.QuizTelegramBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

fun main() {
    val tgBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
    val tgBot = QuizTelegramBot()
    tgBotsApi.registerBot(tgBot)
}