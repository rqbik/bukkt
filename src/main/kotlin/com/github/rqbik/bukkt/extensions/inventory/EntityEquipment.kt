package com.github.rqbik.bukkt.extensions.inventory

import com.github.rqbik.bukkt.extensions.item.stack
import org.bukkit.Material
import org.bukkit.inventory.EntityEquipment

fun EntityEquipment.fill(type: EquipmentType) {
    helmet = when (type) {
        EquipmentType.NETHERITE -> Material.NETHERITE_HELMET
        EquipmentType.DIAMOND -> Material.DIAMOND_HELMET
        EquipmentType.IRON -> Material.IRON_HELMET
        EquipmentType.CHAINMAIL -> Material.CHAINMAIL_HELMET
        EquipmentType.GOLDEN -> Material.GOLDEN_HELMET
        EquipmentType.LEATHER -> Material.LEATHER_HELMET
    }.stack

    chestplate = when (type) {
        EquipmentType.NETHERITE -> Material.NETHERITE_CHESTPLATE
        EquipmentType.DIAMOND -> Material.DIAMOND_CHESTPLATE
        EquipmentType.IRON -> Material.IRON_CHESTPLATE
        EquipmentType.CHAINMAIL -> Material.CHAINMAIL_CHESTPLATE
        EquipmentType.GOLDEN -> Material.GOLDEN_CHESTPLATE
        EquipmentType.LEATHER -> Material.LEATHER_CHESTPLATE
    }.stack

    leggings = when (type) {
        EquipmentType.NETHERITE -> Material.NETHERITE_LEGGINGS
        EquipmentType.DIAMOND -> Material.DIAMOND_LEGGINGS
        EquipmentType.IRON -> Material.IRON_LEGGINGS
        EquipmentType.CHAINMAIL -> Material.CHAINMAIL_LEGGINGS
        EquipmentType.GOLDEN -> Material.GOLDEN_LEGGINGS
        EquipmentType.LEATHER -> Material.LEATHER_LEGGINGS
    }.stack

    boots = when (type) {
        EquipmentType.NETHERITE -> Material.NETHERITE_BOOTS
        EquipmentType.DIAMOND -> Material.DIAMOND_BOOTS
        EquipmentType.IRON -> Material.IRON_BOOTS
        EquipmentType.CHAINMAIL -> Material.CHAINMAIL_BOOTS
        EquipmentType.GOLDEN -> Material.GOLDEN_BOOTS
        EquipmentType.LEATHER -> Material.LEATHER_BOOTS
    }.stack
}

enum class EquipmentType {
    NETHERITE,
    DIAMOND,
    IRON,
    CHAINMAIL,
    GOLDEN,
    LEATHER
}
