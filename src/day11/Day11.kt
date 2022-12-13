package day11

import readInput

fun main() {
    val input = readInput(parent = "src/day11", name = "Day11_input")

    println(solvePartOne(input))
}

private fun solvePartOne(input: List<String>): Int {
    val monkeys = mutableListOf<Monkey>()

    var i = 0
    val monkeyInfoSize = 7
    while (i < input.size) {
        val items = input[i + 1].split(": ")[1].split(", ").map { it.toInt() }
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
                if (new % m.test.divisibleBy == 0) {
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
            add(operationVal.toInt())
        }
    } else {
        if (operationVal == "old") {
            multiply2()
        } else {
            multiply(operationVal.toInt())
        }
    }

private fun add(x: Int): (Int) -> Int = { old: Int ->
    x + old
}

private fun add2(): (Int) -> Int = { old: Int ->
    old + old
}

private fun multiply(x: Int): (Int) -> Int = { old: Int ->
    x * old
}

private fun multiply2(): (Int) -> Int = { old: Int ->
    old * old
}

data class Monkey(
    var inspections: Int = 0,
    val items: MutableList<Int>,
    val operation: (Int) -> Int,
    val test: Test,
)

data class Test(
    val divisibleBy: Int,
    val ifTrue: Int,
    val ifFalse: Int,
)