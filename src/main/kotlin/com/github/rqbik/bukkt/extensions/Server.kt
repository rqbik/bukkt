package com.github.rqbik.bukkt.extensions

import org.bukkit.Bukkit
import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitScheduler

val server: Server
    get() = Bukkit.getServer()

val Server.players: Collection<Player> get() = server.onlinePlayers

var Server.whitelist: Boolean
    get() = hasWhitelist()
    set(value) = setWhitelist(value)

val isPrimaryThread: Boolean
    get() = server.isPrimaryThread

val scheduler: BukkitScheduler
    get() = server.scheduler

fun Server.broadcastMessage(message: Player.() -> String) =
    server.players.forEach { it.sendMessage(message(it)) }
