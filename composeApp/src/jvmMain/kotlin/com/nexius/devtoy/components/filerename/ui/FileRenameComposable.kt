package com.nexius.devtoy.components.filerename.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexius.devtoy.components.filerename.model.RenameFile
import com.nexius.devtoy.components.filerename.model.RenameRule
import com.nexius.devtoy.components.filerename.model.RenameRuleType
import com.nexius.devtoy.utils.BatchRenameUtil
import java.awt.FileDialog
import java.awt.Frame
import java.nio.file.Path
import java.nio.file.Paths

// 全局状态（正确的 Compose 响应式状态）
class AppState {
    // 已选文件
    val selectedFiles: SnapshotStateList<Path> = mutableStateListOf()

    // 预览结果
    val renameFiles = mutableStateOf<List<RenameFile>>(emptyList())

    // 规则列表（可观察）
    val activeRules: SnapshotStateList<RenameRule> = mutableStateListOf()

    // 规则配置项
    val prefixText = mutableStateOf("")
    val suffixText = mutableStateOf("")
    val replaceTarget = mutableStateOf("")
    val replaceValue = mutableStateOf("")
    val numberStart = mutableStateOf(1)
    val numberPadding = mutableStateOf(2)

    // 操作结果
    val operationResult = mutableStateOf("")

    // 统一刷新预览
    fun refreshPreview() {
        renameFiles.value = BatchRenameUtil.applyRules(selectedFiles, activeRules)
    }
}

@Composable
fun FileRenameView() {
    val appState = remember { AppState() }

    Column(
        modifier = Modifier
            .fillMaxSize() // 充满父窗口
            .verticalScroll(rememberScrollState()) // 垂直滚动
            .padding(16.dp)
    ) {
        // 1. 文件选择区域
        FileSelectionArea(
            selectedFiles = appState.selectedFiles,
            onFilesSelected = { files ->
                appState.selectedFiles.clear()
                appState.selectedFiles.addAll(files)
                appState.refreshPreview()
            }
        )

        Spacer(Modifier.height(16.dp))
        Divider()
        Spacer(Modifier.height(16.dp))

        // 2. 规则配置区域
        RuleConfigArea(
            appState = appState
        )

        Spacer(Modifier.height(16.dp))
        Divider()
        Spacer(Modifier.height(16.dp))

        // 3. 预览和执行区域
        PreviewAndExecuteArea(
            renameFiles = appState.renameFiles.value,
            onExecute = {
                val (success, fail) = BatchRenameUtil.executeRename(it)
                appState.operationResult.value = "执行完成：成功 $success 个，失败 $fail 个"
            },
            operationResult = appState.operationResult.value
        )
    }
}

// 1. 文件选择区域
@Composable
fun FileSelectionArea(
    selectedFiles: List<Path>,
    onFilesSelected: (List<Path>) -> Unit
) {
    Column {
        Button(
            onClick = {
                val fileDialog = FileDialog(Frame(), "选择文件", FileDialog.LOAD).apply {
                    isMultipleMode = true
                    isVisible = true
                }
                val files = fileDialog.files?.map { Paths.get(it.absolutePath) } ?: emptyList()
                onFilesSelected(files)
            }
        ) {
            Text("选择文件")
        }

        Spacer(Modifier.height(8.dp))
        Text("已选择文件：${selectedFiles.size} 个")

        if (selectedFiles.isNotEmpty()) {
            Text(
                text = buildString {
                    append(selectedFiles.take(3).joinToString("\n") { it.fileName.toString() })
                    if (selectedFiles.size > 3) append("\n... 共 ${selectedFiles.size} 个文件")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .border(1.dp, Color.Gray)
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState())
            )
        }
    }
}

// 2. 规则配置区域
@Composable
fun RuleConfigArea(appState: AppState) {
    Column {
        Text("重命名规则配置", style = MaterialTheme.typography.h6)
        Spacer(Modifier.height(8.dp))

        // 前缀
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = appState.prefixText.value,
                onValueChange = { appState.prefixText.value = it },
                label = { Text("前缀") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                appState.activeRules.add(RenameRule(RenameRuleType.PREFIX, appState.prefixText.value))
                appState.refreshPreview()
            }) {
                Text("添加前缀")
            }
        }

        Spacer(Modifier.height(8.dp))

        // 后缀
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = appState.suffixText.value,
                onValueChange = { appState.suffixText.value = it },
                label = { Text("后缀") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                appState.activeRules.add(RenameRule(RenameRuleType.SUFFIX, appState.suffixText.value))
                appState.refreshPreview()
            }) {
                Text("添加后缀")
            }
        }

        Spacer(Modifier.height(8.dp))

        // 替换
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = appState.replaceTarget.value,
                onValueChange = { appState.replaceTarget.value = it },
                label = { Text("替换目标") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            TextField(
                value = appState.replaceValue.value,
                onValueChange = { appState.replaceValue.value = it },
                label = { Text("替换为") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                appState.activeRules.add(
                    RenameRule(
                        type = RenameRuleType.REPLACE,
                        value = appState.replaceValue.value,
                        replaceTarget = appState.replaceTarget.value
                    )
                )
                appState.refreshPreview()
            }) {
                Text("替换文本")
            }
        }

        Spacer(Modifier.height(8.dp))

        // 序号
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = appState.numberStart.value.toString(),
                onValueChange = { appState.numberStart.value = it.toIntOrNull() ?: 1 },
                label = { Text("序号起始值") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            TextField(
                value = appState.numberPadding.value.toString(),
                onValueChange = { appState.numberPadding.value = it.toIntOrNull() ?: 2 },
                label = { Text("序号补零位数") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                appState.activeRules.add(
                    RenameRule(
                        type = RenameRuleType.NUMBERING,
                        numberStart = appState.numberStart.value,
                        numberPadding = appState.numberPadding.value
                    )
                )
                appState.refreshPreview()
            }) {
                Text("添加序号")
            }
        }

        Spacer(Modifier.height(8.dp))

        // 大小写
        Row {
            Button(onClick = {
                appState.activeRules.add(RenameRule(RenameRuleType.UPPER_CASE))
                appState.refreshPreview()
            }) {
                Text("转为大写")
            }
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                appState.activeRules.add(RenameRule(RenameRuleType.LOWER_CASE))
                appState.refreshPreview()
            }) {
                Text("转为小写")
            }
        }

        // 可删除标签列表（正确版）
        if (appState.activeRules.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            Text("已添加规则：", style = MaterialTheme.typography.subtitle1)
            Spacer(Modifier.height(6.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                appState.activeRules.forEachIndexed { index, rule ->
                    Surface(
                        shape = MaterialTheme.shapes.small,
                        color = Color(0xFFE3F2FD),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        ) {
                            Text(
                                text = when (rule.type) {
                                    RenameRuleType.PREFIX -> "前缀：${rule.value}"
                                    RenameRuleType.SUFFIX -> "后缀：${rule.value}"
                                    RenameRuleType.REPLACE -> "替换"
                                    RenameRuleType.NUMBERING -> "序号"
                                    RenameRuleType.UPPER_CASE -> "大写"
                                    RenameRuleType.LOWER_CASE -> "小写"
                                },
                                style = MaterialTheme.typography.body2
                            )
                            Spacer(Modifier.width(6.dp))
                            ClickableText(
                                text = AnnotatedString("×"),
                                style = TextStyle(color = Color.Red, fontSize = 16.sp),
                                onClick = {
                                    appState.activeRules.removeAt(index)
                                    appState.refreshPreview()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

// 3. 预览和执行区域
@Composable
fun PreviewAndExecuteArea(
    renameFiles: List<RenameFile>,
    onExecute: (List<RenameFile>) -> Unit,
    operationResult: String
) {
    Column {
        Text("重命名预览", style = MaterialTheme.typography.h6)
        Spacer(Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(1.dp, Color.Gray)
        ) {
            items(renameFiles) { file ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(file.originalName, modifier = Modifier.weight(1f))
                    Text("→", modifier = Modifier.padding(horizontal = 8.dp))
                    Text(
                        file.newName,
                        color = if (file.isValid) Color.Black else Color.Red,
                        modifier = Modifier.weight(1f)
                    )
                    if (file.errorMsg.isNotEmpty()) {
                        Text("(${file.errorMsg})", color = Color.Red, fontSize = 12.sp)
                    }
                }
                Divider()
            }
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { onExecute(renameFiles) },
            enabled = renameFiles.isNotEmpty() && renameFiles.all { it.isValid }
        ) {
            Text("执行批量重命名")
        }

        if (operationResult.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            Text(operationResult, color = Color.Green)
        }
    }
}