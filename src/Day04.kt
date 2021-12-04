fun main() {
    println("======================== Description ========================")
    day(readInput("Day04_desc"))
    println("=========================== Input ===========================")
    day(readInput("Day04"))
}

private fun day(input: List<String>) {
    println("Part 1 -> ${part1(input)}")
    println("Part 2 -> ${part2(input)}")
}

private fun shared(input: List<String>): Iterable<Pair<Int, Int>> {
    val numbers = input[0].split(",").map { it.toInt() }
    val boards = input.drop(1)
        .chunked(6)
        .map {
            it.drop(1)
                .map {
                    it.split(" +".toRegex())
                        .filterNot { it.isEmpty() }
                        .map { it.toInt() }
                }
        }
    return boards.map { getFinishTime(it, numbers) }
}

private fun part1(input: List<String>): Int {
    val finishTimes = shared(input)
    return finishTimes.minByOrNull { it.first }?.second ?: -1
}

private fun part2(input: List<String>): Int {
    val finishTimes = shared(input)
    return finishTimes.maxByOrNull { it.first }?.second ?: -1
}

private fun getFinishTime(board: List<List<Int>>, numbers: List<Int>): Pair<Int, Int> {
    val marks = mutableListOf<MutableList<Boolean>>()
    for (i in board.indices) {
        marks.add(mutableListOf())
        for (j in board.indices) {
            marks[i].add(false)
        }
    }
    for ((index, number) in numbers.withIndex()) {
        for (i in board.indices) {
            for (j in board.indices) {
                if (board[i][j] == number) {
                    marks[i][j] = true
                    if (checkBoard(marks)) {
                        val sum = getUnmarkedSum(board, marks)
                        return index to sum * number
                    }
                }
            }
        }
    }
    return board.size to 0
}

private fun getUnmarkedSum(board: List<List<Int>>, marks: List<List<Boolean>>): Int {
    var sum = 0
    for (i in board.indices) {
        for (j in board.indices) {
            if(!marks[i][j]) {
                sum += board[i][j]
            }
        }
    }
    return sum
}

private fun checkBoard(board: List<List<Boolean>>): Boolean {
    if (board.any { it.all { it } }) return true
    val transpose = mutableMapOf<Int, MutableList<Boolean>>()
    for (row in board ) {
        for((index, mark) in row.withIndex()) {
            transpose.getOrPut(index) { mutableListOf() }.add(mark)
        }
    }
    if (transpose.any { it.value.all { it } }) return true
    return false
}
