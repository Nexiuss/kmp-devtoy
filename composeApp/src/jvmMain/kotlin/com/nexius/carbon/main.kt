package com.nexius.carbon

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.nexius.carbon.components.App

fun main() = application {
    /*Window(
        onCloseRequest = ::exitApplication,
        title = "kmp-carbon",
    ) {
        CarbonApp()
    }*/

    Window(onCloseRequest = ::exitApplication, title = "SQL Editor") { App() }

}