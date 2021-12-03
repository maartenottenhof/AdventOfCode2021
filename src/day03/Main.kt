package day03

import java.io.File

fun main() {
    val binary = File("src/day03/input").readLines()

    println(task1(binary))
    println(task2(binary))
}

private fun task1(binary: List<String>): Int {
    val gammaBinary = (0 until binary[0].length).map { binary.count { s -> s[it] == '1' } }
        .joinToString(separator = "") { if (it > binary.size / 2) "1" else "0" }
    val epsilonBinary = gammaBinary.map { if (it == '1') '0' else '1' }
        .joinToString(separator = "")

    return gammaBinary.toInt(2) * epsilonBinary.toInt(2)
}

private fun task2(binary: List<String>) =
    determineRating(binary) { b, i -> b.count { it[i] == '1' } >= b.size.toFloat() / 2 } *
            determineRating(binary) { b, i -> b.count { it[i] == '0' } > b.size.toFloat() / 2 }

private fun determineRating(binary: List<String>, index: Int = 0, b: (List<String>, Int) -> Boolean): Int {
    val common = if (b(binary, index)) '1' else '0'
    val filtered = binary.filter { it[index] == common }

    return if (filtered.size == 1) {
        filtered[0].toInt(2)
    } else determineRating(filtered, index + 1, b)
}
