package ru.spbstu.krossroads

class BullCar(kross: Krossroad) : AbstractCar(kross) {
    override suspend fun awaitForFreePath(currentCell: Cell, currentDir: Direction) {}
}