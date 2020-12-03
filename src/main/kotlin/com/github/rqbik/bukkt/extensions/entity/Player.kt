package com.github.rqbik.bukkt.extensions.entity

import com.github.rqbik.bukkt.extensions.item.stack
import org.bukkit.*
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory

fun PlayerInventory.clearArmor() = setArmorContents(arrayOf<ItemStack?>(null, null, null, null))

fun PlayerInventory.clearAll() {
    clear()
    clearArmor()
}

fun Player.give(item: ItemStack) = inventory.addItem(item)
fun Player.give(item: Material) = inventory.addItem(item.stack)
fun Player.give(item: Material, amount: Int) = give(item.stack(amount))

fun Player.clear(all: Boolean = true) = if (all) inventory.clearAll() else inventory.clear()
fun Player.clear(item: Material) = inventory.remove(item)

fun Player.clear(items: List<Material>) = items.forEach { clear(it) }

@JvmName("clear0")
fun Player.clear(items: List<ItemStack>) = items.forEach { clear(it.type) }

fun CommandSender.sendMessage(messages: List<String>) = messages.forEach { sendMessage(it) }
fun CommandSender.sendMessage(vararg messages: String) = sendMessage(messages)

fun Player.playSound(
    sound: Sound,
    pitch: Number = 1.0f,
    volume: Number = Float.MAX_VALUE,
    category: SoundCategory = SoundCategory.MASTER,
    location: Location = this.location
) = playSound(location, sound, category, volume.toFloat(), pitch.toFloat())

fun Player.spawnParticle(location: Location, particle: Particle, count: Int = 1) =
    spawnParticle(particle, location, count)

fun Player.clearTitle() =
    sendTitle(" ", " ", 0, 20, 0)

fun Player.feed() {
    saturation = 20.0f
    foodLevel = 20
}

fun Player.resetWalkSpeed() {
    walkSpeed = 0.2f
}

fun Player.resetFlySpeed() {
    flySpeed = 0.1f
}

var Player.fly: Boolean get() = isFlying
    set(value) {
        isFlying = value
        allowFlight = value
    }
