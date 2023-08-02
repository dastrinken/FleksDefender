package assets

import korlibs.image.atlas.*
import korlibs.io.file.std.*
import korlibs.time.*

class Assets {

    private val atlas: MutableMap<String, Atlas> = mutableMapOf()

    fun getAtlas(name: String): Atlas {
        return atlas[name] ?: throw RuntimeException("Atlas '$name' not found")
    }

    suspend fun load(config: Config) {
        val sw = Stopwatch().start()
        println("start resources loading...")
        atlas.clear()
        for ((name, imagePath) in config.images) {
            atlas[name] = resourcesVfs[imagePath].readAtlas()
        }
        println("loaded resources in ${sw.elapsed}")
    }

    /**
     * Data class which contains the config for loading assets.
     */
    data class Config(
        val images: List<Pair<String, String>> = listOf()
    )
}
