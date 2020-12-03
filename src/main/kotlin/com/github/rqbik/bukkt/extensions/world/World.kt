package com.github.rqbik.bukkt.extensions.world

import com.github.rqbik.bukkt.extensions.server
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.entity.Entity

fun WorldCreator.create(): World? = server.createWorld(this)

inline fun <reified T : Entity> World.getEntitiesByClass(): Collection<T> = getEntitiesByClass(T::class.java)

inline fun <reified T : Entity> World.spawnEntity(location: Location) = spawn(location, T::class.java)
inline fun <reified T : Entity> World.spawn(location: Location) = spawnEntity<T>(location) // Alias

fun World.unload(save: Boolean = true) = Bukkit.unloadWorld(this, save)
