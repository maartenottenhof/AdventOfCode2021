package day06

import java.io.File

fun main() {
    val lanterns = File("src/day06/input").readLines()
        .map { it.split(",").map { i -> i.toInt() } }
        .first()

    println(task1(lanterns))
    println(task2(lanterns))
}

private fun task1(lanterns: List<Int>) = calculateLanterns(80, lanterns)

private fun task2(lanterns: List<Int>) = calculateLanterns(256, lanterns)

private fun calculateLanterns(days: Int, lanterns: List<Int>) =
    generateSequence(toLanternsPerDayToGrow(lanterns)) {
        it.subList(1, it.size)
            .apply { set(6, get(6) + it[0]) }
            .apply { add(it[0]) }
    }
        .elementAt(days)
        .sum()

private fun toLanternsPerDayToGrow(lanterns: List<Int>) =
    lanterns.fold(MutableList(9) { 0L }) { acc, i -> acc.apply { set(i, get(i) + 1) } }
