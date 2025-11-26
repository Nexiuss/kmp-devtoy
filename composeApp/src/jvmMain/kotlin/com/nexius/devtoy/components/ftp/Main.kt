package com.nexius.devtoy.components.ftp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.nexius.devtoy.components.ftp.ui.FtpScreen

fun main() = application {
    Window(title = "FTP 客户端工具", onCloseRequest = ::exitApplication) {
        FtpScreen()
    }
}