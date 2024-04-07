package org.meatball.flags.crm.user.service

import org.meatball.flags.core.enums.Region
import java.util.concurrent.ConcurrentHashMap

private val userRegionConfig = ConcurrentHashMap<String, Region>()

fun updateUserRegionConfig(userId: String, region: Region) {
    userRegionConfig[userId] = region
}

fun getUserRegion(userId: String): Region = userRegionConfig[userId] ?: Region.WORLD