package components

import com.github.quillraven.fleks.*
import helper.*
import korlibs.korge.view.*
import korlibs.time.*
import systems.*

//@Serializable not serializable
data class SpriteComponent(
    var imagePath: String = "",
    var animations: MutableMap<Direction, SpriteAnimation> = mutableMapOf(),
    var currentAnimation: Sprite? = null,
    var currentDirection: Direction = Direction.DOWN,
) : Component<SpriteComponent> {
    override fun type(): ComponentType<SpriteComponent> = SpriteComponent

    companion object : ComponentType<SpriteComponent>() {
        val onComponentAdded: ComponentHook<SpriteComponent> = { entity, component ->
            val playground = inject<Container>("playground")
            val atlasLoader = inject<AtlasLoader>()
            val atlas = atlasLoader.getAtlas(component.imagePath)

            // Load animations for the given atlas
            component.animations[Direction.DOWN] = atlas.getSpriteAnimation(prefix = "Walk#Down", 100.milliseconds)
            component.animations[Direction.UP] = atlas.getSpriteAnimation(prefix = "Walk#Up", 100.milliseconds)
            component.animations[Direction.LEFT] = atlas.getSpriteAnimation(prefix = "Walk#Side", 100.milliseconds) // TODO: Flip images programmatically or add additional row to the sprite-sheet
            component.animations[Direction.RIGHT] = atlas.getSpriteAnimation(prefix = "Walk#Side", 100.milliseconds)

            //initial animation
            val spriteAnim = Sprite(component.animations[Direction.DOWN]!!)
            spriteAnim.playAnimationLooped()
            spriteAnim.addTo(playground)
            component.currentAnimation = spriteAnim
        }
    }
}
