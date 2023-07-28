package Scenes

import com.github.quillraven.fleks.*
import korlibs.image.color.*
import korlibs.korge.annotations.*
import korlibs.korge.input.*
import korlibs.korge.scene.*
import korlibs.korge.view.*
import korlibs.korge.view.align.*
import korlibs.math.geom.*
import ui.*


class GameScene : Scene() {

    //private val assets = Assets()
    override suspend fun SContainer.sceneInit() {
        // load assets here and inject them into the world
    }

    @OptIn(KorgeExperimental::class)
    override suspend fun SContainer.sceneMain() {
        container {
            // the main playground for rendering tiles
            val playground = container {
                // add elements or playground will have no width or height. Width and height are determined by the children
                val background = solidRect(stage!!.width, stage!!.height, Colors.WHITE)
                text("Playground Area", color = Colors.BLACK)
            }

            // Makes playground container draggable only on y
            // TODO: add constraints
            playground.draggable(autoMove = false) {
                it.view.y = it.viewNextXY.y
            }

            // test topBar UI sections
            val topBar = topBar(parent!!)

            //align playground below topBar
            playground.alignTopToBottomOf(topBar)

            // shows local and global mouse position centered on the Y-Axis of the stage
            val mouseCoords = text("0.0", color = Colors.RED).centerYOnStage()
            mouseCoords.scale(3)
            mouseCoords.addUpdater {
                text = "mousepos \n Local: ${mouse.currentPosLocal} \n Global: ${mouse.currentPosGlobal}"
            }

            // This is the world object of the entity component system (ECS)
            // It contains all ECS related system and component configuration
            val world = configureWorld(entityCapacity = 512) {
                // Register external objects which are used by systems and component listeners
                injectables {
                    add("playground", playground)  // Currently, we use only one layer to draw all objects to
                }

                // Register component hooks which trigger actions when specific components are created
                components {

                }

                // Register family hooks which trigger actions when specific entities (combination of components) are created
                families {

                }

                // Register all needed systems of the entity component system
                // The order of systems here also define the order in which the systems are called inside Fleks ECS
                systems {

                    // Drawing images on screen should be last otherwise the position might be (0, 0) because it was not set before
                }
            }

            // Run the update of the Fleks ECS - this will periodically call all update functions of the systems (e.g. onTick(), onTickEntity(), etc.)
            addUpdater { dt ->
                world.update(dt.seconds.toFloat())
            }
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    override suspend fun sceneAfterInit() {
        // DO NOT BLOCK. Called after the old scene has been destroyed and the transition has been completed.
    }

    override suspend fun sceneBeforeLeaving() {
        // BLOCK. Called on the old scene after the new scene has been
        // initialized, and before the transition is performed.
    }

    override suspend fun sceneDestroy() {
        // BLOCK. Called on the old scene after the transition
        // has been performed, and the old scene is not visible anymore.
    }

    override suspend fun sceneAfterDestroy() {
        // DO NOT BLOCK. Called on the old scene after the transition has been performed, and the old scene is not visible anymore.
        //
        // At this stage the scene [coroutineContext] [Job] will be cancelled.
        // Stopping [sceneMain] and other [launch] methods using this scene [CoroutineScope].
    }

    override fun onSizeChanged(size: Size) {
        super.onSizeChanged(size)
        // Do something here if the scene size is changed
    }

}
