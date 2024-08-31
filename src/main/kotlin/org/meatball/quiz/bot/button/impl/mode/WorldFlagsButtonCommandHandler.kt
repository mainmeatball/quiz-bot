package org.meatball.quiz.bot.button.impl.mode

import org.meatball.quiz.bot.button.FlagModeButtonCommandService
import org.meatball.quiz.bot.enums.FlagModeButtonCommand

class WorldFlagsButtonCommandHandler : FlagModeButtonCommandService {

    override val flagModeButtonCommand
        get() = FlagModeButtonCommand.WORLD
}

class EuropeFlagsButtonCommandHandler : FlagModeButtonCommandService {

    override val flagModeButtonCommand
        get() = FlagModeButtonCommand.EUROPE
}

class AsiaFlagsButtonCommandHandler : FlagModeButtonCommandService {

    override val flagModeButtonCommand
        get() = FlagModeButtonCommand.ASIA
}

class OceaniaFlagsButtonCommandHandler : FlagModeButtonCommandService {

    override val flagModeButtonCommand
        get() = FlagModeButtonCommand.OCEANIA
}

class AsiaAndOceaniaFlagsButtonCommandHandler : FlagModeButtonCommandService {

    override val flagModeButtonCommand
        get() = FlagModeButtonCommand.ASIA_AND_OCEANIA
}

class AfricaFlagsButtonCommandHandler : FlagModeButtonCommandService {

    override val flagModeButtonCommand
        get() = FlagModeButtonCommand.AFRICA
}

class AmericaFlagsButtonCommandHandler : FlagModeButtonCommandService {

    override val flagModeButtonCommand
        get() = FlagModeButtonCommand.AMERICA
}

class DependentFlagsButtonCommandHandler : FlagModeButtonCommandService {

    override val flagModeButtonCommand
        get() = FlagModeButtonCommand.DEPENDENT
}

class WorldPlusFlagsButtonCommandHandler : FlagModeButtonCommandService {

    override val flagModeButtonCommand
        get() = FlagModeButtonCommand.WORLD_PLUS
}