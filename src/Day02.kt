fun main() {
    fun List<String>.dirSum(dir: String): Int {
        return filter { it.startsWith(dir) }.sumOf { it.split(" ")[1].toInt() }
    }

    fun part1(input: List<String>): Int {
        return input.dirSum("forward") * (input.dirSum("down") - input.dirSum("up"))
    }

    data class Pos(val aim: Int, val hor: Int, val dep: Int)

    fun part2(input: List<String>): Int {
        return input.asSequence()
            .map { it.split(" ") }
            .map { (a, b) -> a to b.toInt() }
            .fold(Pos(0, 0, 0)) { pos, (direction, value) ->
                when (direction) {
                    "forward" -> pos.run { copy(hor = hor + value, dep =  dep + aim * value) }
                    "up" -> pos.run { copy(aim = aim - value) }
                    "down" -> pos.run { copy(aim = aim + value) }
                    else -> error("???")
                }
            }.let { (_, h, d) -> h * d}
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
