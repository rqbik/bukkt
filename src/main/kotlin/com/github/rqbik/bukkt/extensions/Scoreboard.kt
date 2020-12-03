package com.github.rqbik.bukkt.extensions

import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard

fun Scoreboard.getOrRegisterTeam(name: String) = getTeam(name) ?: registerNewTeam(name)

val Objective.unregistered get() = scoreboard == null
