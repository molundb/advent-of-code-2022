package day05

import readInput
import java.util.LinkedList

fun main() {
    val input = readInput(parent = "src/day05", name = "Day05_input")

    println(solvePartOne(input))
    println(solvePartTwo(input))
}

private fun solvePartOne(input: List<String>) = solve(input, false)
private fun solvePartTwo(input: List<String>) = solve(input, true)

private fun solve(input: List<String>, partTwo: Boolean): String {
    val stacks: MutableMap<Int, LinkedList<Char>> = mutableMapOf(
        1 to LinkedList<Char>(),
        2 to LinkedList<Char>(),
        3 to LinkedList<Char>(),
        4 to LinkedList<Char>(),
        5 to LinkedList<Char>(),
        6 to LinkedList<Char>(),
        7 to LinkedList<Char>(),
        8 to LinkedList<Char>(),
        9 to LinkedList<Char>(),
    )

    for (s in input) {
        if (s.startsWith('[')) {
            for (i in s.indices) {
                val c = s[i]
                if (c.isLetter()) {
                    val i1 = (i - 1) / 4 + 1
                    stacks[i1]!!.push(c)
                }
            }
        }

        if (s.startsWith("move")) {
            val split = s.split(' ')

            val numberOfCratesToMove = split[1].toInt()
            val fromStackNumber = split[3].toInt()
            val toStackNumber = split[5].toInt()

            val fromStack = stacks[fromStackNumber]!!
            stacks[fromStackNumber] = LinkedList(fromStack.dropLast(numberOfCratesToMove))

            val toStack = stacks[toStackNumber]!!
            val cratesToMove = fromStack.takeLast(numberOfCratesToMove)
            toStack.addAll(
                if (partTwo) {
                    cratesToMove
                } else {
                    cratesToMove.reversed()
                }
            )
        }
    }

    var res = ""
    stacks.values.forEach {
        res += it.last
    }
    return res
}