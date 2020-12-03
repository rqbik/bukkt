package com.github.rqbik.bukkt.extensions.event

import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import kotlin.reflect.KClass

inline fun <reified T : Event> PluginBoundListener.listen(
    priority: EventPriority = EventPriority.NORMAL,
    ignoreCancelled: Boolean = false,
    noinline block: T.() -> Unit
) {
    listen(plugin, T::class, priority, ignoreCancelled, block)
}

inline fun <reified T : Event> Listener.listen(
    plugin: Plugin,
    priority: EventPriority = EventPriority.NORMAL,
    ignoreCancelled: Boolean = false,
    noinline block: T.() -> Unit
) {
    listen(plugin, T::class, priority, ignoreCancelled, block)
}

inline fun <reified T : Event> Listener.listen(
    plugin: Plugin,
    type: KClass<T>,
    priority: EventPriority = EventPriority.NORMAL,
    ignoreCancelled: Boolean = false,
    crossinline block: T.() -> Unit
) {
    Bukkit.getServer().pluginManager.registerEvent(
        type.java,
        this,
        priority,
        { _, event ->
            if(type.isInstance(event))
                (event as? T)?.block()
        },
        plugin,
        ignoreCancelled
    )
}

fun Listener.register(plugin: Plugin)
        = plugin.server.pluginManager.registerEvents(this, plugin)

fun Listener.unregister() = HandlerList.unregisterAll(this)

inline fun Plugin.events(block: PluginBoundListener.() -> Unit) =
    EventListener(this).apply(block)

inline fun Plugin.events(listener: PluginBoundListener, block: PluginBoundListener.() -> Unit) =
        listener.apply(block)

interface PluginBound {
    val plugin: Plugin
}

interface PluginBoundListener : PluginBound, Listener

class EventListener(override val plugin: Plugin) : PluginBoundListener
