package com.nexius.devtoy.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.unit.dp
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import java.awt.image.BufferedImage
import java.io.PrintWriter
import java.io.StringWriter

// 返回二维码和异常信息
fun generateQrCodeBufferedImage(content: String, size: Int = 400): Pair<BufferedImage?, String?> {
    return try {
        require(!content.isEmpty()){"不能为空"}
        val hints = mapOf(EncodeHintType.CHARACTER_SET to "UTF-8")
        val bitMatrix = QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints)
        val image = BufferedImage(size, size, BufferedImage.TYPE_INT_RGB)
        for (x in 0 until size) {
            for (y in 0 until size) {
                image.setRGB(x, y, if (bitMatrix.get(x, y)) 0xFF000000.toInt() else 0xFFFFFFFF.toInt())
            }
        }
        Pair(image, null)
    } catch (e: Throwable) {
        val sw = StringWriter()
        e.printStackTrace(PrintWriter(sw))
        Pair(null, sw.toString())
    }
}

fun bufferedImageToImageBitmap(bufferedImage: BufferedImage?): Pair<ImageBitmap?, String?> {
    return try {
        Pair(bufferedImage?.toComposeImageBitmap(), null)
    } catch (e: Throwable) {
        val sw = StringWriter()
        e.printStackTrace(PrintWriter(sw))
        Pair(null, sw.toString())
    }
}

@Composable
fun QrCodeImage(url: String) {
    var stacktrace by remember { mutableStateOf<String?>(null) }
    val imageBitmap = remember(url) {
        val (bufferedImage, error1) = generateQrCodeBufferedImage(url)
        if (error1 != null) {
            stacktrace = error1
            null
        } else {
            val (imgBitmap, error2) = bufferedImageToImageBitmap(bufferedImage)
            if (error2 != null) {
                stacktrace = error2
                null
            } else {
                imgBitmap
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize().padding(32.dp)) {
        when {
            imageBitmap != null -> Image(bitmap = imageBitmap, contentDescription = "QR Code")
            stacktrace != null   -> Text("二维码生成失败：\n$stacktrace")
            else                 -> Text("未知错误")
        }
    }
}