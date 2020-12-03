package com.github.rqbik.bukkt.extensions.entity

import org.bukkit.entity.LivingEntity

fun <T : LivingEntity> T.clearEffects() = activePotionEffects.forEach {
    removePotionEffect(it.type)
}