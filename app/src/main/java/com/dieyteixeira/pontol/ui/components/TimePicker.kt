package com.dieyteixeira.pontol.ui.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    timeState: TimePickerState,
    onDismissRequest: () -> Unit,
    onCancelClick: () -> Unit,
    onOKClick: () -> Unit
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
            ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = "Select time"
                )

                TimePicker(
                    state = timeState,
                    layoutType = TimePickerLayoutType.Vertical,
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    Button(
                        modifier = Modifier.padding(end = 8.dp),
                        onClick = onCancelClick
                    ) {
                        Text(
                            text = "Cancel"
                        )
                    }

                    Button(
                        modifier = Modifier.padding(start = 8.dp),
                        onClick = onOKClick
                    ) {
                        Text(text = "OK")
                    }
                }
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@RequiresApi(Build.VERSION_CODES.O)
fun formattedTime(hour: Int, minute: Int): String {
    return String.format("%02dh %02dmin", hour, minute)
}