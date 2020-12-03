# Buk.kt

Dependency

```kotlin
repositories {
    maven { setUrl("https://jitpack.io/") }
}

dependencies {
    implementation("com.github.rqbik", "bukkt", "1.0.0")
}
```

Example

```kotlin
class Plugin : JavaPlugin() {
    override fun onEnable() {
        events { // Automatically registers every event listener that you declare.
            listen<BlockBreakEvent> { // On block break
                world.spawn<Sheep>(block.location).apply {
                    color = DyeColor.RED
                }

                server.broadcastMessage {
                    "Player $name just broke a block!"
                }

                if (block.type == Material.DIAMOND_BLOCK)
                    player.equipment?.fill(EquipmentType.DIAMOND) // Fills player's equipment with diamond armor

                if (block.type == Material.TNT) player.kill()

                if (block.type in Tag.WOOL) player.fly = true

                if (block.location in BoundingBox(-100.0..100.0, 0.0..255.0, -100.0..100.0)) {
                    isCancelled = true
                    player.sendMessage("You can't break blocks at spawn!")
                }

                player.sendMessage(
                    "{name}, you just broke {type}".replace(
                        "{name}" to player.name,
                        "{type}" to block.type
                    )
                )
            }

            listen<EntityDamageByEntityEvent> {
                val player = entity as? Player ?: return@listen

                player.give(Material.DIRT)
                player.give(Material.DIAMOND_BLOCK, 2)
                player.give(item<PotionMeta>(Material.POTION, 1, "Poison") {
                    basePotionData = PotionData(PotionType.POISON)
                })

                player.sendMessage(
                    "Hey!",
                    "Your name is ${player.name}"
                )
            }

            listen<PlayerJoinEvent> {
                // Creates a scoreboard
                val scoreboard = scoreboard("&9Information".colorize()) {
                    // Adds a line
                    line("Your nickname: ${player.name}")
                    line("Your exp: ") {
                        onRender { // This will be invoked when player first sees this line
                            newText = "Your exp: ${(0..10).random()}"
                        }
                    }

                    line("Health: ${player.health}") {
                        onUpdate { // This will be invoked on each line update
                            // Update scoreboard line text with new health value
                            newText = "Health: ${player.health}"
                        }
                    }


                    title {
                        onUpdate {
                            newTitle = "&9Information: ${player.name}"
                        }
                    }
                }

                scoreboard.show(player)

                // Update scoreboard every 10 ticks
                scheduler.runTaskTimer(plugin, Time(0), Time(10) of TimeType.TICKS) {
                    scoreboard.updateLines()
                }
            }

            listen<PlayerMoveEvent> {
                if (!moved) return@listen // Checks if player changed his location

                // Checks if player is in 10 block radius of world spawnpoint
                if (player.location in world.spawnLocation.range(10))
                    player.sendMessage("&9You &rare near &cspawn!".colorize())
            }

            listen<PlayerDropItemEvent> {
                if (itemStack.isArmor) {
                    player.sendMessage("You just dropped armor!")
                }
            }
        }
    }
}
```