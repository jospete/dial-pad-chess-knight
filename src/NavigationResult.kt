/**
 * Contains result data for a navigation attempt in a NavigationTest instance.
 */
class NavigationResult(private val id: Int, val positionValues: List<Int>) {

    /**
     * Dump the state of this result.
     */
    override fun toString(): String {
        return "value $id result (${positionValues.size} distinct values): " + positionValues.toString()
    }
}