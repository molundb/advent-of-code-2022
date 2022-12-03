package day03

import readInput

fun main() {
    val input = readInput(parent = "src/Day03", name = "Day03_input")

    println(solvePartOne(input))
    println(solvePartTwo(input))
}

private fun solvePartTwo(input: List<String>): Int {
    var sum = 0
    var teamCount = 0
    val charsInFirst = mutableSetOf<Char>()
    val charsInFirstAndSecond = mutableSetOf<Char>()

    input.forEach { rucksack ->
        when (teamCount % 3) {
            0 -> {
                charsInFirst.clear()
                charsInFirst.addAll(rucksack.toSet())
            }

            1 -> {
                charsInFirstAndSecond.clear()

                for (c in rucksack) {
                    if (charsInFirst.contains(c)) {
                        charsInFirstAndSecond.add(c)
                    }
                }
            }

            2 -> {
                for (c in rucksack) {
                    if (charsInFirstAndSecond.contains(c)) {
                        sum += c.getPriority()
                        break
                    }
                }
            }

        }
        teamCount++
    }
    return sum
}

private fun solvePartOne(input: List<String>): Int {
    var sum = 0
    input.forEach { rucksack ->
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