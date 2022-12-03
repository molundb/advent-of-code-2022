package day01

import readInput
import kotlin.math.max

fun main() {
    val input = readInput(parent = "src/Day01", name = "Day01_input")

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

    println(max)
}