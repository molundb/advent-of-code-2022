package day10

import readInput

fun main() {
    val input = readInput(parent = "src/day10", name = "Day10_input")

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

    println(signalStrengthSum)

}

private fun calc(c: Int, x: Int) = if ((c - 20) % 40 == 0) {
    c * x
} else {
    0
}

private fun printInfo(c: Int, x: Int, l: String, signalStrengthSum: Int) {
    println("c: $c, x: $x, l: $l, signalStrengthSum: $signalStrengthSum")
}