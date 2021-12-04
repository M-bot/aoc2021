package twentytwenty

import readInput


fun main() {
    val desc = readInput("twentytwenty/day03desc")
    val input = readInput("twentytwenty/day03")

    println(one(desc))
    println(one(input))
    println(two(desc))
    println(two(input))
}

private fun one(input: List<String>): Int {
    var x = 0
    return input.drop(1).count {
        x = (x + 3) % it.length
        it[x] == '#'
    }
}

private fun two(input: List<String>): Int {
    fun slope(right: Int, down: Int = 1): Int {
        var x = 0
        val t = input.chunked(down).mapNotNull { if (it.size == down) it[down - 1] else null }
        val a = if (down == 1) input.drop(1) else t
        return a.count {
            x = (x + right) % it.length
            it[x] == '#'
        }
    }

    return slope(1) * slope(3) * slope(5) * slope(7) * slope(1, 2)
}
