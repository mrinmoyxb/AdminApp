package com.alsalam.alsalamadminapp.Model

import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Random
import androidx.compose.runtime.*

fun randomStringGenerator(): String {
    val charPool: List<Char> = ('0'..'9') + ('a'..'z') + ('A'..'Z')
    val random = Random()
    val stringBuffer = StringBuffer(5)
    repeat(5) {
        val randomIndex = random.nextInt(charPool.size)
        stringBuffer.append(charPool[randomIndex])
    }
    return stringBuffer.toString()
}


@Composable
fun rememberImeState(): State<Boolean> {
    val imeState = remember {
        mutableStateOf(false)
    }

    val view = LocalView.current
    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val isKeyboardOpen = ViewCompat.getRootWindowInsets(view)
                ?.isVisible(WindowInsetsCompat.Type.ime()) ?: true
            imeState.value = isKeyboardOpen
        }

        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
    return imeState
}