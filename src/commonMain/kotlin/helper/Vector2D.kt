package helper

import kotlinx.serialization.*

/**
 * Helper class to define mathematical 2D-Vectors which hold a x and y variable
 * @param x x-coordinate of the vector
 * @param y y-coordinate of the vector
 * */
@Serializable
data class Vector2D(var x: Float, var y: Float){
    constructor(x: Int, y: Int) : this(x.toFloat(), y.toFloat())
    constructor(x: Double, y: Double) : this(x.toFloat(), y.toFloat())

    operator fun plus(other: Vector2D) = Vector2D(x + other.x, y + other.y)
    operator fun minus(other: Vector2D) = Vector2D(x - other.x, y - other.y)
    operator fun times(other: Vector2D) = Vector2D(x * other.x, y * other.y)
    operator fun times(other: Float) = Vector2D(x * other, y * other)
    operator fun div(other: Vector2D) = Vector2D(x / other.x, y / other.y)
    operator fun div(other: Float) = Vector2D(x / other, y / other)
    operator fun rem(other: Vector2D) = Vector2D(x % other.x, y % other.y)
    operator fun inc() = Vector2D(x + 1, y + 1)
    operator fun dec() = Vector2D(x - 1, y - 1)
}
