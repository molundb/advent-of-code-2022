package day11

import readInput

fun main() {
    val input = readInput(parent = "src/day11", name = "Day11_input")

    println(solvePartOne(input))
    println(solvePartTwo(input))
}

private fun solvePartTwo(input: List<String>): Long {
    val monkeys = mutableListOf<Monkey>()

    var divisibleMod = 1L
    var i = 0
    val monkeyInfoSize = 7
    while (i < input.size) {
        val items = input[i + 1].split(": ")[1].split(", ").map { it.toLong() }
        val operation = input[i + 2].split(' ')[6]
        val operationVal = input[i + 2].split(' ')[7]

        val divisibleBy = input[i + 3].split(' ')[5].toInt()
        divisibleMod *= divisibleBy

        val ifTrue = input[i + 4].split(' ')[9].toInt()
        val ifFalse = input[i + 5].split(' ')[9].toInt()

        monkeys.add(
            Monkey(
                items = items.toMutableList(),
                operation = getOperation(operation, operationVal),
                test = Test(divisibleBy, ifTrue, ifFalse)
            )
        )

        i += monkeyInfoSize
    }


    repeat(10000) {
        for (m in monkeys) {
            for (item in m.items) {
                val new = m.operation(item) % divisibleMod
                if (new % m.test.divisibleBy == 0L) {
                    monkeys[m.test.ifTrue].items.add(new)
                } else {
                    monkeys[m.test.ifFalse].items.add(new)
                }
            }
            m.inspections += m.items.size.toLong()
            m.items.clear()
        }
    }

    monkeys.sortByDescending { it.inspections }

    return monkeys[0].inspections * monkeys[1].inspections
}
private fun solvePartOne(input: List<String>): Long {
    val monkeys = mutableListOf<Monkey>()

    var i = 0
    val monkeyInfoSize = 7
    while (i < input.size) {
        val items = input[i + 1].split(": ")[1].split(", ").map { it.toLong() }
        val operation = input[i + 2].split(' ')[6]
        val operationVal = input[i + 2].split(' ')[7]

        val divisibleBy = input[i + 3].split(' ')[5].toInt()
        val ifTrue = input[i + 4].split(' ')[9].toInt()
        val ifFalse = input[i + 5].split(' ')[9].toInt()

        monkeys.add(
            Monkey(
                items = items.toMutableList(),
                operation = getOperation(operation, operationVal),
                test = Test(divisibleBy, ifTrue, ifFalse)
            )
        )

        i += monkeyInfoSize
    }


    repeat(20) {
        for (m in monkeys) {
            for (item in m.items) {
                m.inspections++
                val new = m.operation(item) / 3
                if (new % m.test.divisibleBy == 0L) {
                    monkeys[m.test.ifTrue].items.add(new)
                } else {
                    monkeys[m.test.ifFalse].items.add(new)
                }
            }
            m.items.clear()
        }
    }

    monkeys.sortByDescending { it.inspections }

    return monkeys[0].inspections * monkeys[1].inspections
}

private fun getOperation(operation: String, operationVal: String) =
    if (operation == "+") {
        if (operationVal == "old") {
            add2()
        } else {
            add(operationVal.toLong())
        }
    } else {
        if (operationVal == "old") {
            multiply2()
        } else {
            multiply(operationVal.toLong())
        }
    }

private fun add(x: Long): (Long) -> Long = { old: Long ->
    x + old
}

private fun add2(): (Long) -> Long = { old: Long ->
    old + old
}

private fun multiply(x: Long): (Long) -> Long = { old: Long ->
    x * old
}

private fun multiply2(): (Long) -> Long = { old: Long ->
    old * old
}

data class Monkey(
    var inspections: Long = 0,
    val items: MutableList<Long>,
    val operation: (Long) -> Long,
    val test: Test,
)

data class Test(
    val divisibleBy: Int,
    val ifTrue: Int,
    val ifFalse: Int,
)