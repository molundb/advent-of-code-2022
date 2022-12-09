package day09

import readInput
import kotlin.math.abs

fun main() {
    val input = readInput(parent = "src/day09", name = "Day09_input")

    println(solvePartOne(input))
    println(solvePartTwo(input))
}

private fun solvePartTwo(input: List<String>): Int {
    val startPoint = Point(0, 0)

    val tailVisitedPoints = mutableSetOf(startPoint)

    val points = MutableList(10) { startPoint.copy() }

    for (l in input) {
        val (direction, distance) = l.split(' ')

        for (i in 0 until distance.toInt()) {
            when (direction) {
                "R" -> points[0].x++
                "D" -> points[0].y++
                "L" -> points[0].x--
                "U" -> points[0].y--
            }
            updateTailPoints(points, tailVisitedPoints)

        }
    }
    return tailVisitedPoints.size
}

private fun updateTailPoints(
    points: MutableList<Point>,
    tailVisitedPoints: MutableSet<Point>
) {
    var prevPoint = points[0]

    for (j in 1 until points.size) {
        if (notAdjacent(prevPoint, points[j])) {
            if (prevPoint.x > points[j].x) {
                points[j].x++
            } else if (prevPoint.x < points[j].x) {
                points[j].x--
            }

            if (prevPoint.y > points[j].y) {
                points[j].y++
            } else if (prevPoint.y < points[j].y) {
                points[j].y--
            }

            if (j == points.lastIndex) {
                tailVisitedPoints.add(Point(points[j].x, points[j].y))
            }
        } else {
            break
        }
        prevPoint = points[j]
    }
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