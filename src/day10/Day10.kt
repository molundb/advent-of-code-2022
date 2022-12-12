package day10

import readInput
import kotlin.math.abs

fun main() {
    val input = readInput(parent = "src/day10", name = "Day10_input")

    println(solvePartOne(input))
    println(solvePartTwo(input))
}

private fun solvePartTwo(input: List<String>): String {
    var allLines = ""
    var currLine = "#"
    var c = 1
    var x = 1

    for (l in input) {
        if (c % 40 == 0) {
            allLines += "$currLine\n"
            currLine = ""
        }

        currLine += draw(c, x)

        if (l.startsWith("addx")) {
            c++
            if (c % 40 == 0) {
                allLines += "$currLine\n"
                currLine = ""
            }
            x += l.split(' ')[1].toInt()
            currLine += draw(c, x)
        }

        c++
    }

    return allLines
}

private fun draw(c: Int, x: Int) = if (abs(c % 40 - x) <= 1) {
    "#"
} else {
    "."
}

private fun solvePartOne(input: List<String>): Int {
    var signalStrengthSum = 0
    var c = 1
    var x = 1

    for (l in input) {
        signalStrengthSum += calc(c, x)

        if (l.startsWith("addx")) {
            c++
            signalStrengthSum += calc(c, x)
            x += l.split(' ')[1].toInt()
        }

        c++
    }
    return signalStrengthSum
}

private fun calc(c: Int, x: Int) = if ((c - 20) % 40 == 0) {
    c * x
} else {
    0
}

private fun printInfo(c: Int, x: Int, l: String, signalStrengthSum: Int) {
    println("c: $c, x: $x, l: $l, signalStrengthSum: $signalStrengthSum")
}