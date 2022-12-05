package day05

import readInput
import java.util.LinkedList

fun main() {
    val input = readInput(parent = "src/day05", name = "Day05_input")

    println(solvePartOne(input))
}

private fun solvePartOne(input: List<String>): String {
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

            val fromStack = stacks[split[3].toInt()]!!
            stacks[split[3].toInt()] = LinkedList(fromStack.dropLast(split[1].toInt()))

            val toStack = stacks[split[5].toInt()]!!
            toStack.addAll(fromStack.takeLast(split[1].toInt()).reversed())
//            println(s)
//            printStacks(stacks)
        }
    }

    var res = ""
    stacks.values.forEach {
        res += it.last
    }
    return res
}

private fun printStacks(stacks: MutableMap<Int, LinkedList<Char>>) {
    stacks.forEach {
        println("${it.key}: ${it.value}")
    }
    println()
}