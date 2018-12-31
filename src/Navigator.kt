/**
 * Creates NavigationResult instances based on target matrix values.
 */
class Navigator(private val matrix: NavigationMatrix) {

    /**
     * Create a NavigationResult based on the given start value.
     * Implicitly uses the first position found in the matrix with the given value.
     */
    fun getLongestPath(value: Int): NavigationResult {
        return getLongestPath(value, matrix.positionOf(value))
    }

    /**
     * Create a NavigationResult based on the given start value and position.
     */
    private fun getLongestPath(value: Int, position: Position): NavigationResult {
        return NavigationResult(value, matrix.getPath(position).map { p -> p.value })
    }
}