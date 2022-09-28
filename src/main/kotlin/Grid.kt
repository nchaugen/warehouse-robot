internal class Grid(
    private val width: Int,
    private val length: Int
) {

    internal fun position(x: Int, y: Int): Position {
        return Position(this, x.within(0 until width), y.within(0 until length))
    }

    private fun Int.within(range: IntRange) =
        if (this < range.first) range.first else if (this >= range.last) range.last - 1 else this

    override fun toString() = "Grid($width x $length)"
    override fun equals(other: Any?) = other is Grid && width == other.width && length == other.length
    override fun hashCode() = 31 * width + length

    class Position(
        private val grid: Grid,
        private val x: Int,
        private val y: Int
    ) {
        fun right(steps: Int) = grid.position(x + steps, y)
        fun up(steps: Int) = grid.position(x, y + steps)

        override fun toString() = (x to y).toString()
        override fun equals(other: Any?) = other is Position && grid == other.grid && x == other.x && y == other.y
        override fun hashCode() = 31 * (31 * grid.hashCode() + x) + y
    }
}
