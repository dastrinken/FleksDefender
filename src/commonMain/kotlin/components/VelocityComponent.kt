package components

import com.github.quillraven.fleks.*
import helper.*
import kotlinx.serialization.*

@Serializable
data class VelocityComponent(
    var direction: Vector2D
) : Component<VelocityComponent> {
    override fun type(): ComponentType<VelocityComponent> = VelocityComponent
    companion object : ComponentType<VelocityComponent>()
}
