package com.github.rqbik.bukkt.extensions

import net.md_5.bungee.api.ChatColor

fun String.padCenter(
        length: Int,
        spacer: String = " ",
        prefix: String = "",
        suffix: String = ""
): String {
    if (this.length >= length) return this
    val part = prefix + spacer.repeat((length - this.length) / 2) + suffix
    return part + this + part
}

fun String.replace(vararg placeholders: Pair<String, Any>): String =
    placeholders.fold(this) { acc, (key, value) ->
        acc.replace(key, value.toString())
    }

fun String.colorize(altColorChar: Char = '&', reset: Boolean = false): String =
        (if (reset) ChatColor.RESET.toString() else "") +
        ChatColor.translateAlternateColorCodes(altColorChar, this)

fun String.discolor(): String = ChatColor.stripColor(this)
