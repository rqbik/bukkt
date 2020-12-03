package com.github.rqbik.bukkt.extensions.entity

import org.bukkit.attribute.Attributable
import org.bukkit.entity.Damageable

fun <T : Damageable> T.kill() {
    health = 0.0
}

fun <T> T.heal(amount: Double) where T : Damageable, T : Attributable {
    health = minOf(maximumHealth, amount + health)
}

fun <T> T.heal() where T : Damageable, T : Attributable {
    health = maximumHealth
}
