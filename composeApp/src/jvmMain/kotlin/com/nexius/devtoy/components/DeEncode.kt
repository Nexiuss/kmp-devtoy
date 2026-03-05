package com.nexius.devtoy.components

import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.runtime.*
import com.gabrieldrn.carbon.notification.NotificationStatus
import com.gabrieldrn.carbon.notification.ToastNotification
import com.gabrieldrn.carbon.textinput.TextArea
import java.net.URLDecoder
import java.net.URLEncoder
import java.util.*

@Composable
fun UrlDeEncode() {
    TextConvertComponent(
        forwardConvert = {
            URLEncoder.encode(it, "utf-8")
        },
        backwardConvert = {
            URLDecoder.decode(it, "utf-8")
        },
    )
}

@Composable
fun HtmlDeEncode() {
    TextConvertComponent(
        forwardConvert = {
            encodeHtml(it)
        },
        backwardConvert = {
            decodeHtml(it)
        },
        inputLabel = "编码",
        outputLabel = "解码"
    )
}


@Composable
fun Base64DeEncode() {
    TextConvertComponent(
        forwardConvert = {
            Base64.getEncoder().encodeToString(it.toByteArray())
        },
        backwardConvert = {
            String(Base64.getDecoder().decode(it))
        },
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
        QrCodeImage(url)
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