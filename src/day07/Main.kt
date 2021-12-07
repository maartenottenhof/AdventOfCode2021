package day07

import java.io.File
import kotlin.math.abs

fun main() {
    val crabs = File("src/day07/input").readLines()
        .map { it.split(",").map { i -> i.toInt() } }
        .first()

    println(task1(crabs))
    println(task2(crabs))
}

private fun task1(crabs: List<Int>) = crabs.minOrNull()!!.until(crabs.maxOrNull()!!)
    .minOfOrNull { crabs.sumOf { i -> abs(it - i) } }

private fun task2(crabs: List<Int>) = crabs.minOrNull()!!.until(crabs.maxOrNull()!!)
    .minOfOrNull { crabs.map { i -> abs(it - i) }.sumOf { i -> i * (i + 1) / 2 } }
