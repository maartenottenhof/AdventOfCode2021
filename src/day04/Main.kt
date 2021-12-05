package day04

import java.io.File

fun main() {
    val input = File("src/day04/input").readLines()
    val numbers = input[0].split(",")
        .map { it.toInt() }

    println(task1(numbers, createCards(input)))
    println(task2(numbers, createCards(input)))
}

private fun task1(numbers: List<Int>, cards: List<BingoCard>) = playBingo(numbers, cards).first()
    .let { it.getSumOfUncalledNumbers() * it.finalNumber!!.toInt() }

private fun task2(numbers: List<Int>, cards: List<BingoCard>) = playBingo(numbers, cards).last()
    .let { it.getSumOfUncalledNumbers() * it.finalNumber!!.toInt() }

private fun createCards(input: List<String>) = input.drop(2)
    .filter { it.isNotEmpty() }
    .map { it.trim().split(Regex("  *")).map { i -> i.toInt() } }
    .chunked(5)
    .map { BingoCard(it) }

private fun playBingo(numbers: List<Int>, cards: List<BingoCard>) = numbers.fold(listOf<BingoCard>()) { acc, s ->
    acc + (cards.mapNotNull { c ->
        c.checkNumber(s)
        if (c.getBingo() != null) c else null
    } subtract acc)
}

private class BingoCard(rows: List<List<Int>>) {
    private var rows: List<List<Pair<Int, Boolean>>>
    var finalNumber: Int? = null
        private set

    init {
        this.rows = rows.map { it.map { i -> i to false } }
    }

    fun checkNumber(number: Int) {
        if (finalNumber == null) {
            rows = rows.map { it.map { i -> if (i.first == number) i.first to true else i } }
            if (getBingo() != null) {
                finalNumber = number
            }
        }
    }

    fun getBingo(): List<Int>? {
        val v = rows.indices.map { i -> rows.indices.map { j -> rows[j][i] } }
        return (rows + v).firstOrNull { it.all { i -> i.second } }?.map { it.first }
    }

    fun getSumOfUncalledNumbers() = rows.flatten()
        .filter { !it.second }
        .sumOf { it.first }
}
