package day08

import readInput

fun main() {
    val input = readInput(parent = "src/day08", name = "Day08_input")

    println(solvePartOne(input))
}

private fun solvePartOne(input: List<String>): Int {
    val treeRows = mutableListOf<MutableList<Int>>()
    val minusBlocked = mutableListOf<MutableList<Char>>()

    var visibleTrees = 0

    for (l in input) {
        val row = mutableListOf<Int>()
        val rowC = mutableListOf<Char>()
        for (c in l) {
            row.add(c.digitToInt())
            rowC.add(c)
        }

        treeRows.add(row)
        minusBlocked.add(rowC.toMutableList())
    }

    for ((rowI, row) in treeRows.withIndex()) {
        if (rowI == 0 || rowI == treeRows.lastIndex) {
            visibleTrees += row.size
            continue
        }

        for ((colI, tree) in row.withIndex()) {
            if (colI == 0 || colI == row.lastIndex) {
                visibleTrees++
                continue
            }

            var blocked = 0
            // Check up
            for (z in 0 until rowI) {
                val t = treeRows[z][colI]
                if (tree <= t) {
                    blocked++
                    break
                }
            }

            // Check right
            for (z in colI + 1..row.lastIndex) {
                val t = treeRows[rowI][z]
                if (tree <= t) {
                    blocked++
                    break
                }
            }

            // Check down
            for (z in rowI + 1..treeRows.lastIndex) {
                val t = treeRows[z][colI]
                if (tree <= t) {
                    blocked++
                    break
                }
            }

            // Check left
            for (z in 0 until colI) {
                val t = treeRows[rowI][z]
                if (tree <= t) {
                    blocked++
                    break
                }
            }

            if (blocked < 4) {
                visibleTrees++
            } else {
                minusBlocked[rowI][colI] = 'B'
            }

//            println("r: ${rowI}, c: ${colI}, tree: ${tree}, blocked: $blocked")

        }
    }

    treeRows.print()

    println()

    minusBlocked.printChar()
    return visibleTrees
}

fun MutableList<MutableList<Int>>.print() {
    this.forEach {
        it.forEach {
            print(it)
        }
    }

    println()
}

fun MutableList<MutableList<Char>>.printChar() {
    this.forEach {
        it.forEach {
            print(it)
        }
    }

    println()
}