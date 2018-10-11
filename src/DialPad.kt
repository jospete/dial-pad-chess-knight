class DialPad(private val layout: Array<IntArray>, private val invalidValue: Int = -1) {

    companion object {
        val moveSet = arrayOf(
                Position(-1, 2),
                Position(1, 2),
                Position(-1, -2),
                Position(1, -2),
                Position(2, -1),
                Position(2, 1),
                Position(-2, -1),
                Position(-2, 1)
        )
    }

    private fun valueOfCoordinates(x: Int, y: Int): Int {
        return layout[y][x]
    }

    private fun valueOf(position: Position): Int {
        return valueOfCoordinates(position.x, position.y)
    }

    private fun hasPositionCoordinates(x: Int, y: Int): Boolean {
        return x >= 0 && y >= 0 && y < layout.size && x < layout[y].size && valueOfCoordinates(x, y) != invalidValue
    }

    private fun hasPosition(position: Position): Boolean {
        return hasPositionCoordinates(position.x, position.y)
    }

    private fun positionFrom(x: Int, y: Int): Position {
        return setPositionState(Position(x, y))
    }

    private fun setPositionState(position: Position): Position {
        position.exists = hasPosition(position)
        position.value = if (position.exists) valueOf(position) else invalidValue
        return position
    }

    private fun getNeighbors(position: Position): List<Position> {
        return DialPad.moveSet
                .map { move -> setPositionState(position.shiftBy(move)) }
                .filter { p -> p.exists }
    }

    private fun concat(list1: MutableList<Position>, list2: MutableList<Position>): MutableList<Position> {
        val result = ArrayList<Position>()
        result.addAll(list1)
        result.addAll(list2)
        return result
    }

    private fun getPathFrom(start: Position, previousItems: MutableList<Position>): MutableList<Position> {

        val list = concat(previousItems, arrayListOf(start))

        val distinctNeighbors = getNeighbors(start)
                .asSequence()
                .filter { neighbor -> !list.any { item -> item.value == neighbor.value } }
                .toMutableList()

        if (distinctNeighbors.count() <= 0) {
            return list
        }

        return distinctNeighbors
                .asSequence()
                .map { neighbor -> getPathFrom(neighbor, list) }
                .reduce { acc, next -> if (acc.size > next.size) acc else next }
    }

    fun getPath(start: Position): MutableList<Position> {
        return getPathFrom(start, ArrayList())
    }

    fun positionOf(value: Int): Position {
        for (y in 0..(layout.size - 1)) {
            for (x in 0..(layout[y].size - 1)) {
                if (layout[y][x] == value) {
                    return positionFrom(x, y)
                }
            }
        }
        return Position()
    }
}