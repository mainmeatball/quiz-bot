package org.meatball.quiz.bot.categories.geography.country.entity

import org.meatball.quiz.bot.categories.geography.core.enums.Region
import java.io.File

data class Country(
    val iso2a: String,
    val nameRu: String,
    val answerName: String,
    val region: Region,
    val capital: String,
    val flag: File,
    val geo: File?
)