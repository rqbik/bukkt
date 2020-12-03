package com.github.rqbik.bukkt.extensions.item

import com.destroystokyo.paper.MaterialTags
import org.bukkit.Keyed
import org.bukkit.Material
import org.bukkit.Tag
import org.bukkit.inventory.ItemStack

fun Material.stack(amount: Int = 1) =
    if (isItem) ItemStack(this, amount)
    else throw IllegalArgumentException("Can't convert unobtainable material to ItemStack")

val Material.stack get() = stack()

val Material.isArmor: Boolean get() =
    this in MaterialTags.BOOTS
    || this in MaterialTags.LEGGINGS
    || this in MaterialTags.CHESTPLATES
    || this in MaterialTags.HELMETS

operator fun  <T : Keyed> Tag<T>.contains(item: T) = isTagged(item)
