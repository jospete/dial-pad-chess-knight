class DialPadTestResult(private val id: Int, val positions: List<Int>) {

    override fun toString(): String {
        return "value $id result: " + positions.toString() + " (${positions.size} distinct values)"
    }
}