package org.meatball.quiz.bot.commons.button

interface ButtonCommand {
    val key: String
    val service: ButtonCommandService
}