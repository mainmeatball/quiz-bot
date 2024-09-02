package org.meatball.quiz.core.entity

import java.io.File

data class CountryInfo(
    val name: String,
    val iso2a: String,
    val flag: File,
    val geo: File?
)