package systems

import com.github.quillraven.fleks.*
import com.github.quillraven.fleks.World.Companion.family
import components.*
import kotlin.math.*

/**
 * A system that displays sprites for entities.
 */
class SpriteSystem : IteratingSystem(
    family {
        all(SpriteComponent, TransformComponent)
        any(SpriteComponent, TransformComponent, VelocityComponent) // VelocityComponent is used to set the sprite based on the direction the entity is moving
    },
    interval = EachFrame
) {
    override fun onTickEntity(entity: Entity) {
        val transform = entity[TransformComponent]
        val sprite = entity[SpriteComponent]

        if(entity has VelocityComponent){
            val velocity = entity[VelocityComponent]
            // Check if the direction has changed
            val direction = when {
                abs(velocity.direction.x) > abs(velocity.direction.y) -> {
                    // Horizontal movement is dominant
                    if (velocity.direction.x > 0f) Direction.RIGHT else Direction.LEFT
                }
                abs(velocity.direction.y) > abs(velocity.direction.x) -> {
                    // Vertical movement is dominant
                    if (velocity.direction.y > 0f) Direction.DOWN else Direction.UP
                }
                else -> Direction.DOWN // Equal movement in both directions or no movement TODO:
            }

            // Check if the direction has changed
            if (sprite.currentDirection != direction) {
                sprite.currentDirection = direction

                // Update sprite animation based on the direction
                val animation = sprite.animations[direction]!!
                sprite.currentAnimation?.stopAnimation()
                sprite.currentAnimation?.playAnimationLooped(animation)
            }

        }

        // sync view position with entity position
        sprite.currentAnimation!!.x = transform.position.x
        sprite.currentAnimation!!.y = transform.position.y

    }
}

enum class Direction {
    LEFT, RIGHT, UP, DOWN
}
