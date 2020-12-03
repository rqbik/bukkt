package com.github.rqbik.bukkt.extensions

import net.md_5.bungee.api.chat.*
import net.md_5.bungee.api.chat.hover.content.Entity
import net.md_5.bungee.api.chat.hover.content.Item
import net.md_5.bungee.api.chat.hover.content.Text
import org.bukkit.Material
import org.bukkit.entity.EntityType

fun ComponentBuilder(base: String, builder: ComponentBuilder.() -> Unit): ComponentBuilder {
    return ComponentBuilder(base).apply(builder)
}

operator fun ComponentBuilder.invoke(block : ComponentBuilder.() -> Unit) : ComponentBuilder {
    return this.apply(block)
}

fun ComponentBuilder.append(text: String, reset: Boolean = false, builder: ComponentBuilder.() -> Unit): ComponentBuilder {
    val component = this.append(text)
    if (reset) component.reset()
    return component.apply(builder)
}

operator fun ComponentBuilder.get(pos: Int): BaseComponent = getComponent(pos)

fun Item(material: Material, amount: Int = 1, nbt: String) =
        Item(material.key.toString(), amount, ItemTag.ofNbt(nbt))

fun Entity(entityType: EntityType, uuid: String, name: BaseComponent? = null) =
        Entity(entityType.key.toString(), uuid, name)

fun ComponentBuilder.hover(vararg items: Item) =
        event(HoverEvent(HoverEvent.Action.SHOW_ITEM, items.toList()))

fun ComponentBuilder.hover(vararg text: Text) =
        event(HoverEvent(HoverEvent.Action.SHOW_TEXT, text.toList()))

fun ComponentBuilder.hover(vararg entities: Entity) =
        event(HoverEvent(HoverEvent.Action.SHOW_ENTITY, entities.toList()))

fun ComponentBuilder.click(type: ClickEvent.Action, value: String) =
        event(ClickEvent(type, value))
