package com.github.rqbik.bukkt.extensions.inventory

import com.github.rqbik.bukkt.extensions.item.stack
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

operator fun Inventory.get(index: Int) = getItem(index)

operator fun Inventory.set(index: Int, item: ItemStack?) = setItem(index, item)
operator fun Inventory.set(index: Int, item: Material?) = setItem(index, item?.stack)

val InventoryType.width: Int
    get() = when (this) {
        InventoryType.CHEST, InventoryType.PLAYER, InventoryType.ENDER_CHEST, InventoryType.SHULKER_BOX, InventoryType.BARREL -> 9
        InventoryType.DISPENSER, InventoryType.DROPPER, InventoryType.WORKBENCH, InventoryType.CRAFTING, InventoryType.ANVIL -> 3
        InventoryType.HOPPER -> 5
        else -> 1
    }

fun InventoryType.getPosition(pos: Int): Pair<Int, Int> {
    val y = pos / width
    val x = pos - y * width
    return x + 1 to y + 1
}

fun InventoryType.getIndex(x: Int, y: Int) = (y - 1) * width + (x - 1)

val InventoryHolder.firstEmpty: Int
    get() = inventory.firstEmpty()

val Inventory.full: Boolean
    get() = contents.all { it != null && it.type != Material.AIR }

fun Inventory.hasSpace(
    item: ItemStack,
    amount: Int = item.amount
) = spaceOf(item) >= amount

fun Inventory.spaceOf(item: ItemStack): Int =
    contents.filterNotNull().map {
        if (it.amount < it.maxStackSize && it.isSimilar(item))
            it.maxStackSize - it.amount
        else 0
    }.count()