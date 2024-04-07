package org.meatball.flags.core.service

import org.meatball.flags.core.config.FlagName
import org.meatball.flags.core.config.getFlagL10n
import org.meatball.flags.core.dao.FlagDao
import org.meatball.flags.core.entity.Flag

class FlagService {

    private val flagDao = FlagDao()
    private val flagL10n = getFlagL10n()

    fun getNextFlag(userId: String): Flag {
        val nextFlagName = getNextFlagName(userId)
        val flagFile = flagDao.getByFileName(nextFlagName)
        val flagShortName = nextFlagName.substringBefore('.').uppercase()
        val flagCountryName = flagL10n.getValue(flagShortName)
        return Flag(constructFlagNameAnswer(flagCountryName), flagFile)
    }

    private fun constructFlagNameAnswer(flagName: FlagName): String {
        return "${flagName.ru} \\(${flagName.en}\\)"
    }

    private fun getNextFlagName(userId: String): String {
        return flagDao.getRandomFlagName()
    }
}