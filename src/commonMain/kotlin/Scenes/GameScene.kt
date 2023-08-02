package scenes

import assets.*
import com.github.quillraven.fleks.*
import components.*
import helper.*
import korlibs.korge.scene.*
import korlibs.korge.view.*
import korlibs.math.geom.*
import systems.*

class GameScene : Scene() {
    private val assets = Assets()
    override suspend fun SContainer.sceneInit() {
        // load assets here and inject them into the world in the injectables section inside the world down below
        // create a config for all assets that have to be loaded
        val config = Assets.Config(
            images = listOf(
                Pair("Firebug", "Firebug.json")
            )
        )
        //load assets with config
        assets.load(config)
    }

    override suspend fun SContainer.sceneMain() {
        container {
            // Here are the container views which contain the generated entity objects with visible component "Sprite" attached to it
            val playground = container()


            // This is the world object of the entity component system (ECS)
            // It contains all ECS related system and component configuration
            val world = configureWorld(entityCapacity = 512) {
                // Register external objects which are used by systems and component listeners
                injectables {
                    add(assets)
                    add("playground", playground)
                }

                // Register component hooks which trigger actions when specific components are created
                components {
                    onAdd(SpriteComponent, SpriteComponent.onComponentAdded)
                }

                // Register family hooks which trigger actions when specific entities (combination of components) are created
                families {

                }

                // Register all needed systems of the entity component system
                // The order of systems here also define the order in which the systems are called inside Fleks ECS
                systems {
                    add(MoveSystem())
                    add(SpriteSystem())
                    // Drawing images on screen should be last otherwise the position might be (0, 0) because it was not set before
                }
            }

            // Enemy creation with fixed velocity
            world.entity {
                it += TransformComponent(
                    position = Vector2D(300f, 200f)
                )
                it += SpriteComponent(
                    imagePath = "Firebug"
                )
                it += VelocityComponent(
                    direction = Vector2D(0f, 10f) //hardcoded velocity for now
                )
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
