package com.dieyteixeira.pontol.ui.screen

import android.os.Build
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dieyteixeira.pontol.R
import com.dieyteixeira.pontol.ui.components.DatePickerPonto
import com.dieyteixeira.pontol.ui.components.PeriodTime
import com.dieyteixeira.pontol.ui.components.TimePickerPonto
import com.dieyteixeira.pontol.ui.components.formattedTime
import com.dieyteixeira.pontol.ui.theme.Azul1
import com.dieyteixeira.pontol.ui.theme.Azul2
import com.dieyteixeira.pontol.ui.theme.AzulDegrade
import com.dieyteixeira.pontol.ui.theme.Verde

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InsertScreen() {

    val context = LocalContext.current

    val isInPreview = LocalInspectionMode.current
    val customFontFamily = if (isInPreview) {
        FontFamily.Default
    } else {
        FontFamily(Font(R.font.font_aller))
    }

    var selectedDay by remember { mutableStateOf(24) }
    var selectedMonth by remember { mutableStateOf(9) }
    var selectedYear by remember { mutableStateOf(2024) }

    var showCustomDatePicker by remember { mutableStateOf(false) }

    if (showCustomDatePicker) {
        DatePickerPonto(
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

    val timePickerStateP1Initial = rememberTimePickerState(is24Hour = true)
    val timePickerStateP1Final = rememberTimePickerState(is24Hour = true)
    val timePickerStateP2Initial = rememberTimePickerState(is24Hour = true)
    val timePickerStateP2Final = rememberTimePickerState(is24Hour = true)
    val timePickerStateP3Initial = rememberTimePickerState(is24Hour = true)
    val timePickerStateP3Final = rememberTimePickerState(is24Hour = true)
    val timePickerStateP4Initial = rememberTimePickerState(is24Hour = true)
    val timePickerStateP4Final = rememberTimePickerState(is24Hour = true)

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

        TimePickerPonto(
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
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White.copy(alpha = 0.3f),
                shape = RoundedCornerShape(15.dp)
            )
            .padding(15.dp, 8.dp, 15.dp, 15.dp)
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
                            text = selectedYear.toString(),
                            fontSize = 14.sp,
                            fontFamily = customFontFamily,
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
                            text = selectedDay.toString(),
                            fontSize = 38.sp,
                            fontFamily = customFontFamily,
                            color = Color.White
                        )
                        Text(
                            text = getMonthAbbreviation(selectedMonth),
                            fontSize = 25.sp,
                            fontFamily = customFontFamily,
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
                        fontFamily = customFontFamily,
                        color = Azul1
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 13.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = "+00h",
                            fontSize = 38.sp,
                            fontFamily = customFontFamily,
                            color = Color.White
                        )
                        Text(
                            text = "00min",
                            fontSize = 23.sp,
                            fontFamily = customFontFamily,
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
                                    color = Color.White,
                                    shape = RoundedCornerShape(100)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "8h48min",
                                fontSize = 14.sp,
                                fontFamily = customFontFamily,
                                color = Azul1,
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(15.dp))
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(100)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "1h47min",
                                fontSize = 14.sp,
                                fontFamily = customFontFamily,
                                color = Verde,
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        // Campos para Período 1
        PeriodTime(
            text = "MANHÃ",
            initialTime = initialTimeP1,
            finalTime = finalTimeP1,
            onInitialTimeClick = { currentPicker = Pair(1, true) },
            onFinalTimeClick = { currentPicker = Pair(1, false) }
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Campos para Período 2
        PeriodTime(
            text = "TARDE",
            initialTime = initialTimeP2,
            finalTime = finalTimeP2,
            onInitialTimeClick = { currentPicker = Pair(2, true) },
            onFinalTimeClick = { currentPicker = Pair(2, false) }
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Campos para Período 3
        PeriodTime(
            text = "EXTRA",
            initialTime = initialTimeP3,
            finalTime = finalTimeP3,
            onInitialTimeClick = { currentPicker = Pair(3, true) },
            onFinalTimeClick = { currentPicker = Pair(3, false) }
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Campos para Período 4
        PeriodTime(
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