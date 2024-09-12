package org.meatball.quiz.bot.categories.chemistry.periodictable.entity

import kotlinx.serialization.Serializable

@Serializable
data class PeriodicTable(
    val symbol: String,
    val number: Int,
    val ruName: String,
    val enName: String
)