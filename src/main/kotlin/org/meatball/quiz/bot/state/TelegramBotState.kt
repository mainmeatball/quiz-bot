package org.meatball.quiz.bot.state

enum class TelegramBotState {
    WAITING_FOR_ANSWER,
    WAITING_FOR_QUESTION,
    RECEIVE_COMMAND
}