package day03

import java.io.File

fun main() {
    val binary = File("src/day03/input").readLines()

    println(task1(binary))
}

private fun task1(binary: List<String>): Int {
    val gammaBinary = binary.fold(MutableList(binary[0].length) { 0 }) { acc, s ->
        s.forEachIndexed { i, c -> if (c == '1') acc[i] = acc[i] + 1 }
        acc
    }
        .map { if (it > binary.size / 2) '1' else '0' }
        .joinToString(separator = "")

    val epsilonBinary = gammaBinary.map { if (it == '1') '0' else '1' }.joinToString(separator = "")

    return gammaBinary.toInt(2) * epsilonBinary.toInt(2)
}
