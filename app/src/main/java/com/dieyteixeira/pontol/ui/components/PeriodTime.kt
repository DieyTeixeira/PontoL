package com.dieyteixeira.pontol.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dieyteixeira.pontol.R
import com.dieyteixeira.pontol.ui.theme.Amarelo1
import com.dieyteixeira.pontol.ui.theme.Azul1
import com.dieyteixeira.pontol.ui.theme.AzulDegrade

@Composable
fun PeriodTime(
    icon: Painter,
    colorIcon: Color,
    colorText: Color,
    initialTime: String,
    finalTime: String,
    onInitialTimeClick: () -> Unit,
    onFinalTimeClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(
                color = Color.White.copy(alpha = 0.8f),
                shape = RoundedCornerShape(15.dp)
            )
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .background(
                            color = colorText,
                            shape = RoundedCornerShape(15.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = icon,
                        contentDescription = "Logo",
                        colorFilter = tint(colorIcon),
                        modifier = Modifier
                            .size(100.dp)
                            .rotate(-90f)
                    )
                }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hora Inicial",
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(40.dp)
                        .padding(top = 5.dp)
                        .background(Color.White, RoundedCornerShape(100))
                        .clickable(onClick = onInitialTimeClick),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (initialTime.isEmpty()) "00h 00min" else initialTime,
                        color = Color.Gray,
                        style = TextStyle(fontSize = 18.sp),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hora Final",
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(40.dp)
                        .padding(top = 5.dp)
                        .background(Color.White, RoundedCornerShape(100))
                        .clickable(onClick = onFinalTimeClick),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (finalTime.isEmpty()) "00h 00min" else finalTime,
                        color = Color.Gray,
                        style = TextStyle(fontSize = 18.sp),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PeriodTimePreview() {
    Column(
        modifier = Modifier
            .height(120.dp)
            .background(AzulDegrade),
        verticalArrangement = Arrangement.Center
    ) {
        PeriodTime(
            icon = painterResource(id = R.drawable.txt_manha),
            colorIcon = Color.White,
            colorText = Azul1,
            initialTime = "08h 00min",
            finalTime = "12h 00min",
            onInitialTimeClick = {},
            onFinalTimeClick = {}
        )
    }
}