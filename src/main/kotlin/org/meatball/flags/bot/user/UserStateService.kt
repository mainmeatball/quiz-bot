package org.meatball.flags.bot.user

import java.util.concurrent.ConcurrentHashMap

private val userSentFlag = ConcurrentHashMap<String, String>()

fun updateUserFlag(userId: String, flagName: String) {
    userSentFlag[userId] = flagName
}

fun getLastUserFlag(userId: String): String? = userSentFlag[userId]