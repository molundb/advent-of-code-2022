package day02

import readInput

fun main () {
    val input = readInput(parent = "src/Day02", name = "Day02_input")

    println(solvePartOne(input))
    println(solvePartTwo(input))
}

private fun solvePartOne(input: List<String>): Int {
    val combinations = mapOf(
        Pair("A X", 4),
        Pair("A Y", 8),
        Pair("A Z", 3),
        Pair("B X", 1),
        Pair("B Y", 5),
        Pair("B Z", 9),
        Pair("C X", 7),
        Pair("C Y", 2),
        Pair("C Z", 6),
    )

    var totalScore = 0
    input.forEach {
        totalScore += combinations[it] ?: 0
    }
    return totalScore
}

private fun solvePartTwo(input: List<String>): Int {
    val combinations = mapOf(
        Pair("A X", 3),
        Pair("A Y", 4),
        Pair("A Z", 8),
        Pair("B X", 1),
        Pair("B Y", 5),
        Pair("B Z", 9),
        Pair("C X", 2),
        Pair("C Y", 6),
        Pair("C Z", 7),
    )

    var totalScore = 0
    input.forEach {
        totalScore += combinations[it] ?: 0
    }
    return totalScore
}