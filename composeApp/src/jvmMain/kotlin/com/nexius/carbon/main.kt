package com.nexius.carbon

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.nexius.carbon.components.FontAwesomeMenuTree

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "kmp-carbon",
    ) {
        App()
    }
}