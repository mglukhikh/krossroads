package ru.spbstu.krossroads

enum class Direction(val dx: Int, val dy: Int) {
    NORTH(0, -1),
    SOUTH(0, 1),
    WEST(-1, 0),
    EAST(1, 0)
}

data class Cell(val x: Int, val y: Int) {
    fun move(dir: Direction) = Cell(x + dir.dx, y + dir.dy)
}

data class Line(val start: Cell, val end: Cell) : Sequence<Cell> {

    val dir: Direction = when {
        start.x == end.x -> if (end.y > start.y) Direction.EAST else Direction.WEST
        start.y == end.y -> if (end.x > start.x) Direction.SOUTH else Direction.NORTH
        else -> throw AssertionError("Incorrect line: $start to $end")
    }

    override fun iterator(): Iterator<Cell> {
        return CellIterator()
    }

    inner class CellIterator : Iterator<Cell> {
        var nextCell: Cell? = start

        override fun hasNext(): Boolean = nextCell != null

        override fun next(): Cell {
            val result = nextCell ?: throw NoSuchElementException()
            nextCell = if (result == end) null else result.move(dir)
            return result
        }
    }
}