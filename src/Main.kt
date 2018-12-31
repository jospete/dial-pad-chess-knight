// Mark invalid positions with this value (holes in the NavigationMatrix)
const val invalidValue = -1

// Defines the layout of a traditional phone dial pad
val dialPadLayout = arrayOf(
        intArrayOf( 1,  2,  3),
        intArrayOf( 4,  5,  6),
        intArrayOf( 7,  8,  9),
        intArrayOf(invalidValue,  0, invalidValue)
)

// Defines the valid moves that a knight piece can make in chess
val knightPieceMoveSet = arrayOf(
        Position(-1, 2),
        Position(1, 2),
        Position(-1, -2),
        Position(1, -2),
        Position(2, -1),
        Position(2, 1),
        Position(-2, -1),
        Position(-2, 1)
)

// Make a dial pad matrix that we can navigate over
val dialPad = NavigationMatrix(dialPadLayout, knightPieceMoveSet, invalidValue)

// Make a navigator that will use the dial pad matrix to test longest paths for values
val pathTester = Navigator(dialPad)

// Test these starting point values to get the longest path among them
val startValues = listOf(-50, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12345)

fun main(args: Array<String>) {
    startValues
            .asSequence()
            .map { value -> pathTester.getLongestPath(value) }
            .sortedBy { value -> value.positionValues.size }
            .forEach { value -> println(value) }
}