package day01

import readInput
import kotlin.math.max

fun main() {
    val input = readInput(parent = "src/Day01", name = "Day01_input")

    println(solvePartOne(input))
    println(solvePartTwo(input))
}

private fun solvePartOne(input: List<String>): Int {
    var max = 0
    var curr = 0
    input.forEach { foodItem ->
        if (foodItem.isEmpty()) {
            max = max(max, curr)
            curr = 0
        } else {
            curr += foodItem.toInt()
        }
    }
    return max
}

private fun solvePartTwo(input: List<String>): Int {
    val topThreeCalories = IntArray(3)
    var curr = 0
    input.forEach { foodItem ->
        if (foodItem.isEmpty()) {
            for (i in 0..2) {
                if (curr > topThreeCalories[i]) {
                    if (i == 0) {
                        topThreeCalories[2] = topThreeCalories[1]
                        topThreeCalories[1] = topThreeCalories[0]
                    } else if (i == 1) {
                        topThreeCalories[2] = topThreeCalories[1]
                    }
                    topThreeCalories[i] = curr
                    break
                }
            }
            curr = 0
        } else {
            curr += foodItem.toInt()
        }
    }
    return topThreeCalories.sum()
}