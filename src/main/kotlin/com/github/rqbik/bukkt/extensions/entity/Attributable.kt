package com.github.rqbik.bukkt.extensions.entity

import org.bukkit.attribute.Attributable
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Damageable

/**
 * https://minecraft.gamepedia.com/Attribute
 */

/**
 * Maximum health of an Entity.
 */
var <T : Attributable> T.maximumHealth  get() = getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value
    set(value) {
        if (value != null) getAttribute(Attribute.GENERIC_MAX_HEALTH)?.apply {
            baseValue = value
        }
    }

/**
 * Bukkit JavaDocs suggest that all `Damageable` entities have a GENERIC_MAX_HEALTH attribute.
 * Not sure if all of them do though.
 */
var <T> T.maximumHealth where T : Attributable, T : Damageable
    get() = getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
    set(value) {
        getAttribute(Attribute.GENERIC_MAX_HEALTH)?.apply {
            baseValue = value
        }
    }

/**
 * Range at which an Entity will follow others.
 */
var <T : Attributable> T.followRange  get() = getAttribute(Attribute.GENERIC_FOLLOW_RANGE)?.value
    set(value) {
        if (value != null) getAttribute(Attribute.GENERIC_FOLLOW_RANGE)?.apply {
            baseValue = value
        }
    }

/**
 * Resistance of an Entity to knockback.
 */
var <T : Attributable> T.knockbackResistance  get() = getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)?.value
    set(value) {
        if (value != null) getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)?.apply {
            baseValue = value
        }
    }

/**
 * Movement speed of an Entity.
 */
var <T : Attributable> T.movementSpeed  get() = getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)?.value
    set(value) {
        if (value != null) getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)?.apply {
            baseValue = value
        }
    }

/**
 * Flying speed of an Entity.
 * Applicable only to bees or parrots ([source](https://minecraft.gamepedia.com/Attribute))
 */
var <T : Attributable> T.flyingSpeed get() = getAttribute(Attribute.GENERIC_FLYING_SPEED)?.value
    set(value) {
        if (value != null) getAttribute(Attribute.GENERIC_FLYING_SPEED)?.apply {
            baseValue = value
        }
    }

/**
 * Attack damage of an Entity.
 */
var <T : Attributable> T.attackDamage  get() = getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)?.value
    set(value) {
        if (value != null) getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)?.apply {
            baseValue = value
        }
    }

/**
 * Attack knockback of an Entity.
 */
var <T : Attributable> T.attackKnockback  get() = getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK)?.value
    set(value) {
        if (value != null) getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK)?.apply {
            baseValue = value
        }
    }

/**
 * Attack speed of an Entity.
 * Applicable only to players ([source](https://minecraft.gamepedia.com/Attribute))
 */
var <T : Attributable> T.attackSpeed  get() = getAttribute(Attribute.GENERIC_ATTACK_SPEED)?.value
    set(value) {
        if (value != null) getAttribute(Attribute.GENERIC_ATTACK_SPEED)?.apply {
            baseValue = value
        }
    }

/**
 * Armor bonus of an Entity.
 */
var <T : Attributable> T.armorBonus  get() = getAttribute(Attribute.GENERIC_ARMOR)?.value
    set(value) {
        if (value != null) getAttribute(Attribute.GENERIC_ARMOR)?.apply {
            baseValue = value
        }
    }

/**
 * Armor durability bonus of an Entity.
 */
var <T : Attributable> T.armorToughness  get() = getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)?.value
    set(value) {
        if (value != null) getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)?.apply {
            baseValue = value
        }
    }

/**
 * Luck bonus of a Player.
 * Applicable only to players ([source](https://minecraft.gamepedia.com/Attribute))
 */
var <T : Attributable> T.luck get() = getAttribute(Attribute.GENERIC_LUCK)?.value
    set(value) {
        if (value != null) getAttribute(Attribute.GENERIC_LUCK)?.apply {
            baseValue = value
        }
    }

/**
 * Chance of a zombie to spawn reinforcements.
 * Applicable only to zombies ([source](https://minecraft.gamepedia.com/Attribute))
 */
var <T : Attributable> T.reinforcementsChance get() = getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS)?.value
    set(value) {
        if (value != null) getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS)?.apply {
            baseValue = value
        }
    }

fun <T : Attributable> T.resetAttribute(attribute: Attribute) : T = apply {
    val attributeInstance = getAttribute(attribute) ?: return this
    attributeInstance.baseValue = attributeInstance.defaultValue
}