package org.meatball.flags.crm.user.service

import java.util.concurrent.ConcurrentHashMap

private val userSentFlag = ConcurrentHashMap<String, String>()

fun updateUserFlag(userId: String, flagName: String) {
    userSentFlag[userId] = flagName
}

fun getLastUserFlag(userId: String): String? = userSentFlag[userId]