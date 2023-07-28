import Scenes.*
import korlibs.image.color.*
import korlibs.korge.*
import korlibs.korge.scene.*
import korlibs.math.geom.*
import korlibs.render.*

suspend fun main() = Korge(
        virtualSize = Size(720f, 1080f),
        //windowSize = Size(720f, 1080f),
    backgroundColor = Colors["#0c0f2b"],
    displayMode = KorgeDisplayMode(
        scaleMode = ScaleMode.FIT,
        scaleAnchor = Anchor.MIDDLE_CENTER,
        clipBorders = true
    ),
    quality = GameWindow.Quality.AUTOMATIC,
    forceRenderEveryFrame = true,
    title = "Crawlers Keep",
    gameId = "Crawlers Keep"

){


    injector.apply {
        mapSingleton { GameScene() }
    }

    sceneContainer{
        changeTo<GameScene>()
    }
}


