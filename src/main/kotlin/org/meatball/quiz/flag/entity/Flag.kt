package org.meatball.quiz.flag.entity

import java.io.File

data class Flag(
    val name: String,
    val iso2a: String,
    val image: File,
    val geo: File?
)