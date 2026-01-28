package com.nexius.devtoy.components

import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import com.gabrieldrn.carbon.textinput.TextArea
import compose.icons.FeatherIcons
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import com.gabrieldrn.carbon.notification.NotificationStatus
import com.gabrieldrn.carbon.notification.ToastNotification
import com.nexius.devtoy.utils.generateQrCode
import compose.icons.feathericons.ChevronsDown
import compose.icons.feathericons.ChevronsUp
import org.jetbrains.skia.Bitmap
import java.awt.image.BufferedImage
import java.io.File
import java.net.URLDecoder
import java.net.URLEncoder
import java.util.Base64
import javax.imageio.ImageIO

@Composable
fun UrlDeEncode() {
    //左边输入框
    var leftTextFieldValue by remember { mutableStateOf("") }
    //右边输入框
    var rightTextFieldValue by remember { mutableStateOf("") }


    TextArea(
        value = leftTextFieldValue,
        onValueChange = { leftTextFieldValue = it },
        label = "明文"
    )
    Row {
        IconButton(onClick = {
            rightTextFieldValue = URLEncoder.encode(leftTextFieldValue, "utf-8")
        }){
            Icon(FeatherIcons.ChevronsDown, contentDescription = "编码")
        }
        IconButton(onClick = {
            leftTextFieldValue = URLDecoder.decode(rightTextFieldValue, "utf-8")
        }){
            Icon(FeatherIcons.ChevronsUp, contentDescription = "编码")
        }
    }
    TextArea(
        value = rightTextFieldValue,
        onValueChange = { rightTextFieldValue = it },
        label = "密文"
    )
}

@Composable
fun HtmlDeEncode() {
    //左边输入框
    var leftTextFieldValue by remember { mutableStateOf("") }
    //右边输入框
    var rightTextFieldValue by remember { mutableStateOf("") }


    TextArea(
        value = leftTextFieldValue,
        onValueChange = { leftTextFieldValue = it },
        label = "明文"
    )
    Row {
        IconButton(onClick = {
            rightTextFieldValue = encodeHtml(leftTextFieldValue)
        }){
            Icon(FeatherIcons.ChevronsDown, contentDescription = "编码")
        }
        IconButton(onClick = {
            leftTextFieldValue =decodeHtml(rightTextFieldValue)
        }){
            Icon(FeatherIcons.ChevronsUp, contentDescription = "编码")
        }
    }
    TextArea(
        value = rightTextFieldValue,
        onValueChange = { rightTextFieldValue = it },
        label = "密文"
    )
}


@Composable
fun Base64DeEncode() {
    //左边输入框
    var leftTextFieldValue by remember { mutableStateOf("") }
    //右边输入框
    var rightTextFieldValue by remember { mutableStateOf("") }


    TextArea(
        value = leftTextFieldValue,
        onValueChange = { leftTextFieldValue = it },
        label = "明文"
    )
    Row {
        IconButton(onClick = {
            rightTextFieldValue = Base64.getEncoder().encodeToString(leftTextFieldValue.toByteArray())
        }){
            Icon(FeatherIcons.ChevronsDown, contentDescription = "编码")
        }
        IconButton(onClick = {
            leftTextFieldValue =String(Base64.getDecoder().decode(rightTextFieldValue))
        }){
            Icon(FeatherIcons.ChevronsUp, contentDescription = "编码")
        }
    }
    TextArea(
        value = rightTextFieldValue,
        onValueChange = { rightTextFieldValue = it },
        label = "密文"
    )
}

fun decodeHtml(input: String): String {
    val entities = mapOf(
        "&amp;" to '&',
        "&lt;" to '<',
        "&gt;" to '>',
        "&quot;" to '"',
        "&#39;" to '\''
    )
    var output = input
    for ((key, value) in entities) {
        output = output.replace(key, value.toString())
    }
    return output
}

fun encodeHtml(input: String): String {
    val entities = mapOf(
        '&' to "&amp;",
        '<' to "&lt;",
        '>' to "&gt;",
        '"' to "&quot;",
        '\'' to "&#39;"
    )
    val sb = StringBuilder()
    for (char in input) {
        sb.append(entities[char] ?: char)
    }
    return sb.toString()
}


@Composable
fun QrCode() {
    //左边输入框
    var url by remember { mutableStateOf("http://www.baidu.com") }

    var toastBody by remember { mutableStateOf("暂时不支持保存二维码") }
    var toastVisibility by remember { mutableStateOf(false) }

    TextArea(
        value = url,
        onValueChange = { url = it },
        label = "链接"
    )
    ContextMenuArea(items = {
        listOf(
            ContextMenuItem("保存"){
                toastVisibility = true
            }
        )
    }) {
        Image(imageVector = generateQrCode( url), contentDescription = "二维码")
    }
    if(toastVisibility){
        ToastNotification(
            title = "提示",
            body = toastBody,
            status = NotificationStatus.Informational,
            onClose = {
                toastVisibility = false
            }
        )
    }
}