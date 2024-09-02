package org.meatball.quiz.country.entity

import java.io.File

data class Country(
    val name: String,
    val iso2a: String,
    val geo: File?
)