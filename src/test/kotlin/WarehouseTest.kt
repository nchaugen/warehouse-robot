import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WarehouseTest {

    @Test
    fun `should move inside warehouse`() {
        assertThat(robotAt(5, 5).execute("N E S W")).isEqualTo(robotAt(5, 5))

        assertThat(robotAt(0, 0).execute("N E N E N E N E")).isEqualTo(robotAt(4, 4))

        assertThat(robotAt(9, 9).execute("N")).isEqualTo(robotAt(9, 9))
        assertThat(robotAt(9, 9).execute("E")).isEqualTo(robotAt(9, 9))
        assertThat(robotAt(0, 0).execute("S")).isEqualTo(robotAt(0, 0))
        assertThat(robotAt(0, 0).execute("W")).isEqualTo(robotAt(0, 0))
    }

    @Test
    fun `should move crates`() {
        assertThat(
            Warehouse()
                .withRobotAt(0, 0)
                .withCrateAt(4, 4)
                .withCrateAt(9, 9)
                .execute("N E N E N E N E G W S W S W S W S D")
        ).isEqualTo(
            Warehouse()
                .withRobotAt(0, 0)
                .withCrateAt(0, 0)
                .withCrateAt(9, 9)
        )
    }

    @Test
    fun `should not try and lift a crate if it already lifting one`() {
        assertThat(
            Warehouse()
                .withRobotAt(4, 4)
                .withCrateAt(4, 4)
                .withCrateAt(4, 5)
                .execute("G N G N D")
        ).isEqualTo(
            Warehouse()
                .withRobotAt(4, 6)
                .withCrateAt(4, 6)
                .withCrateAt(4, 5)
        )
    }

    @Test
    fun `should not lift a crate if there is not one present`() {
        assertThat(
            Warehouse()
                .withRobotAt(4, 4)
                .withCrateAt(4, 4)
                .withCrateAt(4, 5)
                .execute("S G S D")
        ).isEqualTo(
            Warehouse()
                .withRobotAt(4, 2)
                .withCrateAt(4, 4)
                .withCrateAt(4, 5)
        )
    }

    @Test
    fun `should not drop a crate on another crate`() {
        assertThat(
            Warehouse()
                .withRobotAt(4, 4)
                .withCrateAt(4, 4)
                .withCrateAt(4, 5)
                .execute("G N D N D")
        ).isEqualTo(
            Warehouse()
                .withRobotAt(4, 6)
                .withCrateAt(4, 6)
                .withCrateAt(4, 5)
        )
    }

    @Test
    fun `should move diagonally`() {
        assertThat(
            robotAt(4, 4).execute("N E W N S E W S")
        ).isEqualTo(
            robotAt(4, 4).execute("NE NW SE SW")
        )
    }

    private fun robotAt(x: Int, y: Int) = Warehouse().withRobotAt(x, y)
}
