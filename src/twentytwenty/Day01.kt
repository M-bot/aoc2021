package twentytwenty

import readInput

fun main() {
    val desc = readInput("twentytwenty/day01desc").map { it.toInt() }
    val input = readInput("twentytwenty/day01").map { it.toInt() }

    println(desc.findPairSlow { it.first + it.second == 2020 }?.let { it.first * it.second })
    println(input.findPairSlow { it.first + it.second == 2020 }?.let { it.first * it.second })

    println(desc.findTripleSlow { it.first + it.second + it.third == 2020 }?.let { (a, b, c) -> a * b *c})
    println(input.findTripleSlow { it.first + it.second + it.third == 2020 }?.let { (a, b, c) -> a * b *c})
}

inline fun List<Int>.findPairSlow(predicate: (Pair<Int, Int>) -> Boolean): Pair<Int, Int>? {
    for (a in this) {
        for (b in this) {
            if (a == b) continue
            val pair = a to b
            if (predicate(pair)) return pair
        }
    }
    return null
}

inline fun List<Int>.findTripleSlow(predicate: (Triple<Int, Int, Int>) -> Boolean): Triple<Int, Int, Int>? {
    for (a in this) {
        for (b in this) {
            for (c in this) {
                if (a == b && b == c) continue
                val triple = Triple(a, b, c)
                if (predicate(triple)) return triple
            }
        }
    }
    return null
}
