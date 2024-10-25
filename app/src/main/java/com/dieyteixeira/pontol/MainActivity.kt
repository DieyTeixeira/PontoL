package com.dieyteixeira.pontol

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.core.view.WindowInsetsControllerCompat
import com.dieyteixeira.pontol.ui.screen.HomeScreen
import com.dieyteixeira.pontol.ui.theme.PontoLTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = false
        windowInsetsController.isAppearanceLightNavigationBars = false

        setContent {

            val customFontFamily = remember {
                listOf(
                    FontFamily(Font(R.font.font_aller_lt)),
                    FontFamily(Font(R.font.font_aller_rg)),
                    FontFamily(Font(R.font.font_aller_bd))
                )
            }

            PontoLTheme {
                HomeScreen(
                    customFontFamily = customFontFamily
                )
            }
        }
    }
}