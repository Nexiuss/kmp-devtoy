package com.nexius.devtoy.components

import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.appstractive.jwt.JWT
import com.appstractive.jwt.from
import com.gabrieldrn.carbon.notification.NotificationStatus
import com.gabrieldrn.carbon.notification.ToastNotification
import com.gabrieldrn.carbon.textinput.TextArea
import kotlinx.serialization.json.Json
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

@Composable
fun JwtDecode() {
    // JWT解码状态管理
    var jwtToken by remember { mutableStateOf("") }
    var jwtHeader by remember { mutableStateOf("") }
    var jwtClaims by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 输入区域
        OutlinedTextField(
            value = jwtToken,
            onValueChange = { jwtToken = it },
            label = { Text("JWT Token") },
            modifier = Modifier.fillMaxWidth().height(100.dp),
            singleLine = false,
            maxLines = Int.MAX_VALUE
        )

        // 解码按钮
        Button(
            onClick = {
                try {
                    val jwt = JWT.from(jwtToken)
                    
                    // 格式化JSON
                    val json = Json { prettyPrint = true; ignoreUnknownKeys = true }
                    
                    // 解析并格式化头部
                    jwtHeader = jwt.header.toString()
                    
                    // 解析并格式化载荷
                    val claimsJson = Json.parseToJsonElement(jwt.claims.toString())
                    jwtClaims = json.encodeToString(claimsJson)
                    
                    errorMessage = ""
                } catch (e: Exception) {
                    jwtHeader = ""
                    jwtClaims = ""
                    errorMessage = "解码失败：${e.message ?: "未知错误"}"
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("解码 JWT")
        }

        // 错误信息显示
        if (errorMessage.isNotBlank()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // 结果展示：使用Row布局并排显示头部和载荷
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // JWT头部显示
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = jwtHeader,
                    onValueChange = {},
                    label = { Text("头部信息") },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth().height(150.dp),
                    singleLine = false,
                    maxLines = Int.MAX_VALUE
                )
            }

            // JWT载荷显示
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = jwtClaims,
                    onValueChange = {},
                    label = { Text("载荷信息") },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth().height(150.dp),
                    singleLine = false,
                    maxLines = Int.MAX_VALUE
                )
            }
        }
    }
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