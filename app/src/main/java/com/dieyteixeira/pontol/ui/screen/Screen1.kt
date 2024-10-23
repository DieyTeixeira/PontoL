package com.dieyteixeira.pontol.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dieyteixeira.pontol.R
import com.dieyteixeira.pontol.ui.components.PeriodTime
import com.dieyteixeira.pontol.ui.components.TimePickerDialog
import com.dieyteixeira.pontol.ui.components.formattedTime
import com.dieyteixeira.pontol.ui.theme.Azul1
import com.dieyteixeira.pontol.ui.theme.Azul3

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Screen1() {

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

    var currentPicker by remember { mutableStateOf<Pair<Int, Boolean>?>(null) } // Par (Período, éHoraInicial)

    if (currentPicker != null) {
        val (periodo, isInitial) = currentPicker!!
        val timePickerState = when (periodo) {
            1 -> if (isInitial) timePickerStateP1Initial else timePickerStateP1Final
            2 -> if (isInitial) timePickerStateP2Initial else timePickerStateP2Final
            3 -> if (isInitial) timePickerStateP3Initial else timePickerStateP3Final
            4 -> if (isInitial) timePickerStateP4Initial else timePickerStateP4Final
            else -> timePickerStateP1Initial // Fallback
        }

        TimePickerDialog(
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
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = "",
                onValueChange = {},
                textStyle = TextStyle.Default.copy(
                    fontSize = 16.sp,
                    lineHeight = 8.sp,
                    color = Color.Gray
                ),
                shape = RoundedCornerShape(15.dp),
                placeholder = {
                    Text(
                        text = "Data do lançamento",
                        fontSize = 16.sp,
                        lineHeight = 8.sp,
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledIndicatorColor = Color.Gray,
                    errorIndicatorColor = Color.Red
                )
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .width(45.dp)
                    .height(45.dp)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(15.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    contentDescription = "Date",
                    tint = Azul1,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        // Campos para Período 1
        PeriodTime(
            icon = painterResource(id = R.drawable.txt_manha),
            colorIcon = Color.White,
            colorText = Azul1,
            initialTime = initialTimeP1,
            finalTime = finalTimeP1,
            onInitialTimeClick = { currentPicker = Pair(1, true) },
            onFinalTimeClick = { currentPicker = Pair(1, false) }
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Campos para Período 2
        PeriodTime(
            icon = painterResource(id = R.drawable.txt_tarde),
            colorIcon = Color.White,
            colorText = Azul1,
            initialTime = initialTimeP2,
            finalTime = finalTimeP2,
            onInitialTimeClick = { currentPicker = Pair(2, true) },
            onFinalTimeClick = { currentPicker = Pair(2, false) }
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Campos para Período 3
        PeriodTime(
            icon = painterResource(id = R.drawable.txt_extra),
            colorIcon = Color.Black,
            colorText = Azul3,
            initialTime = initialTimeP3,
            finalTime = finalTimeP3,
            onInitialTimeClick = { currentPicker = Pair(3, true) },
            onFinalTimeClick = { currentPicker = Pair(3, false) }
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Campos para Período 4
        PeriodTime(
            icon = painterResource(id = R.drawable.txt_extra),
            colorIcon = Color.Black,
            colorText = Azul3,
            initialTime = initialTimeP4,
            finalTime = finalTimeP4,
            onInitialTimeClick = { currentPicker = Pair(4, true) },
            onFinalTimeClick = { currentPicker = Pair(4, false) }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun Screen1Preview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Screen1()
    }
}