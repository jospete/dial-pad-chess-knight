const val invalidValue = -1

val pad = DialPad(arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9),
        intArrayOf(invalidValue, 0, invalidValue)
), invalidValue)

val tester = DialPadTester(pad)

fun main(args: Array<String>) {
    arrayListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
            .asSequence()
            .map { value -> tester.test(value) }
            .sortedBy { value -> value.positions.size }
            .forEach { value -> println(value) }
}