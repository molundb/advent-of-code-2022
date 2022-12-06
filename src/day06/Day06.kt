package day06

import readInput

fun main() {
    val input = readInput(parent = "src/day06", name = "Day06_input")

    println(solvePartOne(input))
    println(solvePartTwo(input))
}

private fun solvePartOne(input: List<String>)= solve(input, 4)
private fun solvePartTwo(input: List<String>) = solve(input, 14)

private fun solve(input: List<String>, distinctCharacters: Int): Int {
    var lastSeen = mutableListOf<Char>()
    val numberOfEachLetter = mutableMapOf<Char, Int>()

    for ((i, c) in input[0].withIndex()) {
        numberOfEachLetter[c] = if (numberOfEachLetter.contains(c)) {
            numberOfEachLetter[c]!! + 1
        } else {
            1
        }

        if (numberOfEachLetter.size == distinctCharacters) {
            return i + 1
        }

        if (lastSeen.size < distinctCharacters - 1) {
            lastSeen.add(c)
        } else {
            val firstC = lastSeen.first()
            val toReduce = numberOfEachLetter[firstC]!!
            if (toReduce > 1) {
                numberOfEachLetter[firstC] = toReduce - 1
            } else {
                numberOfEachLetter.remove(firstC)
            }

            lastSeen = (lastSeen.drop(1) + listOf(c)).toMutableList()
        }
    }

    return -1
}
