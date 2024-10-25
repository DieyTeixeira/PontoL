package com.dieyteixeira.pontol.ui.screen

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dieyteixeira.pontol.ui.components.DatePickerCustom
import com.dieyteixeira.pontol.ui.components.PeriodTime
import com.dieyteixeira.pontol.ui.components.TimePickerCustom
import com.dieyteixeira.pontol.ui.components.formattedTime
import com.dieyteixeira.pontol.ui.theme.Azul1
import com.dieyteixeira.pontol.ui.theme.Azul2
import com.dieyteixeira.pontol.ui.theme.AzulDegrade
import com.dieyteixeira.pontol.ui.theme.Laranja
import com.dieyteixeira.pontol.ui.theme.Verde
import com.dieyteixeira.pontol.ui.theme.Verde2
import java.time.LocalDate
import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InsertScreen(
    customFontFamily: List<FontFamily>? = null
) {

    val selectedDateNow = remember { mutableStateOf(LocalDate.now()) }
    var selectedDay by remember { mutableStateOf(selectedDateNow.value.dayOfMonth) }
    var selectedMonth by remember { mutableStateOf(selectedDateNow.value.monthValue) }
    var selectedYear by remember { mutableStateOf(selectedDateNow.value.year) }

    var showCustomDatePicker by remember { mutableStateOf(false) }

    if (showCustomDatePicker) {
        DatePickerCustom(
            customFontFamily = customFontFamily,
            onDismissRequest = {
                showCustomDatePicker = false
            },
            onCancelClick = {
                showCustomDatePicker = false
            },
            onOKClick = { selectedDate ->
                val year = selectedDate.year
                val month = selectedDate.monthValue
                val day = selectedDate.dayOfMonth

                selectedDay = day
                selectedMonth = month
                selectedYear = year

                showCustomDatePicker = false
            }
        )
    }

    var initialTimeP1 by remember { mutableStateOf("") }
    var finalTimeP1 by remember { mutableStateOf("") }
    var initialTimeP2 by remember { mutableStateOf("") }
    var finalTimeP2 by remember { mutableStateOf("") }
    var initialTimeP3 by remember { mutableStateOf("") }
    var finalTimeP3 by remember { mutableStateOf("") }
    var initialTimeP4 by remember { mutableStateOf("") }
    var finalTimeP4 by remember { mutableStateOf("") }

    var initialMillisP1 by remember { mutableStateOf(0L) }
    var finalMillisP1 by remember { mutableStateOf(0L) }
    var calMillisP1 by remember { mutableStateOf(0L) }
    var initialMillisP2 by remember { mutableStateOf(0L) }
    var finalMillisP2 by remember { mutableStateOf(0L) }
    var calMillisP2 by remember { mutableStateOf(0L) }
    var initialMillisP3 by remember { mutableStateOf(0L) }
    var finalMillisP3 by remember { mutableStateOf(0L) }
    var calMillisP3 by remember { mutableStateOf(0L) }
    var initialMillisP4 by remember { mutableStateOf(0L) }
    var finalMillisP4 by remember { mutableStateOf(0L) }
    var calMillisP4 by remember { mutableStateOf(0L) }

    val timePickerStateP1Initial = rememberTimePickerState(is24Hour = true)
    val timePickerStateP1Final = rememberTimePickerState(is24Hour = true)
    val timePickerStateP2Initial = rememberTimePickerState(is24Hour = true)
    val timePickerStateP2Final = rememberTimePickerState(is24Hour = true)
    val timePickerStateP3Initial = rememberTimePickerState(is24Hour = true)
    val timePickerStateP3Final = rememberTimePickerState(is24Hour = true)
    val timePickerStateP4Initial = rememberTimePickerState(is24Hour = true)
    val timePickerStateP4Final = rememberTimePickerState(is24Hour = true)

    var sumMillisNormal by remember { mutableStateOf(0L) }
    val (totalHoursN, totalMinutesN) = convertMillisToHoursAndMinutes(sumMillisNormal)

    var sumMillisExtra by remember { mutableStateOf(0L) }
    val (totalHoursE, totalMinutesE) = convertMillisToHoursAndMinutes(sumMillisExtra)

    var sumMillisTotal by remember { mutableStateOf(0L) }
    val (totalHoursT, totalMinutesT) = convertMillisToHoursAndMinutes(sumMillisTotal)

    var currentPicker by remember { mutableStateOf<Pair<Int, Boolean>?>(null) }

    if (currentPicker != null) {
        val (periodo, isInitial) = currentPicker!!
        val timePickerState = when (periodo) {
            1 -> if (isInitial) timePickerStateP1Initial else timePickerStateP1Final
            2 -> if (isInitial) timePickerStateP2Initial else timePickerStateP2Final
            3 -> if (isInitial) timePickerStateP3Initial else timePickerStateP3Final
            4 -> if (isInitial) timePickerStateP4Initial else timePickerStateP4Final
            else -> timePickerStateP1Initial
        }

        TimePickerCustom(
            customFontFamily = customFontFamily,
            timeState = timePickerState,
            onDismissRequest = { currentPicker = null },
            onCancelClick = { currentPicker = null },
            onOKClick = {
                val selectedTime = formattedTime(timePickerState.hour, timePickerState.minute)
                when (periodo) {
                    1 -> if (isInitial) initialTimeP1 = selectedTime else finalTimeP1 = selectedTime
                    2 -> if (isInitial) initialTimeP2 = selectedTime else finalTimeP2 = selectedTime
                    3 -> if (isInitial) initialTimeP3 = selectedTime else finalTimeP3 = selectedTime
                    4 -> if (isInitial) initialTimeP4 = selectedTime else finalTimeP4 = selectedTime
                }
                currentPicker = null
            },
            onMiliClick = { milliseconds ->
                val selectedMillis = milliseconds
                when (periodo) {
                    1 -> if (isInitial) initialMillisP1 = selectedMillis else finalMillisP1 = selectedMillis
                    2 -> if (isInitial) initialMillisP2 = selectedMillis else finalMillisP2 = selectedMillis
                    3 -> if (isInitial) initialMillisP3 = selectedMillis else finalMillisP3 = selectedMillis
                    4 -> if (isInitial) initialMillisP4 = selectedMillis else finalMillisP4 = selectedMillis
                }
            }
        )
    }

    fun updateTotalMillis() {
        calMillisP1 =  finalMillisP1 - initialMillisP1
        calMillisP2 =  finalMillisP2 - initialMillisP2
        calMillisP3 =  finalMillisP3 - initialMillisP3
        calMillisP4 =  finalMillisP4 - initialMillisP4

        sumMillisNormal = calMillisP1 + calMillisP2
        sumMillisExtra = calMillisP3 + calMillisP4
        sumMillisTotal = sumMillisNormal + sumMillisExtra
    }

    // Atualiza o total sempre que timeMillis muda
    LaunchedEffect(finalMillisP1, finalMillisP2, finalMillisP3, finalMillisP4) {
        updateTotalMillis()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White.copy(alpha = 0.3f),
                shape = RoundedCornerShape(15.dp)
            )
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(top = 5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Divider(
                        color = Azul2,
                        modifier = Modifier
                            .padding(start = 30.dp, top = 12.dp)
                            .width(62.dp)
                            .height(1.dp)
                    )
                    Box(
                        modifier = Modifier
                            .rotate(-90f)
                            .background(
                                color = Azul2,
                                shape = RoundedCornerShape(100)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$selectedYear",
                            fontSize = 14.sp,
                            fontFamily = customFontFamily?.get(2),
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .padding(vertical = 1.dp, horizontal = 13.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(start = 48.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (selectedDay < 10) "0$selectedDay" else "$selectedDay",
                            fontSize = 38.sp,
                            fontFamily = customFontFamily?.get(2),
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = getMonthAbbreviation(selectedMonth),
                            fontSize = 25.sp,
                            fontFamily = customFontFamily?.get(2),
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    Box(
                        modifier = Modifier
                            .height(55.dp)
                            .padding(start = 95.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.DateRange,
                            contentDescription = "Date",
                            tint = Azul1,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    showCustomDatePicker = true
                                }
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .weight(3f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        text = "Horas Trabalhadas",
                        fontSize = 14.sp,
                        fontFamily = customFontFamily?.get(1),
                        color = Azul1
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 13.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = if (totalHoursT < 10) "+0${totalHoursT}h" else "+${totalHoursT}h",
                            fontSize = 38.sp,
                            fontFamily = customFontFamily?.get(2),
                            color = Color.White
                        )
                        Text(
                            text = if (totalMinutesT < 10) "0${totalMinutesT}min" else "${totalMinutesT}min",
                            fontSize = 23.sp,
                            fontFamily = customFontFamily?.get(2),
                            color = Color.White,
                            modifier = Modifier
                                .padding(bottom = 2.5.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 50.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Azul2,
                                    shape = RoundedCornerShape(100)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                            ) {
                                Text(
                                    text = if (totalHoursN < 10) "0${totalHoursN}h" else "${totalHoursN}h",
                                    fontSize = 14.sp,
                                    fontFamily = customFontFamily?.get(1),
                                    color = Color.White
                                )
                                Text(
                                    text = if (totalMinutesN < 10) "0${totalMinutesN}min" else "${totalMinutesN}min",
                                    fontSize = 14.sp,
                                    fontFamily = customFontFamily?.get(1),
                                    color = Color.White
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Verde2,
                                    shape = RoundedCornerShape(100)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                            ) {
                                Text(
                                    text = if (totalHoursE < 10) "0${totalHoursE}h" else "${totalHoursE}h",
                                    fontSize = 14.sp,
                                    fontFamily = customFontFamily?.get(1),
                                    color = Color.White
                                )
                                Text(
                                    text = if (totalMinutesE < 10) "0${totalMinutesE}min" else "${totalMinutesE}min",
                                    fontSize = 14.sp,
                                    fontFamily = customFontFamily?.get(1),
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Divider(
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        // Campos para Período 1
        PeriodTime(
            customFontFamily = customFontFamily,
            text = "MANHÃ",
            initialTime = initialTimeP1,
            finalTime = finalTimeP1,
            onInitialTimeClick = { currentPicker = Pair(1, true) },
            onFinalTimeClick = { currentPicker = Pair(1, false) }
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Campos para Período 2
        PeriodTime(
            customFontFamily = customFontFamily,
            text = "TARDE",
            initialTime = initialTimeP2,
            finalTime = finalTimeP2,
            onInitialTimeClick = { currentPicker = Pair(2, true) },
            onFinalTimeClick = { currentPicker = Pair(2, false) }
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Campos para Período 3
        PeriodTime(
            customFontFamily = customFontFamily,
            text = "EXTRA",
            initialTime = initialTimeP3,
            finalTime = finalTimeP3,
            onInitialTimeClick = { currentPicker = Pair(3, true) },
            onFinalTimeClick = { currentPicker = Pair(3, false) }
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Campos para Período 4
        PeriodTime(
            customFontFamily = customFontFamily,
            text = "EXTRA",
            initialTime = initialTimeP4,
            finalTime = finalTimeP4,
            onInitialTimeClick = { currentPicker = Pair(4, true) },
            onFinalTimeClick = { currentPicker = Pair(4, false) }
        )
    }
}

@Composable
fun getMonthAbbreviation(month: Int): String {
    return when (month) {
        1 -> "jan"
        2 -> "fev"
        3 -> "mar"
        4 -> "abr"
        5 -> "mai"
        6 -> "jun"
        7 -> "jul"
        8 -> "ago"
        9 -> "set"
        10 -> "out"
        11 -> "nov"
        12 -> "dez"
        else -> ""
    }
}

fun convertMillisToHoursAndMinutes(totalMillis: Long): Pair<Int, Int> {
    val hours = (totalMillis / 1000) / 3600 // 1 hora = 3600 segundos = 3.600.000 milissegundos
    val minutes = ((totalMillis / 1000) % 3600) / 60 // Resto da divisão por 3600 para obter os minutos
    return Pair(hours.toInt(), minutes.toInt())
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun Screen1Preview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulDegrade)
    ) {
        InsertScreen()
    }
}