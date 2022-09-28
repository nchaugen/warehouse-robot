internal class Command(
    private val command: ((Warehouse) -> Warehouse)
) {
    companion object {

        internal fun parse(command: String): Command = when (command) {
            "N" -> Command { warehouse -> warehouse.move(0, 1) }
            "S" -> Command { warehouse -> warehouse.move(0, -1) }
            "E" -> Command { warehouse -> warehouse.move(1, 0) }
            "W" -> Command { warehouse -> warehouse.move(-1, 0) }
            "NW" -> Command { warehouse -> warehouse.move(-1, 1) }
            "NE" -> Command { warehouse -> warehouse.move(1, 1) }
            "SE" -> Command { warehouse -> warehouse.move(1, -1) }
            "SW" -> Command { warehouse -> warehouse.move(-1, -1) }
            "G" -> Command { warehouse -> warehouse.grab() }
            "D" -> Command { warehouse -> warehouse.drop() }
            else -> throw IllegalArgumentException("Unknown command $command")
        }

        internal fun combine(first: String, second: String) = when (first to second) {
            "N" to "E" -> listOf("NE")
            "E" to "N" -> listOf("NE")
            "N" to "W" -> listOf("NW")
            "W" to "N" -> listOf("NW")
            "S" to "E" -> listOf("SE")
            "E" to "S" -> listOf("SE")
            "S" to "W" -> listOf("SW")
            "W" to "S" -> listOf("SW")
            else -> listOf(first, second)
        }
    }

    fun invoke(warehouse: Warehouse) = command.invoke(warehouse)
}
