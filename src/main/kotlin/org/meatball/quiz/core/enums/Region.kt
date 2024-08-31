package org.meatball.quiz.core.enums

enum class Region(val humanName: String, val l10n: String) {
    WORLD("world", "Весь мир"),
    EUROPE("europe", "Европа"),
    ASIA("asia", "Азия"),
    OCEANIA("oceania", "Океания"),
    ASIA_AND_OCEANIA("asia_and_oceania", "Азия и Океания"),
    AFRICA("africa", "Африка"),
    AMERICA("america", "Америка"),
    DEPENDENT("dependent", "Зависимые территории"),
    WORLD_PLUS("world_plus", "Весь мир экстра");

    companion object {
        private val regionByName = entries.associateBy { it.humanName }

        fun smartValueOf(key: String) = regionByName[key]
    }
}