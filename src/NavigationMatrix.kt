/**
 * Defines a 2D matrix that an entity can be moved across, and determines distinct navigation paths.
 * This matrix will use the given layout as its boundaries, and the given move-set to determine the
 * longest path that an entity can take from a given start position.
 */
class NavigationMatrix(private val layout: Array<IntArray>, private val moveSet: Array<Position>, private val invalidValue: Int) {

    /**
     * Determine the longest path that can be taken from the given start position,
     * where all nodes in the path are distinct from one another (no overlapping positions).
     */
    fun getPath(start: Position): List<Position> {
        return getPathFrom(start, emptyList())
    }

    /**
     * Search the configured layout for a Position instance with the given value.
     * NOTE: this does NOT account for duplicate values, as the layout should implicitly
     * only contain unique values for each position.
     */
    fun positionOf(value: Int): Position {

        for (y in 0..(layout.size - 1)) {
            for (x in 0..(layout[y].size - 1)) {
                if (layout[y][x] == value) {
                    return getPositionAt(x, y)
                }
            }
        }

        return Position()
    }

    /**
     * Are the given coordinates inside of our layout?
     */
    private fun containsCoordinates(x: Int, y: Int): Boolean {
        return x >= 0 && y >= 0 && y < layout.size && x < layout[y].size
    }

    /**
     * Get the value for the given coordinates.
     */
    private fun valueOf(x: Int, y: Int): Int {
        return layout[y][x]
    }

    /**
     * Get the value for the given position.
     */
    private fun valueOf(position: Position): Int {
        return valueOf(position.x, position.y)
    }

    /**
     * Do the given coordinates exist AND have a valid value (i.e., not a hole in the matrix)?
     */
    private fun isValid(x: Int, y: Int): Boolean {
        return containsCoordinates(x, y) && valueOf(x, y) != invalidValue
    }

    /**
     * Does the given position exist AND have a valid value (i.e., not a hole in the matrix)?
     */
    private fun isValid(position: Position): Boolean {
        return isValid(position.x, position.y)
    }

    /**
     * Load the state of the given position, based on this matrix configuration.
     */
    private fun setPositionState(position: Position): Position {
        position.exists = isValid(position)
        position.value = if (position.exists) valueOf(position) else invalidValue
        return position
    }

    /**
     * Get a position instance that mirrors the configured layout.
     * The position will be tagged with whether or not it exists, and what value it
     * contains if it does exist in the configured layout.
     */
    private fun getPositionAt(x: Int, y: Int): Position {
        return setPositionState(Position(x, y))
    }

    /**
     * From the given start position, determine what path we can take without
     * overlapping positions in the previous path sequence.
     * This will terminate only when there are no moves left to take for a distinct path.
     */
    private fun getPathFrom(start: Position, previousPath: List<Position>): List<Position> {

        // The start position is out of bounds, so we can't continue
        if (!isValid(start)) {
            return previousPath
        }

        // Make a new path including the current starting position
        val path = previousPath + listOf(start)

        // Find all moves we can make from the given start position that
        // do not overlap with the current path set.
        val distinctNeighbors = moveSet
                .asSequence()
                .map { move -> setPositionState(start.shiftBy(move)) }
                .filter { neighbor -> neighbor.exists && !path.any { item -> item.value == neighbor.value } }
                .toMutableList()

        // No new moves can be made, so the current path is the longest one we can take
        if (distinctNeighbors.count() <= 0) {
            return path
        }

        // Find all sub-paths from the current one, reducing the results down
        // to the longest possible path
        return distinctNeighbors
                .asSequence()
                .map { neighbor -> getPathFrom(neighbor, path) }
                .reduce { acc, next -> if (acc.size > next.size) acc else next }
    }
}