package org.meatball.quiz.bot.categories.art.pictures.entity

import kotlinx.serialization.Serializable

@Serializable
data class ArtistJson(
    val name: String,
    val nation: String,
    val genre: String,
    val years: String,
    val wiki: String,
)

@Serializable
data class WorkJson(
    val id: Int,
    val name: String,
    val url: String,
    val year: String,
)