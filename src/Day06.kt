import java.math.BigInteger

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

private fun part1(input: List<String>) = simulate(input, 80)
private fun part2(input: List<String>) = simulate(input, 256)

private fun parse(input: List<String>): List<Int> {
    return input[0].split(",").map { it.toInt() }
}

private fun simulate(input: List<String>, days: Int): BigInteger {
    val f = Array(10) { i -> parse(input).count { it == i }.toBigInteger() }
    repeat(days) {
        for (x in f.indices) f[(x + f.size - 1) % f.size] = f[(x + f.size) % f.size]
        f[6] += f[9]
    }
    return f.sumOf { it } - f.last()
}

private object Day06Original {
    private fun part1(input: List<String>): Int {
        val parsed = parse(input).toMutableList()
        for (day in 0..79) {
            val additions = mutableListOf<Int>()
            for ((index, timer) in parsed.withIndex()) {
                when (timer) {
                    0 -> {
                        additions.add(8)
                        parsed[index] = 6
                    }
                    else -> parsed[index]--
                }
            }
            parsed += additions
        }
        return parsed.size
    }

    private fun part2(input: List<String>): BigInteger {
        var parsed = parse(input).groupingBy { it }.eachCount().toMutableMap()
            .mapValues { BigInteger.valueOf(it.value.toLong()) }
        for (day in 0..255) {
            println(parsed)
            val new = mutableMapOf<Int, BigInteger>()
            for ((timer, count) in parsed.entries) {
                if (timer == 0) {
                    new[8] = new.getOrDefault(8, BigInteger.ZERO).add(count)
                    new[6] = new.getOrDefault(6, BigInteger.ZERO).add(count)
                } else {
                    new[timer - 1] = new.getOrDefault(timer - 1, BigInteger.ZERO).add(count)
                }
            }
            parsed = new
        }
        return parsed.values.fold(BigInteger.ZERO) { acc, i -> acc.add(i) }
    }
}