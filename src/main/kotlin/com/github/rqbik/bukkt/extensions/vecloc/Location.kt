package com.github.rqbik.bukkt.extensions.vecloc

import org.bukkit.Location
import org.bukkit.util.BoundingBox
import org.bukkit.util.Vector
import kotlin.math.floor

operator fun Location.component1() = x
operator fun Location.component2() = y
operator fun Location.component3() = z
operator fun Location.component4() = yaw
operator fun Location.component5() = pitch

val Location.centered get() = clone().apply {
    x = floor(x) + 0.5
    z = floor(z) + 0.5
}

operator fun Location.plus(vector: Vector) = add(vector)
operator fun Location.plus(other: Location) = add(other)

operator fun Location.minus(vector: Vector) = subtract(vector)
operator fun Location.minus(other: Location) = subtract(other)

operator fun Location.rangeTo(other: Location) = BoundingBox(x, y, z, other.x, other.y, other.z)
operator fun Location.rangeTo(vector: Vector) = BoundingBox(x, y, z, vector.x, vector.y, vector.z)

operator fun BoundingBox.contains(location: Location) = contains(location.toVector())

operator fun Location.unaryMinus() = apply {
    x = -x
    z = -z
}

fun Location.range(radius: Double) = toVector().range(radius)
fun Location.range(radius: Int) = range(radius.toDouble())
