fun main() {
    fun part1(input: List<Int>): Int {
        return input.zipWithNext().count { it.second > it.first }
    }

    fun part1(input: List<String>): Int {
        return part1(input.map { it.toInt() })
    }

    fun part2(input: List<String>): Int {
        return part1(input.map { it.toInt() }.windowed(3) { it.sum() })
    }

    fun part2alt(input: List<String>): Int {
        return input.map { it.toInt() }.windowed(4).count { it[0] < it[3] }
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
    println(part2alt(input))
}
