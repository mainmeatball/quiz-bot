package org.meatball.quiz.bot.categories.geography.country.entity

import java.io.File

data class Country(
    val iso2a: String,
    val name: String,
    val capital: String,
    val flag: File,
    val geo: File?
)