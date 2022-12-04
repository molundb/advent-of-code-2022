package day04

import readInput

fun main() {
    val input = readInput(parent = "src/day04", name = "Day04_input")

    println(solvePartOne(input))
    println(solvePartTwo(input))
}

private fun solvePartOne(input: List<String>): Int = solve(input, partOneOverlapCheck())
private fun solvePartTwo(input: List<String>): Int = solve(input, partTwoOverlapCheck())
private fun partOneOverlapCheck(): (Int, Int, Int, Int) -> Boolean =
    { startPairOne: Int, startPairTwo: Int, endPairOne: Int, endPairTwo: Int ->
        startPairOne >= startPairTwo && endPairOne <= endPairTwo || startPairOne <= startPairTwo && endPairOne >= endPairTwo
    }

private fun partTwoOverlapCheck(): (Int, Int, Int, Int) -> Boolean =
    { startPairOne: Int, startPairTwo: Int, endPairOne: Int, endPairTwo: Int -> !(endPairOne < startPairTwo || startPairOne > endPairTwo) }

private fun solve(input: List<String>, overlapCheck: (Int, Int, Int, Int) -> Boolean): Int {
    var sumOverlap = 0
    input.forEach {
        val pair = it.split(',')

        val startAndEndPairOne = pair[0].split('-')
        val startAndEndPairTwo = pair[1].split('-')

        val startPairOne = startAndEndPairOne[0].toInt()
        val startPairTwo = startAndEndPairTwo[0].toInt()
        val endPairOne = startAndEndPairOne[1].toInt()
        val endPairTwo = startAndEndPairTwo[1].toInt()

        if (overlapCheck(startPairOne, startPairTwo, endPairOne, endPairTwo)) {
            sumOverlap++
        }
    }
    return sumOverlap
}