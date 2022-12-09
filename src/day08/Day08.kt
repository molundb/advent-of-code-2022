package day08

import readInput
import java.lang.Integer.max

fun main() {
    val input = readInput(parent = "src/day08", name = "Day08_input")

    println(solvePartOne(input))
    println(solvePartTwo(input))
}

private fun solvePartTwo(input: List<String>): Int {
    val treeRows = mutableListOf<MutableList<Int>>()

    var highestScenicScore = 0

    for (l in input) {
        val row = mutableListOf<Int>()
        for (c in l) {
            row.add(c.digitToInt())
        }

        treeRows.add(row)
    }

    for (rowI in 1 until treeRows.lastIndex) {
        val row = treeRows[rowI]

        for (colI in 1 until row.lastIndex) {
            val tree = row[colI]

            var scenicScore = 1
            // Check up
            for (z in rowI - 1 downTo 0) {
                val t = treeRows[z][colI]
                if (tree <= t || z == 0) {
                    scenicScore *= rowI - z
                    break
                }
            }

            // Check right
            for (z in colI + 1..row.lastIndex) {
                val t = treeRows[rowI][z]
                if (tree <= t || z == row.lastIndex) {
                    scenicScore *= z - colI
                    break
                }
            }

            // Check down
            for (z in rowI + 1..treeRows.lastIndex) {
                val t = treeRows[z][colI]
                if (tree <= t || z == treeRows.lastIndex) {
                    scenicScore *= z - rowI
                    break
                }
            }

            // Check left
            for (z in colI - 1 downTo 0) {
                val t = treeRows[rowI][z]
                if (tree <= t || z == 0) {
                    scenicScore *= colI - z
                    break
                }
            }

            highestScenicScore = max(highestScenicScore, scenicScore)

        }
    }

    return highestScenicScore
}

private fun solvePartOne(input: List<String>): Int {
    val treeRows = mutableListOf<MutableList<Int>>()

    var visibleTrees = 0

    for (l in input) {
        val row = mutableListOf<Int>()
        for (c in l) {
            row.add(c.digitToInt())
        }

        treeRows.add(row)
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
            }
        }
    }

    return visibleTrees
}