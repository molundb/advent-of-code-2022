package day02

import readInput

fun main () {
    val input = readInput(parent = "src/Day02", name = "Day02_input")

    val totalScore = solvePartOne(input)
    println(totalScore)
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