package day05

import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    val lines = File("src/day05/input").readLines()
        .map { it.split(" -> ") }
        .map { it.map { i -> i.split(",").map { j -> j.toInt() } } }
        .map { (it[0][0] to it[0][1]) to (it[1][0] to it[1][1]) }

    println(task1(lines))
    println(task2(lines))
}

private fun task1(lines: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>) =
    lines.filter { it.first.first == it.second.first || it.first.second == it.second.second }
        .flatMap { calculateHorVert(it) }
        .groupingBy { it }
        .eachCount()
        .filter { it.value > 1 }
        .count()

private fun task2(lines: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>) =
    lines.flatMap {
        if (it.first.first == it.second.first || it.first.second == it.second.second) {
            calculateHorVert(it)
        } else {
            calculateDiagonal(it)
        }
    }
        .groupingBy { it }
        .eachCount()
        .filter { it.value > 1 }
        .count()

private fun calculateDiagonal(line: Pair<Pair<Int, Int>, Pair<Int, Int>>) =
    (0..abs(line.first.first - line.second.first)).map {
        if (line.first.first > line.second.first && line.first.second > line.second.second) {
            line.first.first - it to line.first.second - it
        } else if (line.first.first > line.second.first && line.first.second < line.second.second) {
            line.first.first - it to line.first.second + it
        } else if (line.first.first < line.second.first && line.first.second < line.second.second) {
            line.first.first + it to line.first.second + it
        } else {
            line.first.first + it to line.first.second - it
        }
    }

private fun calculateHorVert(line: Pair<Pair<Int, Int>, Pair<Int, Int>>) =
    if (line.first.first == line.second.first) {
        (min(line.first.second, line.second.second)..max(line.first.second, line.second.second))
            .map { line.first.first to it }
    } else {
        (min(line.first.first, line.second.first)..max(line.first.first, line.second.first))
            .map { it to line.first.second }
    }
