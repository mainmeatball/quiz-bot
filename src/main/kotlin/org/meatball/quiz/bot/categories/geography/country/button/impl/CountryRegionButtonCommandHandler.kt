package org.meatball.quiz.bot.categories.geography.country.button.impl

import org.meatball.quiz.bot.categories.geography.country.button.CountryRegionButtonCommandService
import org.meatball.quiz.bot.categories.geography.country.enums.CountryRegionButtonCommand

class WorldCountriesButtonCommandHandler : CountryRegionButtonCommandService() {

    override val countryRegionButtonCommand
        get() = CountryRegionButtonCommand.WORLD
}

class EuropeCountriesButtonCommandHandler : CountryRegionButtonCommandService() {

    override val countryRegionButtonCommand
        get() = CountryRegionButtonCommand.EUROPE
}

class AsiaCountriesButtonCommandHandler : CountryRegionButtonCommandService() {

    override val countryRegionButtonCommand
        get() = CountryRegionButtonCommand.ASIA
}

class OceaniaCountriesButtonCommandHandler : CountryRegionButtonCommandService() {

    override val countryRegionButtonCommand
        get() = CountryRegionButtonCommand.OCEANIA
}

class AsiaAndOceaniaCountriesButtonCommandHandler : CountryRegionButtonCommandService() {

    override val countryRegionButtonCommand
        get() = CountryRegionButtonCommand.ASIA_AND_OCEANIA
}

class AfricaCountriesButtonCommandHandler : CountryRegionButtonCommandService() {

    override val countryRegionButtonCommand
        get() = CountryRegionButtonCommand.AFRICA
}

class AmericaCountriesButtonCommandHandler : CountryRegionButtonCommandService() {

    override val countryRegionButtonCommand
        get() = CountryRegionButtonCommand.AMERICA
}

class DependentCountriesButtonCommandHandler : CountryRegionButtonCommandService() {

    override val countryRegionButtonCommand
        get() = CountryRegionButtonCommand.DEPENDENT
}

class WorldPlusCountriesButtonCommandHandler : CountryRegionButtonCommandService() {

    override val countryRegionButtonCommand
        get() = CountryRegionButtonCommand.WORLD_PLUS
}