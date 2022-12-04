package day04

import readInput

fun main() {
    val input = readInput(parent = "src/day04", name = "Day04_input")

    println(solvePartOne(input))
}

private fun solvePartOne(input: List<String>): Int {
    var sumOverlap = 0
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
            sumOverlap++
        }
    }
    return sumOverlap
}