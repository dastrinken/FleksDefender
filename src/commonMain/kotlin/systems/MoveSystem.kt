package systems

import com.github.quillraven.fleks.*
import com.github.quillraven.fleks.World.Companion.family
import components.*

/**
 * A system that moves entities.
 */
class MoveSystem : IteratingSystem(
    family {
        all(TransformComponent, VelocityComponent)
    },
    interval = EachFrame
) {
    override fun onTickEntity(entity: Entity) {
        val transform = entity[TransformComponent]
        val velocity = entity[VelocityComponent]
        //Update the position
        transform.position += velocity.direction * deltaTime
    }
}


