class Position(var x: Int = -1, var y: Int = -1, var exists: Boolean = true, var value: Int = 0) {

    private fun shift(x: Int, y: Int): Position {
        return Position(this.x + x, this.y + y)
    }

    fun shiftBy(offset: Position): Position {
        return shift(offset.x, offset.y)
    }

    override fun toString(): String {
        return "[x: $x, y: $y]"
    }
}