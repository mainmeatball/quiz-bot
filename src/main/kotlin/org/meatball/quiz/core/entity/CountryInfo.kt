package org.meatball.quiz.core.entity

import java.io.File

data class CountryInfo(
    val iso2a: String,
    val name: String,
    val capital: String,
    val flag: File,
    val geo: File?
)