package com.github.rqbik.bukkt.extensions.entity

import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Will be replaced with the function in the future
 */
val Entity.isPlayer: Boolean
    get() = type === EntityType.PLAYER

/**
 * Experimental function that contracts type to `Player`
 */
@JvmName("isPlayerExp")
@ExperimentalContracts
fun Entity.isPlayer(): Boolean {
    contract { returns(true) implies (this@isPlayer is Player) }
    return type == EntityType.PLAYER
}