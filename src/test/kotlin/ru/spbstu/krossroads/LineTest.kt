package ru.spbstu.krossroads

import org.junit.Assert.assertEquals
import org.junit.Test

class LineTest {
    @Test
    fun testIterator() {
        assertEquals(6, Line(Cell(3, 3), Cell(3, 8)).count())
        assertEquals(5, Line(Cell(7, 3), Cell(3, 3)).count())
        assertEquals(1, Line(Cell(7, 5), Cell(7, 5)).count())
        assertEquals(3, Line(Cell(1, 2), Cell(3, 2)).count())
        assertEquals(4, Line(Cell(2, 0), Cell(2, -3)).count())
    }

    @Test
    fun testCurveIterator() {
        assertEquals(7, Curve(Cell(5, 5), Cell(1, 5), Cell(1, 7)).count())
    }
}