package com.github.rqbik.bukkt.extensions.event

import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.block.BlockEvent
import org.bukkit.event.enchantment.EnchantItemEvent
import org.bukkit.event.enchantment.PrepareItemEnchantEvent
import org.bukkit.event.entity.*
import org.bukkit.event.hanging.HangingEvent
import org.bukkit.event.inventory.InventoryInteractEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.vehicle.VehicleEvent
import org.bukkit.inventory.ItemStack

val PlayerMoveEvent.moved: Boolean
    get() = from.x != to.x || from.y != to.y || from.z != to.z || from.world != to.world

val InventoryInteractEvent.player
    get() = whoClicked as Player

val PlayerInteractEvent.placedBlock: Block?
    get() = clickedBlock?.getRelative(blockFace)

val PlayerDeathEvent.player: Player
    get() = entity

var PlayerDropItemEvent.itemStack: ItemStack
    get() = itemDrop.itemStack
    set(value) {
        itemDrop.itemStack = value
    }

var ItemSpawnEvent.itemStack: ItemStack
    get() = entity.itemStack
    set(value) {
        entity.itemStack = value
    }

var ItemDespawnEvent.itemStack: ItemStack
    get() = entity.itemStack
    set(value) {
        entity.itemStack = value
    }

var ItemMergeEvent.itemStack: ItemStack
    get() = entity.itemStack
    set(value) {
        entity.itemStack = value
    }

val EntityDamageByEntityEvent.victim: Entity get() = entity

val BlockEvent.world: World get() = block.world
val EnchantItemEvent.world: World get() = enchanter.world
val PrepareItemEnchantEvent.world: World get() = enchanter.world
val EntityEvent.world: World get() = entity.world
val HangingEvent.world: World get() = entity.world
val InventoryInteractEvent.world: World get() = player.world
val PlayerEvent.world: World get() = player.world
val VehicleEvent.world: World get() = vehicle.world
