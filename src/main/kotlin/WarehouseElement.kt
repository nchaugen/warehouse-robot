internal sealed class WarehouseElement(
    protected val position: Grid.Position
) {
    fun isInSamePositionAs(other: WarehouseElement) = this.position == other.position

    abstract fun at(position: Grid.Position): WarehouseElement
    abstract fun move(deltaX: Int, deltaY: Int): WarehouseElement
}
