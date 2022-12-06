package day06

import readInput

fun main() {
    val input = readInput(parent = "src/day06", name = "Day06_input")

    println(solvePartOne(input))
}

private fun solvePartOne(input: List<String>): Int {
    var lastFour = mutableListOf<Char>()
    var lastDuplicate = 0

    for ((i, c) in input[0].withIndex()) {
        if (lastFour.contains(c)) {
            lastDuplicate = i
        } else if (i > 3 && i - lastDuplicate > 3) {
            return i
        }

        if (lastFour.size < 4) {
            lastFour.add(c)
        } else {
            lastFour = (listOf(c) + lastFour.dropLast(1)).toMutableList()
        }
    }

    return -1
}