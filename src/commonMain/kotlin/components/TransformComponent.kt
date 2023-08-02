package components

import com.github.quillraven.fleks.*
import helper.*
import kotlinx.serialization.*

@Serializable
data class TransformComponent(
    var position: Vector2D
) : Component<TransformComponent> {
    override fun type(): ComponentType<TransformComponent> = TransformComponent
    companion object : ComponentType<TransformComponent>()
}
