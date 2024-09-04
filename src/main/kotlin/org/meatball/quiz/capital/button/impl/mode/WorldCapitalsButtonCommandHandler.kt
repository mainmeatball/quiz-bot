package org.meatball.quiz.capital.button.impl.mode

import org.meatball.quiz.capital.button.CapitalModeButtonCommandService
import org.meatball.quiz.capital.enums.CapitalModeButtonCommand

class WorldCapitalsButtonCommandHandler : CapitalModeButtonCommandService {

    override val capitalModeButtonCommand
        get() = CapitalModeButtonCommand.WORLD
}

class EuropeCapitalsButtonCommandHandler : CapitalModeButtonCommandService {

    override val capitalModeButtonCommand
        get() = CapitalModeButtonCommand.EUROPE
}

class AsiaCapitalsButtonCommandHandler : CapitalModeButtonCommandService {

    override val capitalModeButtonCommand
        get() = CapitalModeButtonCommand.ASIA
}

class OceaniaCapitalsButtonCommandHandler : CapitalModeButtonCommandService {

    override val capitalModeButtonCommand
        get() = CapitalModeButtonCommand.OCEANIA
}

class AsiaAndOceaniaCapitalsButtonCommandHandler : CapitalModeButtonCommandService {

    override val capitalModeButtonCommand
        get() = CapitalModeButtonCommand.ASIA_AND_OCEANIA
}

class AfricaCapitalsButtonCommandHandler : CapitalModeButtonCommandService {

    override val capitalModeButtonCommand
        get() = CapitalModeButtonCommand.AFRICA
}

class AmericaCapitalsButtonCommandHandler : CapitalModeButtonCommandService {

    override val capitalModeButtonCommand
        get() = CapitalModeButtonCommand.AMERICA
}

class DependentCapitalsButtonCommandHandler : CapitalModeButtonCommandService {

    override val capitalModeButtonCommand
        get() = CapitalModeButtonCommand.DEPENDENT
}

class WorldPlusCapitalsButtonCommandHandler : CapitalModeButtonCommandService {

    override val capitalModeButtonCommand
        get() = CapitalModeButtonCommand.WORLD_PLUS
}