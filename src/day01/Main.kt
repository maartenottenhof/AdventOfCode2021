import java.io.File

fun main() {
    val measurements = File("src/day01/input").readLines()
        .map(String::toInt)

    println(task1(measurements))
    println(task2(measurements))
}

private fun task1(measurements: List<Int>) = measurements.zipWithNext().count { it.second > it.first }

private fun task2(measurements: List<Int>) = task1(measurements.windowed(3).map { it.sum() })
