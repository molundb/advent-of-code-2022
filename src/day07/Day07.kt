package day07

import readInput

fun main() {
    val input = readInput(parent = "src/day07", name = "Day07_input")

    println(solvePartOne(input))
    println(solvePartTwo(input))
}

private fun solvePartOne(input: List<String>): Int {
    val b = solve(input, calc1())
    return b[0]
}
private fun solvePartTwo(input: List<String>): Int {
    val a = solve(input, calc2())
    a.sort()
    return a.find { it > a.last() - 40000000 }!!
}

private fun solve(input: List<String>, calc: (Directory, MutableList<Int>) -> Unit): MutableList<Int> {
    val directorySizes = mutableListOf<Int>()

    val rootDir = Directory(name = "/")
    var currDir = rootDir

    for (l in input) {
        if (l == "$ cd /") {
            currDir = rootDir
        } else if (l == "$ cd ..") {
            calc(currDir, directorySizes)
            currDir = currDir.parent!!
        } else if (l == "$ ls") {
        } else if (l.startsWith(prefix = "$ cd")) {
            currDir = currDir.subDirs.find { it.name == l.split(' ')[2] }!!
        } else if (l.startsWith(prefix = "dir")) {
            currDir.subDirs.add(Directory(parent = currDir, name = l.split(' ')[1]))
        } else {
            currDir.size += l.split(' ')[0].toInt()
        }
    }

    while (currDir != rootDir) {
        calc(currDir, directorySizes)
        currDir = currDir.parent!!
    }

    directorySizes.add(rootDir.size)

    return directorySizes
}

private fun calc1() = { currDir: Directory, directorySizes: MutableList<Int> ->
    if (directorySizes.isEmpty()) {
        directorySizes.add(0)
    }
    if (currDir.size <= 100000) {
        directorySizes[0] += currDir.size
    }
    currDir.parent!!.size += currDir.size
}

private fun calc2() = { currDir: Directory, directorySizes: MutableList<Int> ->
    directorySizes.add(currDir.size)
    currDir.parent!!.size += currDir.size
}

class Directory(
    val parent: Directory? = null,
    var name: String,
    var size: Int = 0,
    val subDirs: MutableList<Directory> = mutableListOf()
)