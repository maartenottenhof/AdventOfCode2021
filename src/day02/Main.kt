package day02

import java.io.File

fun main() {
    val directions = File("src/day02/input").readLines()
        .map { it.split(" ") }
        .map { it[0] to it[1].toInt() }

    println(task1(directions))
    println(task2(directions))
}

private fun task1(directions: List<Pair<String, Int>>) = directions.fold(0 to 0) { acc, pair ->
    when (pair.first) {
        "up" -> acc.copy(second = acc.second - pair.second)
        "down" -> acc.copy(second = acc.second + pair.second)
        else -> acc.copy(first = acc.first + pair.second)
    }
}.let { it.first * it.second }

private fun task2(directions: List<Pair<String, Int>>) = directions.fold(Triple(0, 0, 0)) { acc, pair ->
    when (pair.first) {
        "up" -> acc.copy(third = acc.third - pair.second)
        "down" -> acc.copy(third = acc.third + pair.second)
        else -> acc.copy(first = acc.first + pair.second, second = acc.second + acc.third * pair.second)
    }
}.let { it.first * it.second }
