/**
 * Contains result data for a navigation attempt in a NavigationTest instance.
 */
class NavigationResult(private val id: Int, val positions: List<Int>) {

    /**
     * Dump the state of this result.
     */
    override fun toString(): String {
        return "value $id result (${positions.size} distinct values): " + positions.toString()
    }
}