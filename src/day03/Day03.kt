package day03

import readInput

fun main() {
    val input = readInput(parent = "src/Day03", name = "Day03_input")

    println(solvePartOne(input))
}

private fun solvePartOne(input: List<String>): Int {
    var sum = 0
    input.forEach {rucksack ->
        val charsInFirstHalf = mutableSetOf<Char>()
        for (i in rucksack.indices) {
            val c = rucksack[i]
            if (i < rucksack.length / 2) {
                charsInFirstHalf.add(c)
            } else {
                if (charsInFirstHalf.contains(c)) {
                    sum += c.getPriority()
                    break
                }
            }
        }
    }
    return sum
}

private fun Char.getPriority() = if (isUpperCase()) {
    this - 'A' + 27
} else {
    this - 'a' + 1
}