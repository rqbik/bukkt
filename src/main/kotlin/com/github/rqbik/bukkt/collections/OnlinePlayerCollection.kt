package com.github.rqbik.bukkt.collections

import com.github.rqbik.bukkt.extensions.event.PluginBound
import com.github.rqbik.bukkt.extensions.event.PluginBoundListener
import com.github.rqbik.bukkt.extensions.event.listen
import com.github.rqbik.bukkt.extensions.event.unregister
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerKickEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.Plugin
import java.util.*

/**
 * Author: DevSrSouza
 * Modified by: rqbik
 */

typealias QuitCollectionCallback = Player.() -> Unit
typealias QuitMapCallback<V> = Player.(V) -> Unit

// List

fun Plugin.onlinePlayerListOf() = OnlinePlayerList(this)

fun PluginBound.onlinePlayerListOf() = plugin.onlinePlayerListOf()

fun onlinePlayerListOf(vararg players: Player, plugin: Plugin)
        = OnlinePlayerList(plugin).apply { addAll(players) }

fun Plugin.onlinePlayerListOf(vararg players: Player)
        = onlinePlayerListOf(*players, plugin = this)

fun PluginBound.onlinePlayerListOf(vararg players: Player)
        = plugin.onlinePlayerListOf(*players)

fun onlinePlayerListOf(vararg pair: Pair<Player, QuitCollectionCallback>, plugin: Plugin)
        = OnlinePlayerList(plugin).apply { pair.forEach { (player, whenPlayerQuit) -> add(player, whenPlayerQuit) } }

fun Plugin.onlinePlayerListOf(vararg pair: Pair<Player, QuitCollectionCallback>)
        = onlinePlayerListOf(*pair, plugin = this)

fun PluginBound.onlinePlayerListOf(vararg pair: Pair<Player, QuitCollectionCallback>)
        = plugin.onlinePlayerListOf(*pair)

// Set

fun Plugin.onlinePlayerSetOf() = OnlinePlayerSet(this)

fun PluginBound.onlinePlayerSetOf() = plugin.onlinePlayerSetOf()

fun onlinePlayerSetOf(vararg players: Player, plugin: Plugin)
        = OnlinePlayerSet(plugin).apply { addAll(players) }

fun Plugin.onlinePlayerSetOf(vararg players: Player)
        = onlinePlayerSetOf(*players, plugin = this)

fun PluginBound.onlinePlayerSetOf(vararg players: Player)
        = plugin.onlinePlayerSetOf(*players)

fun onlinePlayerSetOf(vararg pair: Pair<Player, QuitCollectionCallback>, plugin: Plugin)
        = OnlinePlayerSet(plugin).apply { pair.forEach { (player, whenPlayerQuit) -> add(player, whenPlayerQuit) } }

fun Plugin.onlinePlayerSetOf(vararg pair: Pair<Player, QuitCollectionCallback>)
        = onlinePlayerSetOf(*pair, plugin = this)

fun PluginBound.onlinePlayerSetOf(vararg pair: Pair<Player, QuitCollectionCallback>)
        = plugin.onlinePlayerSetOf(*pair)

// Map

fun <V> Plugin.onlinePlayerMapOf() = OnlinePlayerMap<V>(this)

fun <V> PluginBound.onlinePlayerMapOf() = plugin.onlinePlayerMapOf<V>()

fun <V> onlinePlayerMapOf(vararg pair: Pair<Player, V>, plugin: Plugin)
        = OnlinePlayerMap<V>(plugin).apply { putAll(pair) }

fun <V> Plugin.onlinePlayerMapOf(vararg pair: Pair<Player, V>)
        = onlinePlayerMapOf(*pair, plugin = this)

fun <V> PluginBound.onlinePlayerMapOf(vararg pair: Pair<Player, V>)
        = plugin.onlinePlayerMapOf(*pair)

fun <V> onlinePlayerMapOf(vararg triple: Triple<Player, V, QuitMapCallback<V>>, plugin: Plugin)
        = OnlinePlayerMap<V>(plugin).apply { triple.forEach { (player, value, whenPlayerQuit) -> put(player, value, whenPlayerQuit) } }

fun <V> Plugin.onlinePlayerMapOf(vararg triple: Triple<Player, V, QuitMapCallback<V>>)
        = onlinePlayerMapOf(*triple, plugin = this)

fun <V> PluginBound.onlinePlayerMapOf(vararg triple: Triple<Player, V, QuitMapCallback<V>>)
        = plugin.onlinePlayerMapOf(*triple)

class OnlinePlayerList(override val plugin: Plugin) : LinkedList<Player>(), OnlinePlayerCollection {
    private val whenQuits: MutableList<Pair<Player, QuitCollectionCallback>> = LinkedList()

    override fun add(player: Player, whenPlayerQuits: Player.() -> Unit): Boolean {
        return if (super<OnlinePlayerCollection>.add(player, whenPlayerQuits)) {
            whenQuits.add(player to whenPlayerQuits)
            true
        } else false
    }

    override fun quit(player: Player): Boolean {
        return if (super.quit(player)) {
            val iterator = whenQuits.iterator()
            for (pair in iterator) {
                if (pair.first == player) {
                    iterator.remove()
                    pair.second.invoke(pair.first)
                }
            }
            true
        } else false
    }

    override fun removeFirst(): Player {
        return super.removeFirst().also {
            checkSize()
        }
    }

    override fun removeLastOccurrence(p0: Any?): Boolean {
        return super.removeLastOccurrence(p0).also {
            if (it) checkSize()
        }
    }

    override fun removeAt(p0: Int): Player {
        return super.removeAt(p0).also {
            checkSize()
        }
    }

    override fun remove(element: Player): Boolean {
        return if (super.remove(element)) {
            checkSize()
            true
        } else false
    }

    override fun removeLast(): Player {
        return super.removeLast().also {
            checkSize()
        }
    }

    private var listening = false

    override fun checkSize() =
        when (size) {
            1 -> initialize()
            0 -> destroy()
            else -> {}
        }

    override fun initialize() {
        if (listening) return
        listen<PlayerQuitEvent> { quit(player) }
        listen<PlayerKickEvent> { quit(player) }
    }

    override fun destroy() {
        if (listening) unregister()
    }
}

class OnlinePlayerSet(override val plugin: Plugin) : HashSet<Player>(), OnlinePlayerCollection {
    private val whenQuit: MutableMap<Player, QuitCollectionCallback> = mutableMapOf()

    override fun add(player: Player, whenPlayerQuits: QuitCollectionCallback): Boolean {
        return if (super<OnlinePlayerCollection>.add(player, whenPlayerQuits)) {
            whenQuit[player] = whenPlayerQuits

            checkSize()
            true
        } else false
    }

    override fun add(element: Player): Boolean {
        return super<HashSet>.add(element).also {
            if (it) checkSize()
        }
    }

    override fun remove(element: Player): Boolean = super.quit(element)

    override fun quit(player: Player): Boolean {
        return if (super.quit(player)) {
            whenQuit.remove(player)?.also { block ->
                block.invoke(player)
            }
            true
        } else false
    }

    private var listening = false

    override fun checkSize() =
        when (size) {
            1 -> initialize()
            0 -> destroy()
            else -> {}
        }

    override fun initialize() {
        if (listening) return
        listen<PlayerQuitEvent> { quit(player) }
        listen<PlayerKickEvent> { quit(player) }
    }

    override fun destroy() {
        if (listening) unregister()
    }
}

interface OnlinePlayerCollection : MutableCollection<Player>, PluginBoundListener {
    /**
     * Adds a new Player to the collection with a callback for when the player quits the server.
     */
    fun add(player: Player, whenPlayerQuits: QuitCollectionCallback): Boolean {
        return add(player).also {
            if (it) checkSize()
        }
    }

    /**
     * Removes the player from the collection, calling the [QuitCollectionCallback] provided.
     */
    fun quit(player: Player): Boolean {
        return remove(player).also {
            if (it) checkSize()
        }
    }

    /**
     * Clear the collection calling all [QuitCollectionCallback] from the Players.
     */
    fun clearQuitting() {
        toMutableList().forEach {
            quit(it)
        }
    }

    fun checkSize()
    fun initialize()
    fun destroy()
}


class OnlinePlayerMap<V>(override val plugin: Plugin) : HashMap<Player, V>(), PluginBoundListener {
    private val whenQuits: HashMap<Player, QuitMapCallback<V>> = hashMapOf()

    /**
     * Puts a Player to the map with a [value] and a callback for when the player quits the server.
     */
    fun put(key: Player, value: V, whenPlayerQuits: QuitMapCallback<V>): V? {
        whenQuits[key] = whenPlayerQuits
        return put(key, value).also {
            checkRegistration()
        }
    }

    /**
     * Removes the player from the map, calling the [QuitMapCallback] provided.
     */
    fun quit(player: Player) {
        remove(player)?.also {
            whenQuits.remove(player)?.also { block ->
                block.invoke(player, it)
            }
            checkRegistration()
        }
    }

    /**
     * Clear the map calling all [QuitMapCallback] from the Players.
     */
    fun clearQuitting() {
        keys.toMutableList().forEach {
            quit(it)
        }
    }

    override fun remove(key: Player): V? {
        return super.remove(key).also {
            checkRegistration()
        }
    }

    override fun remove(key: Player, value: V): Boolean {
        return super.remove(key, value).also {
            checkRegistration()
        }
    }

    private var listening = false

    private fun checkRegistration() {
        if (size == 1 && !listening) {
            listening = true
            listen<PlayerQuitEvent> { quit(player) }
            listen<PlayerKickEvent> { quit(player) }
        } else if (size == 0) unregister()
    }
}