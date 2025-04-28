package test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.YearMonth
import generateCalendar
import generateDateFromIndex

class CalendarFunctionTest {

    @Test
    fun `generateCalendar should generate correct number of days`() {
        val yearMonth = YearMonth.of(2025, 4) // 2025年4月
        val calendar = generateCalendar(yearMonth)

        // 4月は30日 → 空白含めて 5週間=35マス（5x7）
        assertEquals(35, calendar.size)
        assertTrue(calendar.contains("1"))
        assertTrue(calendar.contains("30"))
    }

    @Test
    fun `generateCalendar should start with blanks if month doesn't start on Sunday`() {
        val yearMonth = YearMonth.of(2025, 5) // 2025年5月
        val calendar = generateCalendar(yearMonth)

        // 2025/5/1は木曜日 → 日月火水の空白が最初に必要
        assertEquals("", calendar[0]) // 日
        assertEquals("", calendar[1]) // 月
        assertEquals("", calendar[2]) // 火
        assertEquals("", calendar[3]) // 水
        assertEquals("1", calendar[4]) // 木（1日）
    }

    @Test
    fun `generateDateFromIndex should return correct LocalDate`() {
        val yearMonth = YearMonth.of(2025, 4) // 2025年4月
        val date = generateDateFromIndex(yearMonth, weekIndex = 0, dayIndex = 2)

        // 2025/4/1は火曜日
        assertEquals(LocalDate.of(2025, 4, 1), date)
    }

    @Test
    fun `generateDateFromIndex should return null for invalid date`() {
        val yearMonth = YearMonth.of(2025, 2) // 2025年2月
        val date = generateDateFromIndex(yearMonth, weekIndex = 5, dayIndex = 6)

        // 2月は28日まで → 無効
        assertNull(date)
    }
}
