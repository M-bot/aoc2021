fun main() {
    val input = readInput("Day03")
    println(part1(input))
    println(part1alt(input))
    val oxygen = getRating(input, 0) { (zero, one) -> zero.size > one.size }.toInt(2)
    val co2 = getRating(input, 0) { (zero, one) -> zero.size <= one.size }.toInt(2)
    println(oxygen * co2)
}


fun part1(input: List<String>): Int {
    val length = input[0].length
    return input.flatMap { it.toList().withIndex() }.groupingBy { it.index }.fold(0 to 0) { accumulator, element ->
        if (element.value == '0') {
            accumulator.let { it.copy(first = it.first + 1) }
        } else {
            accumulator.let { it.copy(second = it.second + 1) }
        }
    }.map { (_, value) ->
        if (value.first > value.second) 0 else 1
    }.fold(0) { acc, i -> (acc shl 1) + i }.let { it * (it xor ((1 shl length) - 1)) }
}

fun part1alt(input: List<String>): Int {
     val a  = input.flatMap { it.toList().withIndex() }.groupingBy { it.index }
    println(a)
    return 0
}

fun getRating(input: List<String>, index: Int, criteria: (pair: Pair<List<String>, List<String>>) -> Boolean): String {
    if (input.size == 1) return input[0]
    return input
        .partition { it[index] == '0' }
        .let { if (criteria(it)) it.first else it.second }
        .let { getRating(it, index + 1, criteria) }
}
