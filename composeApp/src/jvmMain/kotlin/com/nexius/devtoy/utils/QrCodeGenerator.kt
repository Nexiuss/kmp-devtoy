package com.nexius.devtoy.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathBuilder
import androidx.compose.ui.unit.dp
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix

fun generateQrCode(
    url: String,
): ImageVector {
    val matrix = generateQrBitMatrix(url)
    return bitMatrixToImageVector(matrix)
}

fun generateQrBitMatrix(url: String, size: Int = 256): BitMatrix {
    return MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, size, size)
}

fun bitMatrixToImageVector(bitMatrix: BitMatrix, size: Float = 256f): ImageVector {
    val width = bitMatrix.width
    val height = bitMatrix.height
    val blockSize = size / width

    return ImageVector.Builder(
        defaultWidth = size.dp,
        defaultHeight = size.dp,
        viewportWidth = size,
        viewportHeight = size
    ).apply {
        for (y in 0 until height) {
            for (x in 0 until width) {
                if (bitMatrix[x, y]) {
                    addPath(
                        pathData = PathBuilder().apply {
                            moveTo(x * blockSize, y * blockSize)
                            lineTo((x + 1) * blockSize, y * blockSize)
                            lineTo((x + 1) * blockSize, (y + 1) * blockSize)
                            lineTo(x * blockSize, (y + 1) * blockSize)
                            close()
                        }.nodes,
                        fill = SolidColor(Color.Black),
                    )
                }
            }
        }
    }.build()
}