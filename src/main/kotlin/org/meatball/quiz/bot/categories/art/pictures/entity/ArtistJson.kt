package org.meatball.quiz.bot.categories.art.pictures.entity

import kotlinx.serialization.Serializable

@Serializable
data class ArtistJson(
    val code: String,
    val name: String,
    val nation: String,
    val years: String,
    val genre: String,
    val wiki: String
)

@Serializable
data class WorkJson(
    val id: String,
    val nameRu: String,
    val nameEn: String,
    val author: String,
    val wiki: String,
    val filename: String
)