const val iv = -1

val pad = DialPad(arrayOf(
        intArrayOf( 1,  2,  3, 1234),
        intArrayOf( 4,  5,  6, 4444, 6612),
        intArrayOf( 7,  8,  9, 8923, 2341, 9867),
        intArrayOf(iv,  0, iv)
), iv)

val tester = DialPadTester(pad)

fun main(args: Array<String>) {
    arrayListOf(-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 12345)
            .asSequence()
            .map { value -> tester.test(value) }
            .sortedBy { value -> value.positions.size }
            .forEach { value -> println(value) }
}