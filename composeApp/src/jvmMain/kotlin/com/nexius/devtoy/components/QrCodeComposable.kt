package com.nexius.devtoy.components

import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text

import com.nexius.devtoy.components.Icons.CheckCircle
import com.nexius.devtoy.components.Icons.Clear
import com.nexius.devtoy.components.Icons.ContentCopy
import com.nexius.devtoy.components.Icons.QrCodeScanner
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gabrieldrn.carbon.notification.NotificationStatus
import com.gabrieldrn.carbon.notification.ToastNotification
import com.gabrieldrn.carbon.textinput.TextArea
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.nexius.devtoy.theme.AppColors
import com.nexius.devtoy.utils.ImageUtils
import com.nexius.devtoy.utils.QrCodeUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.awt.image.BufferedImage
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun QrCodeImage(url: String) {
    var image by remember { mutableStateOf<ImageBitmap?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(url) {
        error = null
        image = null
        try {
            val bufferedImage = QrCodeUtils.generateQrCode(url)
            image = ImageUtils.bufferedImageToImageBitmap(bufferedImage)
        } catch (e: Exception) {
            error = e.message
        }
    }

    Box(modifier = Modifier.fillMaxSize().padding(32.dp)) {
        Column {
            var downloadStatus by remember { mutableStateOf("") }
            image?.let {
                ContextMenuArea(items = {
                    listOf(
                        ContextMenuItem("保存图片") {
                            coroutineScope.launch{
                                downloadStatus = downloadPng(url, 400)
                                delay(if (downloadStatus.contains("已保存到")) 5000 else 2000) // 成功时显示更长时间
                                downloadStatus = ""
                            }
                        }
                    )
                }) {
                    Image(bitmap = it, contentDescription = "QR Code")
                }

            }
            error?.let { Text("二维码生成失败：\n$error") }
            if(downloadStatus.isNotEmpty()){
                Row {
                    Icon(
                        imageVector = CheckCircle,
                        contentDescription = null,
                        tint = AppColors.Success,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(downloadStatus)
                }


            }
        }

    }
}

/**
 * 二维码编码解码工具组件
 */
@Composable
fun QrcodeToolComponent(
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("生成二维码", "解码二维码")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Tab选项卡
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column {
                // Tab标签
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    tabs.forEachIndexed { index, title ->
                        Surface(
                            onClick = { selectedTab = index },
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp),
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            androidx.compose.material3.Text(
                                text = title,
                                modifier = Modifier.padding(vertical = 12.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                    }
                }

                // Tab内容
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    when (selectedTab) {
                        0 -> QrcodeGenerateTab()
                        1 -> QrcodeDecodeTab()
                    }
                }
            }
        }

    }
}

@Composable
fun QrcodeGenerateTab() {
    //左边输入框
    var url by remember { mutableStateOf("http://www.baidu.com") }
    Column {
        TextArea(
            value = url,
            onValueChange = { url = it },
            label = "链接"
        )
        QrCodeImage(url)
    }

}

/**
 * 解码二维码Tab
 */
@Composable
private fun QrcodeDecodeTab() {
    val clipboardManager = LocalClipboardManager.current
    val coroutineScope = rememberCoroutineScope()

    var selectedFilePath by remember { mutableStateOf("") }
    var decodedContent by remember { mutableStateOf("") }
    var isDecoding by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // 文件选择区域
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                androidx.compose.material3.Text(
                    text = "选择二维码图片",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppColors.TextPrimary
                )

                // 使用统一的文件输入框
                FileInputField(
                    label = "二维码图片文件",
                    value = selectedFilePath,
                    onValueChange = { selectedFilePath = it },
                    placeholder = "选择二维码图片文件",
                    allowedExtensions = listOf("png", "jpg", "jpeg", "bmp", "gif")
                )

                // 解码按钮
                Button(
                    onClick = {
                        if (selectedFilePath.isNotBlank()) {
                            isDecoding = true
                            decodedContent = ""

                            coroutineScope.launch {
                                try {
                                    val result = decodeQrCodeFromPath(selectedFilePath)
                                    decodedContent = result ?: "无法识别二维码内容"
                                    delay(500) // 短暂延迟以显示加载状态
                                    isDecoding = false
                                } catch (e: Exception) {
                                    isDecoding = false
                                    decodedContent = "解码失败: ${e.message}"
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.Primary
                    ),
                    shape = RoundedCornerShape(8.dp),
                    enabled = selectedFilePath.isNotBlank() && !isDecoding
                ) {
                    if (isDecoding) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        androidx.compose.material3.Text(
                            text = "解码中...",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    } else {
                        Icon(
                            imageVector = QrCodeScanner,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        androidx.compose.material3.Text(
                            text = "解码二维码",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        // 解码结果显示区域
        if (decodedContent.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        androidx.compose.material3.Text(
                            text = "解码结果",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = AppColors.TextPrimary
                        )

                        IconButton(
                            onClick = {
                                clipboardManager.setText(AnnotatedString(decodedContent))
                            }
                        ) {
                            Icon(
                                imageVector = ContentCopy,
                                contentDescription = "复制",
                                tint = AppColors.Primary
                            )
                        }
                    }

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        color = if (decodedContent.contains("失败") || decodedContent.contains("无法识别") || decodedContent.contains("不存在"))
                            AppColors.Red50 else AppColors.Green50
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            androidx.compose.material3.Text(
                                text = "解码内容",
                                fontSize = 12.sp,
                                color = AppColors.TextSecondary
                            )
                            SelectionContainer {
                                androidx.compose.material3.Text(
                                    text = decodedContent,
                                    fontSize = 14.sp,
                                    color = AppColors.TextPrimary,
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    }

                    // 操作按钮
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                decodedContent = ""
                                selectedFilePath = ""
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = Clear,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            androidx.compose.material3.Text("清除", fontSize = 14.sp)
                        }

                        Button(
                            onClick = {
                                clipboardManager.setText(AnnotatedString(decodedContent))
                            },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AppColors.Primary
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = ContentCopy,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            androidx.compose.material3.Text("复制内容", fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}


suspend fun decodeQrCodeFromPath(filePath: String): String? {
    return withContext(Dispatchers.IO) {
        try {
            if (filePath.isBlank()) return@withContext null

            val file = java.io.File(filePath)
            if (file.exists()) {
                QrCodeUtils.decodeQrCodeFromFile(file)
            } else {
                "文件不存在或格式不支持"
            }
        } catch (e: Exception) {
            "解码失败: ${e.message}"
        }
    }
}


/**
 * 在指定目录创建带时间戳的文件
 * @param directory 目录路径
 * @param extension 文件扩展名
 * @return File 创建的文件对象
 */
fun createTimestampFile(directory: String, extension: String): File {
    val dir = File(directory)
    if (!dir.exists()) {
        dir.mkdirs()
    }

    val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
    val fileName = "${timestamp}_qrcode.$extension"
    return File(dir, fileName)
}


/**
 * 获取默认下载路径（JVM 实现）
 */
fun getDefaultDownloadPath(): String {
    return System.getProperty("user.home") + File.separator + "Downloads"
}


suspend fun downloadPng(
    content: String,
    size: Int,
): String {
    return withContext(Dispatchers.IO) {
        try {
            val bufferedImage = QrCodeUtils.generateQrCode(
                content = content,
                size = size,
            )
            // 使用设置中的默认下载路径
            val downloadPath = getDefaultDownloadPath()
            val file = createTimestampFile(downloadPath, "png")

            QrCodeUtils.saveQrCodeToFile(bufferedImage, file, "PNG")
            "PNG 已保存到: ${file.absolutePath}"
        } catch (e: Exception) {
            "下载失败: ${e.message}"
        }
    }
}