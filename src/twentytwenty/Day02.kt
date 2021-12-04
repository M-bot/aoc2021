package twentytwenty

import readInput

data class Input(val range: IntRange, val required: Char, val password: String)

fun parse(input: String): Input {
    val parts = input.split(" ")
    val range = parts[0].split("-")
    return Input(IntRange(range[0].toInt(), range[1].toInt()), parts[1][0], parts[2])
}

fun Input.isValid(): Boolean {
    return password.groupingBy { it }.eachCount()[required]?.let { it in range } == true
}

fun Input.isValid2(): Boolean {
    return (password[range.first - 1] == required) xor (password[range.last - 1] == required)
}

fun main() {
    val desc = readInput("twentytwenty/day02desc").map { parse(it) }
    val input = readInput("twentytwenty/day02").map { parse(it) }

    println(desc.count { it.isValid() })
    println(desc.count { it.isValid2() })
    println("^ desc - input V")
    println(input.count { it.isValid() })
    println(input.count { it.isValid2() })
}
