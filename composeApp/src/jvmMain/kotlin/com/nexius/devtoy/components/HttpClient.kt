package com.nexius.devtoy.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HttpClientGui() {
    // 状态管理
    var url by remember { mutableStateOf("https://jsonplaceholder.typicode.com/posts/1") }
    var method by remember { mutableStateOf("GET") }
    var requestHeaders by remember { mutableStateOf("") }
    var requestBody by remember { mutableStateOf("") }
    var response by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var requestLogs by remember { mutableStateOf("") }

    // 请求方法列表
    val methods = listOf("GET", "POST", "PUT", "DELETE")
    // 下拉框展开状态
    var expanded by remember { mutableStateOf(false) }

    // Ktor 客户端
    val client = remember { HttpClient(CIO) }

    // 解析请求头字符串为 Map
    fun parseHeaders(headersString: String): Map<String, String> {
        return headersString
            .lines() // 按行分割
            .map { it.trim() } // 去除每行首尾空格
            .filter { it.isNotEmpty() && it.contains(":") } // 过滤空行和无效行
            .associate { line ->
                val parts = line.split(":", limit = 2) // 按第一个冒号分割
                val key = parts[0].trim()
                val value = parts[1].trim()
                key to value
            }
    }

    // 获取当前时间字符串
    fun getCurrentTime(): String {
        val now = LocalDateTime.now()
        return "${now.hour.toString().padStart(2, '0')}:${now.minute.toString().padStart(2, '0')}:${now.second.toString().padStart(2, '0')}.${now.nano.toString().padStart(9, '0').substring(0, 3)}"
    }

    // 发送请求的函数
    fun sendRequest() {
        coroutineScope.launch {
            isLoading = true
            try {
                // 解析请求头
                val headers = parseHeaders(requestHeaders)

                // 记录请求开始日志
                val startTime = getCurrentTime()
                val requestLog = buildString {
                    appendLine("[$startTime] Request Start")
                    appendLine("  Method: $method")
                    appendLine("  URL: $url")
                    if (headers.isNotEmpty()) {
                        appendLine("  Headers:")
                        headers.forEach { (key, value) ->
                            appendLine("    $key: $value")
                        }
                    }
                    if (requestBody.isNotEmpty() && method != "GET" && method != "DELETE") {
                        appendLine("  Body:")
                        appendLine("    $requestBody")
                    }
                    appendLine()
                }

                // 发送请求并获取完整响应
                val httpResponse = when (method) {
                    "GET" -> client.get(url) {
                        headers.forEach { (key, value) -> header(key, value) }
                    }
                    "POST" -> client.post(url) {
                        headers.forEach { (key, value) -> header(key, value) }
                        setBody(requestBody)
                    }
                    "PUT" -> client.put(url) {
                        headers.forEach { (key, value) -> header(key, value) }
                        setBody(requestBody)
                    }
                    "DELETE" -> client.delete(url) {
                        headers.forEach { (key, value) -> header(key, value) }
                    }
                    else -> throw IllegalArgumentException("Unsupported method")
                }

                // 获取响应内容
                val responseBody = httpResponse.bodyAsText()
                response = responseBody

                // 记录响应日志
                val endTime = getCurrentTime()
                val responseLog = buildString {
                    appendLine("[$endTime] Response Received")
                    appendLine("  Status: ${httpResponse.status.value} ${httpResponse.status.description}")
                    appendLine("  Response Headers:")
                    httpResponse.headers.forEach { key, values ->
                        values.forEach { value ->
                            appendLine("    $key: $value")
                        }
                    }
                    appendLine("  Response Body:")
                    appendLine("    $responseBody")
                    appendLine("-")
                    appendLine()
                }

                // 更新日志
                requestLogs = requestLog + responseLog + requestLogs

            } catch (e: Exception) {
                val errorTime = getCurrentTime()
                val errorLog = buildString {
                    appendLine("[$errorTime] Request Failed")
                    appendLine("  Error: ${e.message}")
                    appendLine("  Stacktrace: ${e.stackTraceToString().take(500)}...")
                    appendLine("-")
                    appendLine()
                }
                response = "Error: ${e.message}"
                requestLogs = errorLog + requestLogs
            } finally {
                isLoading = false
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("KMP HTTP Client") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // URL、请求方法和发送按钮同一行布局
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 请求方法下拉框
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier = Modifier.width(120.dp)
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = method,
                        onValueChange = {},
                        label = { Text("Method") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        methods.forEach { selectedMethod ->
                            DropdownMenuItem(
                                text = { Text(selectedMethod) },
                                onClick = {
                                    method = selectedMethod
                                    expanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                            )
                        }
                    }
                }

                // URL 输入框
                OutlinedTextField(
                    value = url,
                    onValueChange = { url = it },
                    label = { Text("URL") },
                    modifier = Modifier.weight(1f)
                )

                // 发送按钮移到这里
                Button(
                    onClick = ::sendRequest,
                    enabled = !isLoading,
                    modifier = Modifier.height(56.dp) // 匹配输入框高度
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("Send Request")
                    }
                }
            }

            // 请求头输入
            OutlinedTextField(
                value = requestHeaders,
                onValueChange = { requestHeaders = it },
                label = { Text("请求头(key: value)") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2,
                maxLines = 4,
                placeholder = { Text("例如：\nContent-Type: application/json\nAuthorization: Bearer token123") }
            )

            // 请求体输入
            OutlinedTextField(
                value = requestBody,
                onValueChange = { requestBody = it },
                label = { Text("Request Body") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 4,
                maxLines = 8
            )

            // 移除原来的按钮行

            // 响应和日志区域 - 使用 TabRow 切换
            var selectedTabIndex by remember { mutableStateOf(0) }
            val tabs = listOf("Response", "Request Logs")

            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index }
                    )
                }
            }

            Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
                when (selectedTabIndex) {
                    0 -> {
                        // 响应显示
                        Card(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text("Response:", style = MaterialTheme.typography.titleMedium)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = response,
                                    modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                    1 -> {
                        // 请求日志显示
                        Card(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                // Request Logs 标题和 Clear Logs 按钮同一行
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("Request Logs:", style = MaterialTheme.typography.titleMedium)
                                    Button(
                                        onClick = { requestLogs = "" },
                                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                                        modifier = Modifier.height(36.dp) // 小一点的按钮
                                    ) {
                                        Text("Clear Logs")
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = requestLogs.ifEmpty { "No logs yet. Send a request to see logs." },
                                    modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                                    style = MaterialTheme.typography.bodySmall,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}