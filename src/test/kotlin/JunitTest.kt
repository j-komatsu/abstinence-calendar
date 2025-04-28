package test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class JunitTest {
    @Test
    fun additionTest() {
        val result = 2 + 3
        assertEquals(5, result)
    }

    @Test
    fun stringEqualityTest() {
        val greeting = "Hello, Kotlin!"
        assertTrue(greeting.startsWith("Hello"))
    }
}
