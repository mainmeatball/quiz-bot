package org.meatball.quiz.bot.categories.art.pictures.service

import org.meatball.quiz.bot.categories.art.pictures.dao.PicturesDao
import org.meatball.quiz.bot.categories.art.pictures.entity.Picture
import org.meatball.quiz.bot.categories.art.pictures.entity.WorkJson
import org.meatball.quiz.bot.categories.art.pictures.enums.PicturesShowingMode
import java.util.Optional
import java.util.concurrent.ConcurrentHashMap
import kotlin.jvm.optionals.getOrNull
import kotlin.math.max

class PicturesService {

    private val works = picturesDao.getWorks()
    private val artists = picturesDao.getArtists()
    private val userStateMap = ConcurrentHashMap<String, Optional<UserState>>()

    fun getNext(userId: String): Picture {
        val nextUserState = getUserState(userId, next = true)
        return constructPicture(nextUserState)
    }

    fun getCurrent(userId: String): Picture {
        return constructPicture(getUserState(userId))
    }

    fun getCurrentMode(userId: String): PicturesShowingMode {
        return getUserState(userId).mode
    }

    fun updateConfig(userId: String, mode: PicturesShowingMode) {
        reshuffleUserCollection(userId, mode)
    }

    fun clearUserState(userId: String) {
        userStateMap[userId] = Optional.empty()
    }

    private fun getUserState(userId: String, next: Boolean = false): UserState {
        val currentUserState = userStateMap[userId]?.getOrNull()
        val defaultUserState = currentUserState == null
        var userState = userStateMap[userId]?.getOrNull() ?: defaultUserState()
        when {
            defaultUserState ->
                userStateMap[userId] = Optional.of(userState)
            userState.isLastElement() ->
                reshuffleUserCollection(userId, userState.mode)
        }
        if (next) {
            userState.index++
        }
        return userState
    }

    private fun reshuffleUserCollection(userId: String, mode: PicturesShowingMode): UserState {
        val userState = UserState(
            elements = works.values.shuffled(),
            index = -1,
            mode = mode
        )
        userStateMap[userId] = Optional.of(userState)
        return userState
    }

    private fun defaultUserState() = UserState(
        elements = works.values.shuffled(),
        index = -1,
        mode = PicturesShowingMode.ALL
    )

    private fun constructPicture(userState: UserState): Picture {
        val work = userState.currentElement()
        val picture = picturesDao.getPicture(work)
        val artist = artists.getValue(work.author)
        return Picture(
            picture,
            constructTextAnswer(work, userState),
            work,
            artist
        )
    }

    private fun constructTextAnswer(work: WorkJson, userState: UserState): String {
        val counter = "${userState.index + 1}/${userState.elements.lastIndex + 1}"
        val artist = artists.getValue(work.author)
        return """
            ${work.nameRu}
            ${artist.name}, ${artist.genre}
            ${artist.years}
            ($counter)
        """.trimIndent()
    }

    private data class UserState(
        val elements: List<WorkJson>,
        var index: Int,
        val mode: PicturesShowingMode
    ) {
        fun isLastElement() = elements.lastIndex == index

        fun currentElement() = elements[max(index, 0)]
    }

    private companion object {
        private val picturesDao = PicturesDao()
    }
}