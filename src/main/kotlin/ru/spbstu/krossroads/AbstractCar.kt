package ru.spbstu.krossroads

abstract class AbstractCar(private val kross: Krossroad) {
    val index = ++carCounter

    var started = false

    var finished = false

    var currentCell: Cell? = null

    protected val otherCars: List<AbstractCar> = kross.cars

    protected val krossroadCells: Set<Cell> = kross.allCells

    suspend fun run(way: Way) {
        if (started) {
            throw AssertionError("Car $index already started")
        }
        kross.cars += this
        started = true
        var prev: Cell? = null
        for (cell in way) {
            currentCell = cell
            println("Car $index on cell $cell")
            if (prev != null) {
                val dir = Line(prev, cell).dir
                awaitForFreePath(cell, dir)
            }
            prev = cell
        }
        currentCell = null
        finished = true
    }

    abstract suspend fun awaitForFreePath(currentCell: Cell, currentDir: Direction)

    companion object {
        private var carCounter = 0
    }
}