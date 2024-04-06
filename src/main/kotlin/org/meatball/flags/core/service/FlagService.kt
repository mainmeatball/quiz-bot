package org.meatball.flags.core.service

import org.meatball.flags.core.dao.FlagDao
import org.meatball.flags.core.entity.Flag

class FlagService {

    private val flagDao = FlagDao()

    fun getNextFlag(userId: String): Flag {
        val nextFlagName = getNextFlagName(userId)
        return flagDao.getByFileName(nextFlagName)
    }

    private fun getNextFlagName(userId: String): String {
        return flagDao.getRandomFlagName()
    }
}