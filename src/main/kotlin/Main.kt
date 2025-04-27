import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import holiday.HolidayProvider
import java.time.LocalDate
import java.time.YearMonth
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.rememberWindowState
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.File


@Composable
@Preview
fun App() {
    var currentYearMonth by remember { mutableStateOf(YearMonth.now()) }
    //val abstinenceStatus = remember { mutableStateMapOf<LocalDate, Boolean?>() }
    val abstinenceStatus = remember {
        mutableStateMapOf<LocalDate, Boolean?>().apply {
            putAll(loadAbstinenceStatus()) // 👈 保存データを読み込む！
        }
    }
    val holidays = HolidayProvider.getHolidays()
    val today = LocalDate.now()
    val days = generateCalendar(currentYearMonth)

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // 月移動ボタン
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "◀ 前月",
                modifier = Modifier
                    .clickable { currentYearMonth = currentYearMonth.minusMonths(1) }
                    .padding(8.dp),
                color = Color(0xFF6200EE),
                fontSize = 16.sp
            )
            Text(
                text = "今月",
                modifier = Modifier
                    .clickable { currentYearMonth = YearMonth.now() }
                    .padding(8.dp),
                color = Color(0xFF6200EE),
                fontSize = 16.sp
            )
            Text(
                text = "翌月 ▶",
                modifier = Modifier
                    .clickable { currentYearMonth = currentYearMonth.plusMonths(1) }
                    .padding(8.dp),
                color = Color(0xFF6200EE),
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 年月表示
        Text(
            text = "${currentYearMonth.year}年 ${currentYearMonth.monthValue}月",
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 曜日ヘッダー
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            listOf("日", "月", "火", "水", "木", "金", "土").forEach {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(56.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // カレンダー日付
        days.chunked(7).forEachIndexed { weekIndex, week ->
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                week.forEachIndexed { dayIndex, dayText ->
                    val date = generateDateFromIndex(currentYearMonth, weekIndex, dayIndex)

                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(56.dp)
                            .background(
                                color = when {
                                    date == today -> Color(0xFFCCE4FF) // 今日ハイライト（薄い青）
                                    else -> Color(0xFFF8F8F8)
                                },
                                shape = RoundedCornerShape(10.dp)
                            )
                            .let { modifier ->
                                if (dayText.isNotBlank() && date != null) {
                                    modifier.clickable {
                                        abstinenceStatus[date] = when (abstinenceStatus[date]) {
                                            null -> true
                                            true -> false
                                            false -> null
                                        }
                                        saveAbstinenceStatus(abstinenceStatus) // 👈 禁酒記録をすぐ保存！
                                    }
                                } else {
                                    modifier // 空白セルはクリック不可
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (dayText.isNotBlank() && date != null) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = dayText,
                                    color = when {
                                        holidays.contains(date) -> Color.Red
                                        dayIndex == 0 -> Color.Red
                                        dayIndex == 6 -> Color.Blue
                                        else -> Color.Black
                                    },
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                when (abstinenceStatus[date]) {
                                    true -> Text(
                                        text = "○",
                                        color = Color.Green,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                    false -> Text(
                                        text = "×",
                                        color = Color.Red,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                    null -> {}
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// カレンダー日付生成
fun generateCalendar(yearMonth: YearMonth): List<String> {
    val days = mutableListOf<String>()

    val firstDay = yearMonth.atDay(1)
    val dayOfWeekValue = (firstDay.dayOfWeek.value % 7) // 日曜=0

    repeat(dayOfWeekValue) { days.add("") }

    for (day in 1..yearMonth.lengthOfMonth()) {
        days.add(day.toString())
    }

    while (days.size % 7 != 0) {
        days.add("")
    }

    return days
}

// インデックスからLocalDate復元
fun generateDateFromIndex(yearMonth: YearMonth, weekIndex: Int, dayIndex: Int): LocalDate? {
    val firstDay = yearMonth.atDay(1)
    val offset = (firstDay.dayOfWeek.value % 7)
    val dayNumber = weekIndex * 7 + dayIndex - offset + 1

    return if (dayNumber in 1..yearMonth.lengthOfMonth()) {
        yearMonth.atDay(dayNumber)
    } else {
        null
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Abstinence Calendar",
        state = rememberWindowState(
            size = DpSize(600.dp, 650.dp) // ✅これでウィンドウ自体を大きくする！！
        ),
        resizable = true
    ) {
        App()
    }
}

// データクラス（シリアライズ用）
@Serializable
data class AbstinenceRecord(
    val date: String,
    val status: Boolean?
)

// 保存関数
fun saveAbstinenceStatus(map: Map<LocalDate, Boolean?>) {
    val recordList = map.map { (date, status) ->
        AbstinenceRecord(date.toString(), status)
    }
    val json = Json.encodeToString(recordList)
    File("abstinence_record.json").writeText(json)
}

// 読み込み関数
fun loadAbstinenceStatus(): Map<LocalDate, Boolean?> {
    val file = File("abstinence_record.json")
    if (!file.exists()) return emptyMap()

    val json = file.readText()
    val recordList = Json.decodeFromString<List<AbstinenceRecord>>(json)
    return recordList.associate { LocalDate.parse(it.date) to it.status }
}