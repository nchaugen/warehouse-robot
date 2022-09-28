internal class Robot(
    position: Grid.Position
) : WarehouseElement(position) {

    override fun at(position: Grid.Position) = Robot(position)
    override fun move(deltaX: Int, deltaY: Int) = this.at(position.right(deltaX).up(deltaY))

    override fun toString() = "Robot$position"
    override fun equals(other: Any?) = other is Robot && position == other.position
    override fun hashCode() = 31 * position.hashCode()
}
