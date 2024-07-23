package com.alsalam.alsalamadminapp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alsalam.alsalamadminapp.Navigation.MainScreen
import com.alsalam.alsalamadminapp.ui.theme.AlSalamAdminAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        enableEdgeToEdge()
        setContent {
            AlSalamAdminAppTheme {
                MainScreen()
            }
        }
    }
}

