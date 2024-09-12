package org.meatball.quiz.bot.commons.enums

import org.meatball.quiz.bot.commons.button.ButtonCommandService

interface ButtonCommand {
    val key: String
    val service: ButtonCommandService
}