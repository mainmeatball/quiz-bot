package org.meatball.quiz.bot.categories.geography.flag.button.impl

import org.meatball.quiz.bot.categories.geography.flag.button.FlagRegionButtonCommandService
import org.meatball.quiz.bot.categories.geography.flag.enums.FlagRegionButtonCommand

class WorldFlagsButtonCommandHandler : FlagRegionButtonCommandService() {

    override val flagRegionButtonCommand
        get() = FlagRegionButtonCommand.WORLD
}

class EuropeFlagsButtonCommandHandler : FlagRegionButtonCommandService() {

    override val flagRegionButtonCommand
        get() = FlagRegionButtonCommand.EUROPE
}

class AsiaFlagsButtonCommandHandler : FlagRegionButtonCommandService() {

    override val flagRegionButtonCommand
        get() = FlagRegionButtonCommand.ASIA
}

class OceaniaFlagsButtonCommandHandler : FlagRegionButtonCommandService() {

    override val flagRegionButtonCommand
        get() = FlagRegionButtonCommand.OCEANIA
}

class AsiaAndOceaniaFlagsButtonCommandHandler : FlagRegionButtonCommandService() {

    override val flagRegionButtonCommand
        get() = FlagRegionButtonCommand.ASIA_AND_OCEANIA
}

class AfricaFlagsButtonCommandHandler : FlagRegionButtonCommandService() {

    override val flagRegionButtonCommand
        get() = FlagRegionButtonCommand.AFRICA
}

class AmericaFlagsButtonCommandHandler : FlagRegionButtonCommandService() {

    override val flagRegionButtonCommand
        get() = FlagRegionButtonCommand.AMERICA
}

class DependentFlagsButtonCommandHandler : FlagRegionButtonCommandService() {

    override val flagRegionButtonCommand
        get() = FlagRegionButtonCommand.DEPENDENT
}

class WorldPlusFlagsButtonCommandHandler : FlagRegionButtonCommandService() {

    override val flagRegionButtonCommand
        get() = FlagRegionButtonCommand.WORLD_PLUS
}