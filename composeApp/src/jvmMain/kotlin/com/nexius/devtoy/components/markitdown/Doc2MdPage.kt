package com.nexius.devtoy.components.markitdown

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nexius.devtoy.components.filerename.ui.FileSelectionArea
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.nio.file.Path

@Composable
fun DocumentToMarkdownPanel() {
    // 状态
    var selectedFiles by remember { mutableStateOf(emptyList<Path>()) }
    var settings by remember { mutableStateOf(ConvertSettings()) }
    var log by remember { mutableStateOf("准备就绪\n") }
    var isConverting by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // 引擎
    val engine = remember { MarkitdownEngineFactory.createEngine() }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        // 文件选择区域（你指定的组件）
        FileSelectionArea(selectedFiles) {
            selectedFiles = it
            log += "✅ 已选择 ${it.size} 个文件\n"
        }

        HorizontalDivider(Modifier.padding(vertical = 8.dp))

        // 转换选项
        Text("转换选项")
        Row(Modifier.fillMaxWidth().wrapContentHeight()) {
            Checkbox(settings.includeImages, onCheckedChange = { settings = settings.copy(includeImages = it) })
            Text("包含图片")
            Checkbox(settings.includeTables, onCheckedChange = { settings = settings.copy(includeTables = it) })
            Text("包含表格")
            Checkbox(settings.useOcr, onCheckedChange = { settings = settings.copy(useOcr = it) })
            Text("启用OCR")
        }

        Spacer(Modifier.height(8.dp))

        // 按钮
        Button(
            onClick = {
                if (selectedFiles.isEmpty()) {
                    log += "❌ 请先选择文件\n"
                    return@Button
                }

                scope.launch(Dispatchers.IO) {
                    isConverting = true
                    log += "🔄 开始批量转换...\n"

                    val options = MarkitdownEngineFactory.createOptions(settings)

                    selectedFiles.forEach { path ->
                        try {
                            log += "处理：${path.fileName}\n"
                            val result = engine.convert(path, options)
                            val outputPath = path.parent.resolve("${path.fileName}.md")
                            outputPath.toFile().writeText(result.markdown, Charsets.UTF_8)
                            log += "✅ 保存：$outputPath\n"
                        } catch (e: Exception) {
                            log += "❌ 失败：${e.message}\n"
                        }
                    }

                    log += "🏁 全部完成\n"
                    isConverting = false
                }
            },
            enabled = !isConverting
        ) {
            Text(if (isConverting) "转换中..." else "开始批量转换为 Markdown")
        }

        HorizontalDivider(Modifier.padding(vertical = 8.dp))

        // 日志
        Text("运行日志")
        Box(
            Modifier
                .fillMaxWidth()
                .height(220.dp)
                .border(1.dp, MaterialTheme.colorScheme.outline)
                .padding(8.dp)
                .verticalScroll(ScrollState(0))
        ) {
            Text(log)
        }
    }
}