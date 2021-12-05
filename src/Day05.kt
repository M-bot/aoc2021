fun main() {
    println("======================== Description ========================")
    day(readInput("Day05_desc"))
    println("=========================== Input ===========================")
    day(readInput("Day05"))
}

private fun day(input: List<String>) {
    println("Part 1 -> ${part1(input)}")
    println("Part 2 -> ${part2(input)}")
}

data class Line(val a: Int, val b: Int, val i: Int, val j: Int)

private fun parse(input: List<String>) = input.map { line ->
    val (x1, y1, x2, y2) = line.split(" -> |,".toRegex()).map { it.toInt() }
    Line(x1, y1, x2, y2)
}

private fun countPointsOfOverlap(i: List<Line>) = with(1000.let { n -> Array(n) { Array(n) { 0 } } }) {
    infix fun Int.s(o: Int) = when {
        this > o -> (this downTo o).asSequence()
        this < o -> (this..o).asSequence()
        else -> generateSequence { o }
    }
    for (l in i) (l.b s l.j zip (l.a s l.i)).forEach { (y, x) -> get(y)[x]++ }
    sumOf { it.count { it > 1 } }
}


private fun part1(input: List<String>) = countPointsOfOverlap(parse(input).filter { it.a == it.i || it.b == it.j })
private fun part2(input: List<String>) = countPointsOfOverlap(parse(input))


private object OriginalSolution {
    private fun parse(input: List<String>): List<List<List<Int>>> {
        return input.map { it.split(" -> ").map { it.split(",").map { it.toInt() } } }
    }

    private fun part1(input: List<String>): Int {
        val parsed = parse(input).filter { it[0][0] == it[1][0] || it[0][1] == it[1][1] }
        val array: Array<Array<Int>> = Array(1000) { Array(1000) { 0 } }
        for (line in parsed) {
            if (line[0][0] == line[1][0]) {
                val range = if (line[0][1] > line[1][1]) line[1][1]..line[0][1] else line[0][1]..line[1][1]
                for (i in range) {
                    array[i][line[0][0]] += 1
                }
            }
            if (line[0][1] == line[1][1]) {
                val range = if (line[0][0] > line[1][0]) line[1][0]..line[0][0] else line[0][0]..line[1][0]
                for (i in range) {
                    array[line[0][1]][i] += 1
                }
            }
        }
        for (ar in array) {
            for (a in ar) {
                print("$a ")
            }
            println()
        }
        return array.flatten().count { it > 1 }
    }

    private fun part2(input: List<String>): Int {
        val parsed = parse(input)
        val array: Array<Array<Int>> = Array(1000) { Array(1000) { 0 } }
        for (line in parsed) {
            if (line[0][0] == line[1][0]) {
                val range = if (line[0][1] > line[1][1]) line[1][1]..line[0][1] else line[0][1]..line[1][1]
                for (i in range) {
                    array[i][line[0][0]] += 1
                }
            } else if (line[0][1] == line[1][1]) {
                val range = if (line[0][0] > line[1][0]) line[1][0]..line[0][0] else line[0][0]..line[1][0]
                for (i in range) {
                    array[line[0][1]][i] += 1
                }
            } else if (line[0][0] < line[1][0] && line[0][1] < line[1][1]) {
                val rangeY = if (line[0][1] > line[1][1]) line[1][1]..line[0][1] else line[0][1]..line[1][1]
                val rangeX = if (line[0][0] > line[1][0]) line[1][0]..line[0][0] else line[0][0]..line[1][0]
                for (i in rangeY.zip(rangeX)) {
                    array[i.first][i.second] += 1
                }
            } else {
//            println(line)
                val rangeY = if (line[0][1] > line[1][1]) line[0][1] downTo line[1][1] else line[0][1]..line[1][1]
                val rangeX = if (line[0][0] > line[1][0]) line[0][0] downTo line[1][0] else line[0][0]..line[1][0]
//            println(rangeX)
//            println(rangeY)
                for (i in rangeY.zip(rangeX)) {
                    array[i.first][i.second] += 1
                }
            }
        }
//    for (ar in array) {
//        for (a in ar) {
//            print("$a ")
//        }
//        println()
//    }
        return array.flatten().count { it > 1 }
    }
}