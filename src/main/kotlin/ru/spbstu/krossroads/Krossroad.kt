package ru.spbstu.krossroads

import java.util.*

class Krossroad(
        val possibleWays: List<Way>
) {
    val allCells = possibleWays.flatMap { it.toList() }.toSet()

    val cars = mutableListOf<AbstractCar>()

    private val random = Random()
}