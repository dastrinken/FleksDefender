import Scenes.*
import korlibs.image.color.*
import korlibs.korge.*
import korlibs.korge.scene.*
import korlibs.math.geom.*
import korlibs.render.*

suspend fun main() = Korge(
    virtualSize = Size(1080, 1920),
    windowSize = Size(720, 1080),
    backgroundColor = Colors["#2b2b2b"],
    displayMode = KorgeDisplayMode(
        scaleMode = ScaleMode.FIT,
        scaleAnchor = Anchor.MIDDLE_CENTER,
        clipBorders = true
    ),
    quality = GameWindow.Quality.AUTOMATIC,
    forceRenderEveryFrame = true
){

    injector.apply {
        mapSingleton { RootScene() }
    }

    sceneContainer{
        changeTo<RootScene>()
    }
}


