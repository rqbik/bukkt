package com.github.rqbik.bukkt.extensions.vecloc

import org.bukkit.Location
import org.bukkit.util.BoundingBox
import org.bukkit.util.Vector

operator fun Vector.component1() = x
operator fun Vector.component2() = y
operator fun Vector.component3() = z

operator fun Vector.plus(vector: Vector) = add(vector)
operator fun Vector.plus(other: Location) = add(other.toVector())

operator fun Vector.minus(vector: Vector) = subtract(vector)

operator fun Vector.rangeTo(other: Vector) = BoundingBox(x, y, z, other.x, other.y, other.z)
operator fun Vector.rangeTo(location: Location) = BoundingBox(x, y, z, location.x, location.y, location.z)

operator fun Vector.unaryMinus() = apply {
    x = -x
    z = -z
}

fun Vector.range(radius: Double) =
        BoundingBox(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius)

fun Vector.range(radius: Int) = range(radius.toDouble())

fun BoundingBox(
    x: ClosedFloatingPointRange<Double>,
    y: ClosedFloatingPointRange<Double>,
    z: ClosedFloatingPointRange<Double>
) =
        BoundingBox(x.start, y.start, z.start, x.endInclusive, y.endInclusive, z.endInclusive)