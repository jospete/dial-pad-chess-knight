class DialPadTester(private val pad: DialPad) {

    fun test(value: Int): DialPadTestResult {
        return DialPadTestResult(value, pad.getPath(pad.positionOf(value)).map { p -> p.value })
    }
}