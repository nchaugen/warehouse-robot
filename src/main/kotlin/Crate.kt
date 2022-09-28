internal class Crate(
    position: Grid.Position,
    private val grabbed: Boolean = false
) : WarehouseElement(position) {

    override fun at(position: Grid.Position) = Crate(position, grabbed)
    override fun move(deltaX: Int, deltaY: Int) = if (grabbed) this.at(position.right(deltaX).up(deltaY)) else this

    fun isGrabbed(): Boolean = grabbed
    fun grab(): Crate = Crate(position, true)
    fun drop(): Crate = Crate(position, false)

    override fun toString() = "$position ${if (grabbed) "G" else ""}"
    override fun equals(other: Any?) = other is Crate && position == other.position && grabbed == other.grabbed
    override fun hashCode() = 31 * position.hashCode()
}
