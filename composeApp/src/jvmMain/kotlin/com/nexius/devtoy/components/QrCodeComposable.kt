package com.nexius.devtoy.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.nexius.devtoy.utils.ImageUtils
import com.nexius.devtoy.utils.QrCodeUtils

@Composable
fun QrCodeImage(url: String) {
    var image by remember { mutableStateOf<ImageBitmap?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(url) {
        error = null
        image = null
        try {
            image = ImageUtils.bufferedImageToImageBitmap(QrCodeUtils.generateQrCode(url))
        } catch (e: Exception) {
            error = e.message
        }
    }

    Box(modifier = Modifier.fillMaxSize().padding(32.dp)) {
        image?.let { Image(bitmap = it, contentDescription = "QR Code") }
        error?.let { Text("二维码生成失败：\n$error") }
    }
}