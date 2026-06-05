package com.nexius.devtoy.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appstractive.jwt.JWT
import com.appstractive.jwt.from
import com.mohamedrejeb.calf.picker.FilePickerFileType
import com.mohamedrejeb.calf.picker.FilePickerSelectionMode
import com.mohamedrejeb.calf.picker.rememberFilePickerLauncher
import com.nexius.devtoy.utils.Ascii85File
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection
import java.io.File
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Base85DeEncode() {
    // 状态
    var textContent by remember { mutableStateOf("") }
    var selectedFileInfo by remember { mutableStateOf("未选择文件") }
    val clipboard = remember { Toolkit.getDefaultToolkit().systemClipboard }

    // Snackbar消息提示
    val snackHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    fun showMsg(msg: String) = scope.launch {
        snackHostState.showSnackbar(msg, duration = SnackbarDuration.Short)
    }

    // 文件选择器
    val filePicker = FilePicker(
        fileType = FilePickerFileType.All,
        onResult = { files, _ ->
            val file = files.firstOrNull() ?: return@FilePicker
            selectedFileInfo = "${file.file.absolutePath}"
            runCatching {
                textContent = Ascii85File.encode(file.file.readBytes(), "application/octet-stream", file.file.name)
                showMsg("文件编码成功")
            }.onFailure {
                showMsg("读取失败：${it.message}")
            }
        }
    ) {}

    // 保存目录选择器
    val saveDirPicker = rememberFilePickerLauncher(
        type = FilePickerFileType.Folder,
        selectionMode = FilePickerSelectionMode.Single,
        onResult = { files ->
            val path = files.firstOrNull() ?: return@rememberFilePickerLauncher
            runCatching {
                val result = Ascii85File.decode(textContent)
                val saveFile = File(path.file, result.fileName)
                saveFile.createNewFile()
                saveFile.writeBytes(result.data)
                showMsg("✅ 文件保存成功：${saveFile.absolutePath}")
            }.onFailure {
                showMsg("❌ 保存失败：${it.message}")
            }
        }
    )

    // 主界面
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Base85文本 ↔ 文件") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        snackbarHost = { SnackbarHost(snackHostState) },
        containerColor = Color(0xFFF5F7FA)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 顶部：仅文件选择区域
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFE0E5EC), RoundedCornerShape(12.dp))
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { filePicker.launch() },
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF475569)),
                        modifier = Modifier
                            .width(100.dp)
                            .height(38.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("选择文件", fontSize = 12.sp)
                    }

                    TextField(
                        value = selectedFileInfo,
                        onValueChange = { selectedFileInfo = it },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Gray,
                            focusedIndicatorColor = Color.Blue,
                            cursorColor = Color.Blue,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent,
                        ),
                        shape = RoundedCornerShape(0.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(start = 10.dp, bottom = 4.dp),
                        textStyle = TextStyle(fontSize = 12.sp),
                        interactionSource = remember { MutableInteractionSource() }
                    )

                    IconButton(onClick = {
                        var selectfile = File(selectedFileInfo)
                        if(!selectfile.exists()){
                            showMsg("文件不存在")
                            return@IconButton
                        }
                        runCatching {
                            textContent = Ascii85File.encode(selectfile.readBytes(), "application/octet-stream", selectfile.name)
                        }.onFailure {
                            showMsg("读取失败：${it.message}")
                        }
                    }) {
                        Icon(
                            Icons.convertToText,
                            contentDescription = "转文本",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            // ====================== 文本内容区域（整合按钮 + 标题 + 输入框）======================
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                // 文本头部 + 功能按钮（放在同一行）
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 左侧标题 + 长度
                    Column {
                        Text("文本内容", style = MaterialTheme.typography.titleSmall)
                        Text("长度：${textContent.length}", style = MaterialTheme.typography.labelSmall)
                    }

                    // 右侧：复制 + 粘贴 + 保存文件 按钮
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Button(
                            onClick = {
                                val sel = StringSelection(textContent)
                                clipboard.setContents(sel, sel)
                                showMsg("✅ 文本已复制")
                            },
                            modifier = Modifier.size(width = 70.dp, height = 34.dp),
                            shape = RoundedCornerShape(6.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0284C7)),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text("复制", fontSize = 12.sp)
                        }

                        Button(
                            onClick = {
                                runCatching {
                                    val str = clipboard.getData(DataFlavor.stringFlavor) as? String ?: ""
                                    textContent = str
                                    showMsg("✅ 粘贴成功")
                                }.onFailure {
                                    showMsg("❌ 粘贴失败")
                                }
                            },
                            modifier = Modifier.size(width = 70.dp, height = 34.dp),
                            shape = RoundedCornerShape(6.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF059669)),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text("粘贴", fontSize = 12.sp)
                        }

                        Button(
                            onClick = { saveDirPicker.launch() },
                            modifier = Modifier.size(width = 80.dp, height = 34.dp),
                            shape = RoundedCornerShape(6.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC2626)),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text("保存文件", fontSize = 12.sp)
                        }
                    }
                }

                Spacer(Modifier.height(6.dp))

                // 文本输入框（占满剩余高度）
                TextField(
                    value = textContent,
                    onValueChange = { textContent = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White),
                    label = { Text("Base85 编码文本") },
                    minLines = 1,
                    maxLines = Int.MAX_VALUE,
                    textStyle = TextStyle(fontSize = 13.sp)
                )
            }
        }
    }
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

/**
 * 文本命名格式转换工具
 * 支持：大驼峰、小驼峰、下划线、大写下划线、连字符
 */
@Composable
fun TextCaseConvert() {
    // 输入输出状态
    var inputText by remember { mutableStateOf("") }
    var outputText by remember { mutableStateOf("") }
    var convertTip by remember { mutableStateOf("请输入文本，点击下方按钮转换") }

    // 系统剪贴板
    val clipboard = remember { Toolkit.getDefaultToolkit().systemClipboard }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 输入区域
        OutlinedTextField(
            value = inputText,
            onValueChange = {
                inputText = it
                convertTip = "请点击转换按钮"
            },
            label = { Text("原始文本") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            singleLine = false,
            maxLines = Int.MAX_VALUE,
            textStyle = TextStyle(fontSize = 13.sp)
        )

        // 转换按钮行
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { outputText = inputText.toUpperCamelCase() },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("大驼峰", fontSize = 12.sp)
            }
            Button(
                onClick = { outputText = inputText.toLowerCamelCase() },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("小驼峰", fontSize = 12.sp)
            }
            Button(
                onClick = { outputText = inputText.toSnakeCase() },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("下划线", fontSize = 12.sp)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { outputText = inputText.toUpperSnakeCase() },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("大写下划线", fontSize = 12.sp)
            }
            Button(
                onClick = { outputText = inputText.toKebabCase() },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("连字符", fontSize = 12.sp)
            }
            Button(
                onClick = { inputText = ""; outputText = ""; convertTip = "已清空" },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("清空", fontSize = 12.sp)
            }
        }

        // 提示文本
        Text(
            text = convertTip,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(vertical = 2.dp)
        )

        // 输出区域 + 复制按钮
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = outputText,
                onValueChange = {},
                label = { Text("转换结果") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                singleLine = false,
                maxLines = Int.MAX_VALUE,
                textStyle = TextStyle(fontSize = 13.sp)
            )

            // 复制按钮
            Button(
                onClick = {
                    if (outputText.isBlank()) {
                        convertTip = "无内容可复制"
                        return@Button
                    }
                    val selection = StringSelection(outputText)
                    clipboard.setContents(selection, selection)
                    convertTip = "✅ 复制成功"
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("复制结果到剪贴板")
            }
        }
    }
}

// ====================== 核心转换工具方法 ======================

/**
 * 分割文本为单词列表（支持空格、下划线、连字符、驼峰）
 */
private fun String.splitToWords(): List<String> {
    if (isBlank()) return emptyList()
    return trim()
        // 处理下划线、连字符、空格
        .replace(Regex("[-_\\s]+"), " ")
        // 处理驼峰：大写字母前加空格
        .replace(Regex("(?<=[a-z])(?=[A-Z])"), " ")
        .split(" ")
        .filter { it.isNotBlank() }
        .map { it.lowercase() }
}

/**
 * 大驼峰 UpperCamelCase
 */
fun String.toUpperCamelCase(): String {
    return splitToWords()
        .joinToString("") { it.replaceFirstChar { c -> c.uppercase() } }
}

/**
 * 小驼峰 lowerCamelCase
 */
fun String.toLowerCamelCase(): String {
    val words = splitToWords()
    if (words.isEmpty()) return ""
    return words.first() + words.drop(1)
        .joinToString("") { it.replaceFirstChar { c -> c.uppercase() } }
}

/**
 * 下划线 snake_case
 */
fun String.toSnakeCase(): String {
    return splitToWords().joinToString("_")
}

/**
 * 大写下划线 SCREAMING_SNAKE_CASE
 */
fun String.toUpperSnakeCase(): String {
    return splitToWords().joinToString("_") { it.uppercase() }
}

/**
 * 连字符 kebab-case
 */
fun String.toKebabCase(): String {
    return splitToWords().joinToString("-")
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