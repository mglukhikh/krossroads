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

typealias Way = Sequence<Cell>

data class Line(val start: Cell, val end: Cell) : Way {

    val dir: Direction = when {
        start.x == end.x -> if (end.y > start.y) Direction.SOUTH else Direction.NORTH
        start.y == end.y -> if (end.x > start.x) Direction.EAST else Direction.WEST
        else -> throw AssertionError("Incorrect line: $start to $end")
    }

    override fun iterator(): Iterator<Cell> {
        return CellIterator()
    }

    inner class CellIterator : Iterator<Cell> {
        private var nextCell: Cell? = start

        override fun hasNext(): Boolean = nextCell != null

        override fun next(): Cell {
            val result = nextCell ?: throw NoSuchElementException()
            nextCell = if (result == end) null else result.move(dir)
            return result
        }
    }
}

data class Curve(val start: Cell, val turn: Cell, val end: Cell) : Way {

    val first = Line(start, turn)
    val second = Line(turn, end)

    override fun iterator(): Iterator<Cell> {
        return CompositeIterator()
    }

    inner class CompositeIterator : Iterator<Cell> {
        private val firstIterator = first.iterator()
        private val secondIterator = second.iterator()

        init {
            secondIterator.next()
        }

        override fun hasNext(): Boolean = firstIterator.hasNext() || secondIterator.hasNext()

        override fun next(): Cell = if (firstIterator.hasNext()) firstIterator.next() else secondIterator.next()
    }
}