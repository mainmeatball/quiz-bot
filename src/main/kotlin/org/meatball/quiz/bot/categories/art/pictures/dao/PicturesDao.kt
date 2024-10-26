package org.meatball.quiz.bot.categories.art.pictures.dao

import kotlinx.serialization.json.Json
import org.meatball.quiz.bot.categories.art.pictures.entity.ArtistJson
import org.meatball.quiz.bot.categories.art.pictures.entity.WorkJson
import org.meatball.quiz.bot.config.PATH_TO_RESOURCES
import java.io.File

class PicturesDao {

    fun getWorks(): Map<String, WorkJson> {
        val picturesDirectory = File("$PICTURES_JSON_FILE_PATH/top100")

        return picturesDirectory.walkTopDown()
            .maxDepth(1)
            .filter { it.name != "top100" }
            .filter { it.isDirectory }
            .flatMap { readWorksInfo("top100/${it.name}") }
            .associateBy { it.id }
    }
    
    fun getArtists(): Map<String, ArtistJson> {
        val artistsFile = File("$PICTURES_JSON_FILE_PATH/artists.json")
        val artistJson = json.decodeFromString<List<ArtistJson>>(artistsFile.readText(Charsets.UTF_8))
        return artistJson.associateBy { it.code }
    }

    fun getPicture(pictureInfo: WorkJson): File {
        return File("$PICTURES_JSON_FILE_PATH/top100/${pictureInfo.author}/${pictureInfo.filename}")
    }

    private fun readWorksInfo(dirName: String): List<WorkJson> {
        val worksInfo = File("$PICTURES_JSON_FILE_PATH/$dirName/works.json")
        if (worksInfo.length() < 10) {
            return emptyList()
        }
        val works = json.decodeFromString<List<WorkJson>>(worksInfo.readText(Charsets.UTF_8))
        return works
    }

    private companion object {
        private const val PICTURES_JSON_FILE_PATH = "${PATH_TO_RESOURCES}/pics"
        private val json = Json { ignoreUnknownKeys = true }
    }
}