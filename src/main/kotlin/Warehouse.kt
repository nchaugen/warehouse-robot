class Warehouse(
    private val grid: Grid = Grid(10, 10),
    private val robot: Robot = Robot(grid.position(0, 0)),
    private val crates: Crates = Crates()
) {

    fun execute(commands: String): Warehouse = Commands.parse(commands).fold(this, Warehouse::execute)
    private fun execute(command: Command) = command.invoke(this)

    fun withRobotAt(x: Int, y: Int) = this.with(robot.at(grid.position(x, y)))
    fun withCrateAt(x: Int, y: Int) = this.with(crates.addAt(grid.position(x, y)))

    private fun with(robot: Robot) = Warehouse(grid, robot, crates)
    private fun with(crates: Crates) = Warehouse(grid, robot, crates)

    internal fun move(deltaX: Int, deltaY: Int) = this
        .with(robot.move(deltaX, deltaY))
        .with(crates.move(deltaX, deltaY))

    internal fun grab() = this.with(crates.grabbedBy(robot))
    internal fun drop() = this.with(crates.droppedFrom(robot))

    override fun toString() = "Warehouse($grid, $robot, $crates)"
    override fun hashCode() = 31 * (31 * grid.hashCode() + crates.hashCode()) + robot.hashCode()
    override fun equals(other: Any?) =
        other is Warehouse && grid == other.grid && crates == other.crates && robot == other.robot
}
