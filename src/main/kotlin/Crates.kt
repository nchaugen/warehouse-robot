class Crates(
    private val crates: List<Crate> = emptyList()
) {

    fun addAt(position: Grid.Position) = Crates(this.crates + Crate(position))

    fun move(deltaX: Int, deltaY: Int) = this.map { it.move(deltaX, deltaY) }

    fun grabbedBy(robot: Robot) = when {
        crates.any(Crate::isGrabbed) -> this
        else -> this.map { if (it.isInSamePositionAs(robot)) it.grab() else it }
    }

    fun droppedFrom(robot: Robot) = when {
        crates.none(Crate::isGrabbed) -> this
        crates.any { it.isInSamePositionAs(robot) && !it.isGrabbed() } -> this
        else -> this.map { if (it.isInSamePositionAs(robot)) it.drop() else it }
    }

    override fun toString() = "Crates$crates"
    override fun equals(other: Any?) = other is Crates && crates == other.crates
    override fun hashCode() = crates.hashCode()

    private fun map(transform: (Crate) -> Crate) = Crates(crates.map(transform))
}
