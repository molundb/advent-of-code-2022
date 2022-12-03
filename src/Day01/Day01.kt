fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_input_test")
//    check(part1(testInput) == 1)

    val input = readInput(parent = "src/Day01", name = "Day01_input")
    println(part1(input))
}
