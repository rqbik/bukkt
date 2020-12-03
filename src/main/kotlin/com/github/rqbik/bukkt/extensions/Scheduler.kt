package com.github.rqbik.bukkt.extensions

import com.github.rqbik.bukkt.extensions.time.Time
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitScheduler
import org.bukkit.scheduler.BukkitTask
import java.util.function.Consumer

fun BukkitScheduler.runTaskLater(plugin: Plugin, delay: Long, task: () -> Unit) =
        runTaskLater(plugin, task, delay)

fun BukkitScheduler.runTaskTimer(plugin: Plugin, delay: Long, period: Long, task: () -> Unit) =
        runTaskTimer(plugin, task, delay, period)

fun BukkitScheduler.runTaskLater(plugin: Plugin, task: Runnable, delay: Time) =
        runTaskLater(plugin, task, delay.asTicks().toLong())

fun BukkitScheduler.runTaskLater(plugin: Plugin, task: Consumer<BukkitTask>, delay: Time) =
        runTaskLater(plugin, task, delay.asTicks().toLong())

fun BukkitScheduler.runTaskLater(plugin: Plugin, delay: Time, task: () -> Unit) =
        runTaskLater(plugin, delay.asTicks().toLong(), task)

fun BukkitScheduler.runTaskTimer(plugin: Plugin, task: Runnable, delay: Time, period: Time) =
        runTaskTimer(plugin, task, delay.asTicks().toLong(), period.asTicks().toLong())

fun BukkitScheduler.runTaskTimer(plugin: Plugin, task: Consumer<BukkitTask>, delay: Time, period: Time) =
        runTaskTimer(plugin, task, delay.asTicks().toLong(),  period.asTicks().toLong())

fun BukkitScheduler.runTaskTimer(plugin: Plugin, delay: Time, period: Time, task: () -> Unit) =
        runTaskTimer(plugin, delay.asTicks().toLong(),  period.asTicks().toLong(), task)