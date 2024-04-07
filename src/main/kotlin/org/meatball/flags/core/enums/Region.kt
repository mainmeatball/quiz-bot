package org.meatball.flags.core.enums

enum class Region(val humanName: String, val l10n: String) {
    WORLD("world", "Весь мир"),
    EUROPE("europe", "Европа"),
    ASIA("asia", "Азия"),
    OCEANIA("oceania", "Океания"),
    ASIA_AND_OCEANIA("asia_and_oceania", "Азия и Океания"),
    AFRICA("africa", "Африка"),
    AMERICA("america", "Северная и южная Америка");

    companion object {
        private val regionByName = entries.associateBy { it.humanName }

        fun smartValueOf(key: String) = regionByName[key]
    }
}