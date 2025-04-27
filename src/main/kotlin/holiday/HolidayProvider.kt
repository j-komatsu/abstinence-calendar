package holiday

import java.time.LocalDate

object HolidayProvider {
    fun getHolidays(): List<LocalDate> {
        return listOf(
            // 2025年
            LocalDate.of(2025, 1, 1),   // 元日
            LocalDate.of(2025, 1, 13),  // 成人の日
            LocalDate.of(2025, 2, 11),  // 建国記念の日
            LocalDate.of(2025, 2, 23),  // 天皇誕生日
            LocalDate.of(2025, 2, 24),  // 振替休日（天皇誕生日）
            LocalDate.of(2025, 3, 20),  // 春分の日
            LocalDate.of(2025, 4, 29),  // 昭和の日
            LocalDate.of(2025, 5, 3),   // 憲法記念日
            LocalDate.of(2025, 5, 4),   // みどりの日
            LocalDate.of(2025, 5, 5),   // こどもの日
            LocalDate.of(2025, 5, 6),   // 振替休日（こどもの日）
            LocalDate.of(2025, 7, 21),  // 海の日
            LocalDate.of(2025, 8, 11),  // 山の日
            LocalDate.of(2025, 9, 15),  // 敬老の日
            LocalDate.of(2025, 9, 23),  // 秋分の日
            LocalDate.of(2025, 10, 13), // スポーツの日
            LocalDate.of(2025, 11, 3),  // 文化の日
            LocalDate.of(2025, 11, 23), // 勤労感謝の日
            LocalDate.of(2025, 11, 24), // 振替休日（勤労感謝の日）

            // 2026年
            LocalDate.of(2026, 1, 1),   // 元日
            LocalDate.of(2026, 1, 12),  // 成人の日
            LocalDate.of(2026, 2, 11),  // 建国記念の日
            LocalDate.of(2026, 2, 23),  // 天皇誕生日
            LocalDate.of(2026, 3, 20),  // 春分の日
            LocalDate.of(2026, 4, 29),  // 昭和の日
            LocalDate.of(2026, 5, 3),   // 憲法記念日
            LocalDate.of(2026, 5, 4),   // みどりの日
            LocalDate.of(2026, 5, 5),   // こどもの日
            LocalDate.of(2026, 7, 20),  // 海の日
            LocalDate.of(2026, 8, 11),  // 山の日
            LocalDate.of(2026, 9, 21),  // 敬老の日
            LocalDate.of(2026, 9, 23),  // 秋分の日
            LocalDate.of(2026, 10, 12), // スポーツの日
            LocalDate.of(2026, 11, 3),  // 文化の日
            LocalDate.of(2026, 11, 23), // 勤労感謝の日

            // 2027年
            LocalDate.of(2027, 1, 1),   // 元日
            LocalDate.of(2027, 1, 11),  // 成人の日
            LocalDate.of(2027, 2, 11),  // 建国記念の日
            LocalDate.of(2027, 2, 23),  // 天皇誕生日
            LocalDate.of(2027, 3, 21),  // 春分の日
            LocalDate.of(2027, 4, 29),  // 昭和の日
            LocalDate.of(2027, 5, 3),   // 憲法記念日
            LocalDate.of(2027, 5, 4),   // みどりの日
            LocalDate.of(2027, 5, 5),   // こどもの日
            LocalDate.of(2027, 5, 6),   // 振替休日（こどもの日）
            LocalDate.of(2027, 7, 19),  // 海の日
            LocalDate.of(2027, 8, 11),  // 山の日
            LocalDate.of(2027, 9, 20),  // 敬老の日
            LocalDate.of(2027, 9, 23),  // 秋分の日
            LocalDate.of(2027, 10, 11), // スポーツの日
            LocalDate.of(2027, 11, 3),  // 文化の日
            LocalDate.of(2027, 11, 23)  // 勤労感謝の日
        )
    }
}
