package org.meatball.quiz.bot.categories.geography.capital.button.impl

import org.meatball.quiz.bot.categories.geography.capital.button.CapitalRegionButtonCommandService
import org.meatball.quiz.bot.categories.geography.capital.enums.CapitalRegionButtonCommand

class WorldCapitalsButtonCommandHandler : CapitalRegionButtonCommandService() {

    override val capitalRegionButtonCommand
        get() = CapitalRegionButtonCommand.WORLD
}

class EuropeCapitalsButtonCommandHandler : CapitalRegionButtonCommandService() {

    override val capitalRegionButtonCommand
        get() = CapitalRegionButtonCommand.EUROPE
}

class AsiaCapitalsButtonCommandHandler : CapitalRegionButtonCommandService() {

    override val capitalRegionButtonCommand
        get() = CapitalRegionButtonCommand.ASIA
}

class OceaniaCapitalsButtonCommandHandler : CapitalRegionButtonCommandService() {

    override val capitalRegionButtonCommand
        get() = CapitalRegionButtonCommand.OCEANIA
}

class AsiaAndOceaniaCapitalsButtonCommandHandler : CapitalRegionButtonCommandService() {

    override val capitalRegionButtonCommand
        get() = CapitalRegionButtonCommand.ASIA_AND_OCEANIA
}

class AfricaCapitalsButtonCommandHandler : CapitalRegionButtonCommandService() {

    override val capitalRegionButtonCommand
        get() = CapitalRegionButtonCommand.AFRICA
}

class AmericaCapitalsButtonCommandHandler : CapitalRegionButtonCommandService() {

    override val capitalRegionButtonCommand
        get() = CapitalRegionButtonCommand.AMERICA
}

class DependentCapitalsButtonCommandHandler : CapitalRegionButtonCommandService() {

    override val capitalRegionButtonCommand
        get() = CapitalRegionButtonCommand.DEPENDENT
}

class WorldPlusCapitalsButtonCommandHandler : CapitalRegionButtonCommandService() {

    override val capitalRegionButtonCommand
        get() = CapitalRegionButtonCommand.WORLD_PLUS
}