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

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListScreen(
    customFontFamily: List<FontFamily>? = null
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White.copy(alpha = 0.3f),
                shape = RoundedCornerShape(15.dp)
            )
            .padding(15.dp)
    ) {
        Text(
            text = "Lista de Registros",
            fontSize = 20.sp,
            fontFamily = customFontFamily?.get(2),
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Preview
//@Composable
//private fun Screen1Preview() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(AzulDegrade)
//    ) {
//        InsertScreen()
//    }
//}