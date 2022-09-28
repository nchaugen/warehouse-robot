internal class Commands(private val commands: List<Command>) {

    companion object {

        internal fun parse(commands: String) =
            Commands(commands
                .split(" ")
                .fold(emptyList()) { combined: List<String>, command: String ->
                    when {
                        combined.isEmpty() -> listOf(command)
                        else -> combined.dropLast(1) + Command.combine(combined.last(), command)
                    }
                }
                .map(Command::parse)
            )
    }

    fun fold(initial: Warehouse, operation: (Warehouse, Command) -> Warehouse) =
        commands.fold(initial, operation)
}
