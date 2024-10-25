package com.dieyteixeira.pontol.ui.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.dieyteixeira.pontol.R
import com.dieyteixeira.pontol.ui.theme.Azul1

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerCustom(
    customFontFamily: List<FontFamily>? = null,
    timeState: TimePickerState,
    onDismissRequest: () -> Unit,
    onCancelClick: () -> Unit,
    onOKClick: () -> Unit,
    onMiliClick: (Long) -> Unit = {}
) {

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
                    text = "Selecione o horário",
                    fontFamily = customFontFamily?.get(2)
                )

                TimePicker(
                    state = timeState,
                    layoutType = TimePickerLayoutType.Vertical,
                )

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
                            fontFamily = customFontFamily?.get(2)
                        )
                    }

                    Button(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .height(35.dp),
                        onClick = {
                            val hour = timeState.hour
                            val minute = timeState.minute
                            val milliseconds = convertToMilliseconds(hour, minute)
                            onMiliClick(milliseconds)
                            onOKClick()
                        }
                    ) {
                        Text(
                            text = "OK",
                            fontFamily = customFontFamily?.get(2)
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertToMilliseconds(hour: Int, minute: Int): Long {
    return (hour * 3600 + minute * 60) * 1000L
}

@SuppressLint("DefaultLocale")
@RequiresApi(Build.VERSION_CODES.O)
fun formattedTime(hour: Int, minute: Int): String {
    return String.format("%02dh %02dmin", hour, minute)
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun TimePickerDialogPreview() {
    TimePickerCustom(
        customFontFamily = null,
        timeState = rememberTimePickerState(
            initialHour = 5,
            initialMinute = 35
        ),
        onDismissRequest = {},
        onCancelClick = {},
        onOKClick = {}
    )
}