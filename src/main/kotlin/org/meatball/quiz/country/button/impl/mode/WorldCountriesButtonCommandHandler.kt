package org.meatball.quiz.country.button.impl.mode

import org.meatball.quiz.country.button.CountryModeButtonCommandService
import org.meatball.quiz.country.enums.CountryModeButtonCommand

class WorldCountriesButtonCommandHandler : CountryModeButtonCommandService {

    override val countryModeButtonCommand
        get() = CountryModeButtonCommand.WORLD
}

class EuropeCountriesButtonCommandHandler : CountryModeButtonCommandService {

    override val countryModeButtonCommand
        get() = CountryModeButtonCommand.EUROPE
}

class AsiaCountriesButtonCommandHandler : CountryModeButtonCommandService {

    override val countryModeButtonCommand
        get() = CountryModeButtonCommand.ASIA
}

class OceaniaCountriesButtonCommandHandler : CountryModeButtonCommandService {

    override val countryModeButtonCommand
        get() = CountryModeButtonCommand.OCEANIA
}

class AsiaAndOceaniaCountriesButtonCommandHandler : CountryModeButtonCommandService {

    override val countryModeButtonCommand
        get() = CountryModeButtonCommand.ASIA_AND_OCEANIA
}

class AfricaCountriesButtonCommandHandler : CountryModeButtonCommandService {

    override val countryModeButtonCommand
        get() = CountryModeButtonCommand.AFRICA
}

class AmericaCountriesButtonCommandHandler : CountryModeButtonCommandService {

    override val countryModeButtonCommand
        get() = CountryModeButtonCommand.AMERICA
}

class DependentCountriesButtonCommandHandler : CountryModeButtonCommandService {

    override val countryModeButtonCommand
        get() = CountryModeButtonCommand.DEPENDENT
}

class WorldPlusCountriesButtonCommandHandler : CountryModeButtonCommandService {

    override val countryModeButtonCommand
        get() = CountryModeButtonCommand.WORLD_PLUS
}