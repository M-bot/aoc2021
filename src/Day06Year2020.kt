fun main() {
    println("======================== Description ========================")
    day(readInput("Day06_desc"))
    println("=========================== Input ===========================")
    day(readInput("Day06"))
}

private fun day(input: List<String>) {
    println("Part 1 -> ${part1(input)}")
    println("Part 2 -> ${part2(input)}")
}

private fun part1(input: List<String>): Int {
    return input.joinToString("") { it.ifEmpty { "-" } }.split("-").map { it.toSet() }.sumOf { it.size }
}

private fun part2(input: List<String>): Int {
    val tes = input.joinToString("!") { it.ifEmpty { "-" } }.split("-").map { it.toList().groupingBy { it }.eachCount() }.sumOf {
        val lines = if(it['!'] == 1) 1 else it['!']!! - 1
        val count = it.count { (_, value) -> value == lines }
        if (it['!'] == 1) count - 1 else count
    }
    println(tes)
    return tes

}

