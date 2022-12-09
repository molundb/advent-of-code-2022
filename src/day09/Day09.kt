package day09

import readInput
import kotlin.math.abs

fun main() {
    val input = readInput(parent = "src/day09", name = "Day09_input")

    println(solvePartOne(input))
}

private fun solvePartOne(input: List<String>): Int {
    val startPoint = Point(0, 0)
    val currHeadPoint = startPoint.copy()
    val currTailPoint = startPoint.copy()

    val tailVisitedPoints = mutableSetOf(startPoint)

    for (l in input) {
        val (direction, distance) = l.split(' ')

        for (i in 0 until distance.toInt()) {
            when (direction) {
                "R" -> {
                    currHeadPoint.x++

                    if (notAdjacent(currHeadPoint, currTailPoint)) {
                        currTailPoint.x++
                        currTailPoint.y = currHeadPoint.y

                        tailVisitedPoints.add(Point(currTailPoint.x, currTailPoint.y))
                    }
                }

                "D" -> {
                    currHeadPoint.y++

                    if (notAdjacent(currHeadPoint, currTailPoint)) {
                        currTailPoint.y++
                        currTailPoint.x = currHeadPoint.x

                        tailVisitedPoints.add(Point(currTailPoint.x, currTailPoint.y))
                    }
                }

                "L" -> {
                    currHeadPoint.x--

                    if (notAdjacent(currHeadPoint, currTailPoint)) {
                        currTailPoint.x--
                        currTailPoint.y = currHeadPoint.y

                        tailVisitedPoints.add(Point(currTailPoint.x, currTailPoint.y))
                    }
                }

                "U" -> {
                    currHeadPoint.y--

                    if (notAdjacent(currHeadPoint, currTailPoint)) {
                        currTailPoint.y--
                        currTailPoint.x = currHeadPoint.x

                        tailVisitedPoints.add(Point(currTailPoint.x, currTailPoint.y))
                    }
                }
            }
        }
    }
    return tailVisitedPoints.size
}

private fun notAdjacent(currHeadPoint: Point, currTailPoint: Point) =
    abs(currHeadPoint.x - currTailPoint.x) > 1 || abs(currHeadPoint.y - currTailPoint.y) > 1

data class Point(
    var x: Int,
    var y: Int,
)