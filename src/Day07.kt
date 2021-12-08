import kotlin.math.abs

fun main() {
    println("======================== Description ========================")
    day(readInput("Day07_desc"))
    println("=========================== Input ===========================")
    day(readInput("Day07"))
}

private fun day(input: List<String>) {
    println("Part 1 -> ${part1(input)}")
    println("Part 2 -> ${part2(input)}")
}

private fun part1(input: List<String>): Int {
    val parsed = input[0].split(",").map { it.toInt() }

    return (parsed.range()).map { i -> parsed.sumOf { abs(i - it) } }.min()
}

private fun part2(input: List<String>): Int {
    val parsed = input[0].split(",").map { it.toInt() }

    return (parsed.range()).map { i -> parsed.map { abs(i - it) }.sumOf { it * (it + 1) / 2 } }.min()
}

private fun List<Int>.range(): IntRange = min()..max()
private fun List<Int>.min(): Int = minOf { it }
private fun List<Int>.max(): Int = maxOf { it }

