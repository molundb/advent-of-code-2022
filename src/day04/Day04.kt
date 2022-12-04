package day04

import readInput

fun main() {
    val input = readInput(parent = "src/day04", name = "Day04_input")

    println(solvePartOne(input))
    println(solvePartTwo(input))
}

private fun solvePartOne(input: List<String>): Int {
    var sumCompleteOverlap = 0
    input.forEach {
        val pair = it.split(',')

        val startAndEndPairOne = pair[0].split('-')
        val startAndEndPairTwo = pair[1].split('-')

        val startPairOne = startAndEndPairOne[0].toInt()
        val startPairTwo = startAndEndPairTwo[0].toInt()
        val endPairOne = startAndEndPairOne[1].toInt()
        val endPairTwo = startAndEndPairTwo[1].toInt()

        if (startPairOne >= startPairTwo && endPairOne <= endPairTwo
            || startPairOne <= startPairTwo && endPairOne >= endPairTwo
        ) {
            sumCompleteOverlap++
        }
    }
    return sumCompleteOverlap
}

private fun solvePartTwo(input: List<String>): Int {
    var sumPartialOverlap = 0
    input.forEach {
        val pair = it.split(',')

        val startAndEndPairOne = pair[0].split('-')
        val startAndEndPairTwo = pair[1].split('-')

        val startPairOne = startAndEndPairOne[0].toInt()
        val startPairTwo = startAndEndPairTwo[0].toInt()
        val endPairOne = startAndEndPairOne[1].toInt()
        val endPairTwo = startAndEndPairTwo[1].toInt()

        if (!(endPairOne < startPairTwo || startPairOne > endPairTwo)
        ) {
            sumPartialOverlap++
        }
    }
    return sumPartialOverlap
}