/**
 * Defines a position on a NavigationMatrix layout.
 * Here, a position is defined as an [x,y] coordinate that may or may not exist,
 * with a value that will only be considered valid if the position does in-fact exist.
 */
class Position(var x: Int = -1, var y: Int = -1, var exists: Boolean = false, var value: Int = 0) {

    /**
     * Create a new position instance which is offset from this one by the given offset.
     */
    fun shiftBy(offset: Position): Position {
        return shift(offset.x, offset.y)
    }

    /**
     * Create a new position instance which is offset from this one by the given values.
     */
    private fun shift(xOffset: Int, yOffset: Int): Position {
        return Position(x + xOffset, y + yOffset)
    }

    /**
     * Dump the state of this position.
     */
    override fun toString(): String {
        return "[x: $x, y: $y] -> exists: $exists, value: $value"
    }
}