package com.nexius.devtoy.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.calf.io.KmpFile
import com.mohamedrejeb.calf.picker.FilePickerFileType
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.HttpHeaders.ContentEncoding
import io.ktor.http.content.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.skia.skottie.LogLevel
import java.io.ByteArrayOutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// 优化：提取常量，增强可维护性
private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
private const val MAX_STACKTRACE_LENGTH = 500
private const val DEFAULT_UPLOAD_FIELD_NAME = "file"
private const val DEFAULT_BASE_URL = "https://jsonplaceholder.typicode.com/posts/1"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HttpClientGui() {
    // 状态管理 - 优化：使用 TextFieldValue 提升输入体验
    var url by remember { mutableStateOf(TextFieldValue(DEFAULT_BASE_URL)) }
    var method by remember { mutableStateOf("GET") }
    var requestHeaders by remember { mutableStateOf(TextFieldValue("")) }
    var requestBody by remember { mutableStateOf(TextFieldValue("")) }
    var response by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var requestLogs by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }

    // 文件上传相关状态
    var selectedFiles by remember { mutableStateOf<List<KmpFile>>(emptyList()) }
    var showFileChooser by remember { mutableStateOf(false) }
    var uploadFieldName by remember { mutableStateOf(TextFieldValue(DEFAULT_UPLOAD_FIELD_NAME)) }

    // 新增：响应格式化状态
    var formatResponse by remember { mutableStateOf(true) }

    // 请求方法列表
    val methods = listOf("GET", "POST", "PUT", "DELETE", "PATCH", "HEAD", "OPTIONS")
    var expanded by remember { mutableStateOf(false) }

    // Ktor 客户端 - 优化：增强配置，添加日志和编码支持
    val client = remember {
        HttpClient(CIO) {
            engine {
                maxConnectionsCount = 100
                endpoint {
                    maxConnectionsPerRoute = 10
                    pipelineMaxSize = 20
                    keepAliveTime = 5000
                    connectTimeout = 15000 // 优化：延长超时时间
                    socketTimeout = 15000   // 新增：添加套接字超时
                    connectAttempts = 3
                }
            }

            // 新增：默认请求配置
            defaultRequest {
                header(HttpHeaders.UserAgent, "KMP-HTTP-Client/1.0")
            }
        }
    }

    // 解析请求头字符串为 Map - 优化：增强容错性
    fun parseHeaders(headersString: TextFieldValue): Map<String, String> {
        return headersString.text
            .lines()
            .map { it.trim() }
            .filter { it.isNotEmpty() && it.contains(":") }
            .associate { line ->
                val parts = line.split(":", limit = 2)
                val key = parts[0].trim()
                val value = parts.getOrElse(1) { "" }.trim()
                key to value
            }
    }

    // 获取当前时间字符串 - 优化：使用格式化器，更简洁
    fun getCurrentTime(): String {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER)
    }

    // 新增：格式化JSON响应
    fun formatJson(json: String): String {
        return try {
            // 简单的JSON格式化实现（如需更完善可引入JSON库）
            val outputStream = ByteArrayOutputStream()
            var indentLevel = 0
            var inString = false
            var inEscape = false

            for (c in json) {
                when {
                    inEscape -> {
                        outputStream.write(c.code)
                        inEscape = false
                    }
                    c == '\\' -> {
                        outputStream.write(c.code)
                        inEscape = true
                    }
                    c == '"' -> {
                        outputStream.write(c.code)
                        inString = !inString
                    }
                    inString -> {
                        outputStream.write(c.code)
                    }
                    c == '{' || c == '[' -> {
                        outputStream.write(c.code)
                        indentLevel++
                        outputStream.write('\n'.code)
                        outputStream.write(("  ".repeat(indentLevel)).toByteArray())
                    }
                    c == '}' || c == ']' -> {
                        indentLevel--
                        outputStream.write('\n'.code)
                        outputStream.write(("  ".repeat(indentLevel)).toByteArray())
                        outputStream.write(c.code)
                    }
                    c == ',' -> {
                        outputStream.write(c.code)
                        outputStream.write('\n'.code)
                        outputStream.write(("  ".repeat(indentLevel)).toByteArray())
                    }
                    c == ':' -> {
                        outputStream.write(c.code)
                        outputStream.write(' '.code)
                    }
                    c.isWhitespace() -> {} // 忽略原有的空白字符
                    else -> {
                        outputStream.write(c.code)
                    }
                }
            }
            outputStream.toString()
        } catch (e: Exception) {
            json // 格式化失败返回原字符串
        }
    }

    // 发送请求的函数 - 优化：重构逻辑，增强错误处理
    fun sendRequest() {
        // 输入验证
        if (url.text.isBlank()) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar("URL不能为空！")
            }
            return
        }

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
                    appendLine("  URL: ${url.text}")
                    if (headers.isNotEmpty()) {
                        appendLine("  Headers:")
                        headers.forEach { (key, value) ->
                            appendLine("    $key: $value")
                        }
                    }
                    // 记录文件上传信息
                    if (selectedFiles.isNotEmpty()) {
                        appendLine("  Upload Files:")
                        selectedFiles.forEach { file ->
                            appendLine("    Field: ${uploadFieldName.text}, File: ${file.file.name} (${file.file.length()} bytes)")
                        }
                        appendLine("  Upload Field Name: ${uploadFieldName.text}")
                    }
                    // 记录请求体（非文件上传时）
                    if (requestBody.text.isNotEmpty() && method !in listOf("GET", "DELETE", "HEAD") && selectedFiles.isEmpty()) {
                        appendLine("  Body:")
                        appendLine("    ${requestBody.text}")
                    }
                    appendLine()
                }

                // 发送请求并获取完整响应 - 优化：使用Dispatchers.IO
                val httpResponse = withContext(Dispatchers.IO) {
                    when (method) {
                        "GET" -> client.get(url.text) {
                            headers.forEach { (key, value) -> header(key, value) }
                        }
                        "POST" -> createPostRequest(url.text, headers, selectedFiles, uploadFieldName.text, requestBody.text)
                        "PUT" -> createPutRequest(url.text, headers, selectedFiles, uploadFieldName.text, requestBody.text)
                        "DELETE" -> client.delete(url.text) {
                            headers.forEach { (key, value) -> header(key, value) }
                        }
                        "PATCH" -> client.patch(url.text) {
                            headers.forEach { (key, value) -> header(key, value) }
                            if (selectedFiles.isEmpty()) {
                                setBody(requestBody.text)
                            } else {
                                // PATCH方法文件上传
                                contentType(ContentType.MultiPart.FormData)
                                setBody(createMultiPartFormData(selectedFiles, uploadFieldName.text, requestBody.text))
                            }
                        }
                        "HEAD" -> client.head(url.text) {
                            headers.forEach { (key, value) -> header(key, value) }
                        }
                        "OPTIONS" -> client.options(url.text) {
                            headers.forEach { (key, value) -> header(key, value) }
                        }
                        else -> throw IllegalArgumentException("Unsupported HTTP method: $method")
                    }
                }

                // 获取响应内容
                val responseBody = httpResponse.bodyAsText()
                // 格式化响应（如果启用）
                response = if (formatResponse && responseBody.startsWith("{") || responseBody.startsWith("[")) {
                    formatJson(responseBody)
                } else {
                    responseBody
                }

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
                    appendLine("    $response")
                    appendLine("-".repeat(80))
                    appendLine()
                }

                // 更新日志
                requestLogs = requestLog + responseLog + requestLogs

                // 显示成功提示
                snackbarHostState.showSnackbar("请求成功！状态码: ${httpResponse.status.value}")

            } catch (e: Exception) {
                val errorTime = getCurrentTime()
                val errorMessage = e.message ?: "未知错误"
                val errorLog = buildString {
                    appendLine("[$errorTime] Request Failed")
                    appendLine("  Error: $errorMessage")
                    appendLine("  Stacktrace: ${e.stackTraceToString().take(MAX_STACKTRACE_LENGTH)}...")
                    appendLine("-".repeat(80))
                    appendLine()
                }
                response = "Error: $errorMessage\n\n${e.stackTraceToString().take(MAX_STACKTRACE_LENGTH)}..."
                requestLogs = errorLog + requestLogs

                // 显示错误提示
                snackbarHostState.showSnackbar("请求失败: $errorMessage", duration = SnackbarDuration.Long)
            } finally {
                isLoading = false
            }
        }
    }

    // 布局部分 - 优化：添加SnackbarHost，提升用户反馈
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("KMP HTTP Client") },
                actions = {
                    // 新增：清空所有输入按钮
                    IconButton(onClick = {
                        url = TextFieldValue(DEFAULT_BASE_URL)
                        method = "GET"
                        requestHeaders = TextFieldValue("")
                        requestBody = TextFieldValue("")
                        response = ""
                        selectedFiles = emptyList()
                        uploadFieldName = TextFieldValue(DEFAULT_UPLOAD_FIELD_NAME)
                    }) {
                        Icon(Icons.Default.ClearAll, contentDescription = "清空所有")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
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
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    trailingIcon = {
                        if (url.text.isNotEmpty()) {
                            IconButton(onClick = { url = TextFieldValue("") }) {
                                Icon(Icons.Default.Clear, contentDescription = "清空URL")
                            }
                        }
                    }
                )

                // 发送按钮
                Button(
                    onClick = ::sendRequest,
                    enabled = !isLoading,
                    modifier = Modifier.height(56.dp)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Icon(Icons.Default.Send, contentDescription = "发送请求", modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("发送")
                    }
                }
            }

            // 请求头输入 - 优化：支持文本选择
            SelectionContainer {
                OutlinedTextField(
                    value = requestHeaders,
                    onValueChange = { requestHeaders = it },
                    label = { Text("请求头(key: value)") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2,
                    maxLines = 4,
                    placeholder = {
                        Text("例如：\nContent-Type: application/json\nAuthorization: Bearer token123")
                    },
                    trailingIcon = {
                        if (requestHeaders.text.isNotEmpty()) {
                            IconButton(onClick = { requestHeaders = TextFieldValue("") }) {
                                Icon(Icons.Default.Clear, contentDescription = "清空请求头")
                            }
                        }
                    }
                )
            }

            // 文件上传区域
            if (method in listOf("POST", "PUT", "PATCH")) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("文件上传", style = MaterialTheme.typography.titleMedium)

                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                // 文件选择按钮
                                OutlinedButton(
                                    onClick = { showFileChooser = true },
                                    modifier = Modifier.height(40.dp),
                                    enabled = !isLoading
                                ) {
                                    Icon(Icons.Default.AttachFile, contentDescription = "选择文件", modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("选择文件")
                                }

                                // 清除文件按钮
                                if (selectedFiles.isNotEmpty()) {
                                    IconButton(
                                        onClick = { selectedFiles = emptyList() },
                                        modifier = Modifier.size(40.dp),
                                        enabled = !isLoading
                                    ) {
                                        Icon(Icons.Default.Clear, contentDescription = "清除文件")
                                    }
                                }
                            }
                        }

                        // 上传字段名输入
                        OutlinedTextField(
                            value = uploadFieldName,
                            onValueChange = { uploadFieldName = it },
                            label = { Text("上传字段名") },
                            placeholder = { Text("默认: file") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            enabled = !isLoading,
                            trailingIcon = {
                                if (uploadFieldName.text != DEFAULT_UPLOAD_FIELD_NAME) {
                                    IconButton(onClick = { uploadFieldName = TextFieldValue(DEFAULT_UPLOAD_FIELD_NAME) }) {
                                        Icon(Icons.Default.Restore, contentDescription = "恢复默认")
                                    }
                                }
                            }
                        )

                        // 选中文件列表
                        if (selectedFiles.isNotEmpty()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .verticalScroll(rememberScrollState())
                                    .heightIn(max = 120.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text("已选文件 (${selectedFiles.size}):", style = MaterialTheme.typography.bodySmall)
                                selectedFiles.forEach { file ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = file.file.name,
                                            style = MaterialTheme.typography.bodySmall,
                                            modifier = Modifier.weight(1f)
                                        )
                                        Text(
                                            text = "${file.file.length() / 1024} KB",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        IconButton(
                                            onClick = { selectedFiles = selectedFiles - file },
                                            modifier = Modifier.size(24.dp),
                                            enabled = !isLoading
                                        ) {
                                            Icon(Icons.Default.Close, contentDescription = "移除文件", modifier = Modifier.size(16.dp))
                                        }
                                    }
                                }
                            }
                        } else {
                            Text(
                                "未选择文件",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // 请求体输入 - 优化：支持文本选择，禁用状态提示
            SelectionContainer {
                OutlinedTextField(
                    value = requestBody,
                    onValueChange = { requestBody = it },
                    label = {
                        if (selectedFiles.isNotEmpty()) {
                            Text("请求体 (文件上传时将作为表单字段 'body' 发送)")
                        } else {
                            Text("请求体 (GET/DELETE 方法将被忽略)")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 4,
                    maxLines = 8,
                    enabled = !isLoading,
                    trailingIcon = {
                        if (requestBody.text.isNotEmpty()) {
                            IconButton(onClick = { requestBody = TextFieldValue("") }, enabled = !isLoading) {
                                Icon(Icons.Default.Clear, contentDescription = "清空请求体")
                            }
                        }
                    },
                    placeholder = {
                        Text("{\n  \"title\": \"foo\",\n  \"body\": \"bar\",\n  \"userId\": 1\n}")
                    }
                )
            }

            // 响应和日志区域 - 使用 TabRow 切换
            var selectedTabIndex by remember { mutableStateOf(0) }
            val tabs = listOf("响应", "请求日志")

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
                        // 响应显示 - 优化：添加格式化开关
                        Card(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("响应内容:", style = MaterialTheme.typography.titleMedium)
                                    // 响应格式化开关
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text("格式化JSON:", style = MaterialTheme.typography.bodySmall)
                                        Switch(
                                            checked = formatResponse,
                                            onCheckedChange = {
                                                formatResponse = it
                                                // 重新格式化当前响应
                                                if (response.isNotEmpty() && (response.startsWith("{") || response.startsWith("["))) {
                                                    response = if (it) formatJson(response) else response.replace(Regex("\\s+"), "")
                                                }
                                            },
                                            modifier = Modifier.padding(start = 8.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                SelectionContainer {
                                    Text(
                                        text = response.ifEmpty { "等待请求响应..." },
                                        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                                        style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace)
                                    )
                                }
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
                                    Text("请求日志:", style = MaterialTheme.typography.titleMedium)
                                    Button(
                                        onClick = { requestLogs = "" },
                                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                                        modifier = Modifier.height(36.dp),
                                        enabled = !isLoading
                                    ) {
                                        Icon(Icons.Default.Delete, contentDescription = "清空日志", modifier = Modifier.size(16.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("清空日志")
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                SelectionContainer {
                                    Text(
                                        text = requestLogs.ifEmpty { "暂无日志。发送请求查看详细日志。" },
                                        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                                        style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // 文件选择器 - 优化：正确的位置和多文件选择
    if (showFileChooser) {
        FilePicker(
            fileType = FilePickerFileType.All,
            onResult = { files,context ->
                selectedFiles = files
            }
        ).launch()
    }
}

// 新增：创建POST请求的辅助函数 - 优化：代码复用
private suspend fun createPostRequest(
    url: String,
    headers: Map<String, String>,
    files: List<KmpFile>,
    fieldName: String,
    body: String
): HttpResponse {
    val client = HttpClient(CIO)
    return if (files.isNotEmpty()) {
        client.post(url) {
            headers.forEach { (key, value) -> header(key, value) }
            contentType(ContentType.MultiPart.FormData)
            setBody(createMultiPartFormData(files, fieldName, body))
        }
    } else {
        client.post(url) {
            headers.forEach { (key, value) -> header(key, value) }
            setBody(body)
        }
    }
}

// 新增：创建PUT请求的辅助函数 - 优化：代码复用
private suspend fun createPutRequest(
    url: String,
    headers: Map<String, String>,
    files: List<KmpFile>,
    fieldName: String,
    body: String
): HttpResponse {
    val client = HttpClient(CIO)
    return if (files.isNotEmpty()) {
        client.put(url) {
            headers.forEach { (key, value) -> header(key, value) }
            contentType(ContentType.MultiPart.FormData)
            setBody(createMultiPartFormData(files, fieldName, body))
        }
    } else {
        client.put(url) {
            headers.forEach { (key, value) -> header(key, value) }
            setBody(body)
        }
    }
}

// 新增：创建多部分表单数据的辅助函数 - 优化：代码复用
private fun createMultiPartFormData(
    files: List<KmpFile>,
    fieldName: String,
    body: String
): MultiPartFormDataContent {
    return MultiPartFormDataContent(
        formData {
            // 添加文件
            files.forEach { file ->
                append(
                    fieldName,
                    file.file.readBytes(),
                    Headers.build {
                        append(HttpHeaders.ContentType, ContentType.Application.OctetStream)
                        append(HttpHeaders.ContentDisposition, "filename=${file.file.name}")
                    }
                )
            }
            // 如果有请求体，作为表单字段添加
            if (body.isNotEmpty()) {
                append("body", body)
            }
        }
    )
}