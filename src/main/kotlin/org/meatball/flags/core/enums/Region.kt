package org.meatball.flags.core.enums

enum class Region(val humanName: String, val l10n: String) {
    WORLD("world", "World"),
    EUROPE("europe", "Europe"),
    ASIA("asia", "Asia"),
    OCEANIA("oceania", "Oceania"),
    ASIA_AND_OCEANIA("asia_and_oceania", "Asia and Oceania"),
    AFRICA("africa", "Africa"),
    AMERICA("america", "South and North America");

    companion object {
        private val regionByName = entries.associateBy { it.humanName }

        fun smartValueOf(key: String) = regionByName[key]
    }
}