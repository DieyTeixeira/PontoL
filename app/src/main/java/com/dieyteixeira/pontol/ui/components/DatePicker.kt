package com.dieyteixeira.pontol.ui.components

import android.app.DatePickerDialog
import android.os.Build
import android.view.ContextThemeWrapper
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import com.dieyteixeira.pontol.R
import com.dieyteixeira.pontol.ui.theme.Azul1
import com.dieyteixeira.pontol.ui.theme.Azul2
import java.time.LocalDate
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerPonto(
    onDismissRequest: () -> Unit,
    onCancelClick: () -> Unit,
    onOKClick: (LocalDate) -> Unit
) {

    val calendar = Calendar.getInstance()

    val isInPreview = LocalInspectionMode.current
    val customFontFamily = if (isInPreview) {
        FontFamily.Default
    } else {
        FontFamily(Font(R.font.font_aller))
    }

    val selDate = remember { mutableStateOf(LocalDate.now()) }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = true)
    ) {
        ElevatedCard(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.extraLarge),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = "Selecione a data",
                    fontFamily = customFontFamily,
                )
                Spacer(modifier = Modifier.height(5.dp))

                CustomCalendarView(onDateSelected = {
                    selDate.value = it
                })

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    TextButton(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .height(35.dp),
                        onClick = onCancelClick
                    ) {
                        Text(
                            text = "Cancel",
                            color = Azul1,
                            fontFamily = customFontFamily
                        )
                    }

                    Button(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .height(35.dp),
                        onClick = {
                            onOKClick(selDate.value)
                            onDismissRequest()
                        }
                    ) {
                        Text(
                            text = "OK",
                            fontFamily = customFontFamily
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomCalendarView(onDateSelected: (LocalDate) -> Unit) {
    val selectedDate = remember { mutableStateOf(LocalDate.now()) }
    val day = selectedDate.value.dayOfMonth
    val month = selectedDate.value.monthValue
    val year = selectedDate.value.year

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(60.dp)
                    .width(70.dp)
                    .background(
                        color = Azul2,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$day",
                    style = TextStyle(
                        fontSize = 35.sp
                    ),
                    color = Color.White
                )
            }
            Text(
                text = ".",
                style = TextStyle(
                    fontSize = 50.sp
                ),
                color = Color.Black,
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )
            Box(
                modifier = Modifier
                    .height(60.dp)
                    .width(70.dp)
                    .background(
                        color = Azul2,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = getMonthAbbreviation(month),
                    style = TextStyle(
                        fontSize = 35.sp
                    ),
                    color = Color.White
                )
            }
            Text(
                text = ".",
                style = TextStyle(
                    fontSize = 50.sp
                ),
                color = Color.Black,
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )
            Box(
                modifier = Modifier
                    .height(60.dp)
                    .width(100.dp)
                    .background(
                        color = Azul2,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$year",
                    style = TextStyle(
                        fontSize = 35.sp
                    ),
                    color = Color.White
                )
            }
        }
        AndroidView(
            factory = { context ->
                CalendarView(context).apply {
                    setOnDateChangeListener { _, year, month, dayOfMonth ->
                        LocalDate.now().withMonth(month + 1).withYear(year).withDayOfMonth(dayOfMonth)
                            .also { selectedDate.value = it }
                        onDateSelected(selectedDate.value)
                    }
                }
            }
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