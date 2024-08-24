package org.meatball.quiz.crm.user.service

import org.meatball.quiz.core.entity.Flag
import java.util.concurrent.ConcurrentHashMap

private val userSentFlag = ConcurrentHashMap<String, Flag>()

fun updateUserFlag(userId: String, flag: Flag) {
    userSentFlag[userId] = flag
}

fun getLastUserFlag(userId: String): Flag? = userSentFlag[userId]