package org.meatball.quiz.bot.categories.art.pictures.dao

import kotlinx.serialization.json.Json
import org.meatball.quiz.bot.categories.art.pictures.entity.ArtistJson
import org.meatball.quiz.bot.categories.art.pictures.entity.WorkJson
import org.meatball.quiz.bot.config.PATH_TO_RESOURCES
import java.io.File

class PicturesDao {

    fun getArtistsWithWorks(): Map<String, Pair<ArtistJson, List<WorkJson>>> {
        val picturesDirectory = File(PICTURES_JSON_FILE_PATH)

        return picturesDirectory.walkTopDown()
            .maxDepth(1)
            .filterNot { it.name == "pictures" }
            .filter { it.isDirectory }
            .associate {
                val artistInfo = readArtistInfo(it.name)
                val worksInfo = readWorksInfo(it.name)
                it.name to Pair(artistInfo, worksInfo)
            }
    }

    fun getPicture(artist: String, pictureName: Int): File {
        return File("$PICTURES_JSON_FILE_PATH/$artist/images/$pictureName.jpg")
    }

    private fun readArtistInfo(dirName: String): ArtistJson {
        val artistFile = File("$PICTURES_JSON_FILE_PATH/$dirName/artist.json")
        val artistJson = json.decodeFromString<ArtistJson>(artistFile.readText(Charsets.UTF_16))
        return artistJson
    }

    private fun readWorksInfo(dirName: String): List<WorkJson> {
        val worksInfo = File("$PICTURES_JSON_FILE_PATH/$dirName/works.json")
        val works = json.decodeFromString<List<WorkJson>>(worksInfo.readText(Charsets.UTF_16))
        return works
    }

    private companion object {
        private const val PICTURES_JSON_FILE_PATH = "${PATH_TO_RESOURCES}/pictures"
        private val json = Json { ignoreUnknownKeys = true }
    }
}