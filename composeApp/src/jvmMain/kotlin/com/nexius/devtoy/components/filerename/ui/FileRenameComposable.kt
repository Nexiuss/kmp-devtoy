package com.nexius.devtoy.components.filerename.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
    val scrollState = rememberScrollState()

    // 现代化主题色
    val primaryColor = Color(0xFF4A6FFF)
    val surfaceColor = Color(0xFFFAFAFA)
    val cardColor = Color.White
    val dividerColor = Color(0xFFEEEEEE)

    MaterialTheme(
        colors = MaterialTheme.colors.copy(
            primary = primaryColor,
            surface = surfaceColor,
            background = surfaceColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .verticalScroll(scrollState)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // 1. 文件选择区域
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = 2.dp,
                backgroundColor = cardColor
            ) {
                Column(Modifier.padding(20.dp)) {
                    FileSelectionArea(
                        selectedFiles = appState.selectedFiles,
                        onFilesSelected = { files ->
                            appState.selectedFiles.clear()
                            appState.selectedFiles.addAll(files)
                            appState.refreshPreview()
                        }
                    )
                }
            }

            // 2. 规则配置区域
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = 2.dp,
                backgroundColor = cardColor
            ) {
                Column(Modifier.padding(20.dp)) {
                    RuleConfigArea(appState = appState)
                }
            }

            // 3. 预览和执行区域
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = 2.dp,
                backgroundColor = cardColor
            ) {
                Column(Modifier.padding(20.dp)) {
                    PreviewAndExecuteArea(
                        renameFiles = appState.renameFiles.value,
                        onExecute = {
                            val (success, fail) = BatchRenameUtil.executeRename(it)
                            appState.operationResult.value = "✅ 执行完成：成功 $success 个，失败 $fail 个"
                        },
                        operationResult = appState.operationResult.value
                    )
                }
            }
        }
    }
}

// 1. 文件选择区域
@Composable
fun FileSelectionArea(
    selectedFiles: List<Path>,
    onFilesSelected: (List<Path>) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "文件选择",
            style = MaterialTheme.typography.h6.copy(fontSize = 18.sp),
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Button(
            onClick = {
                val fileDialog = FileDialog(Frame(), "选择文件", FileDialog.LOAD).apply {
                    isMultipleMode = true
                    isVisible = true
                }
                val files = fileDialog.files?.map { Paths.get(it.absolutePath) } ?: emptyList()
                onFilesSelected(files)
            },
            modifier = Modifier
                .height(44.dp)
                .clip(RoundedCornerShape(12.dp)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary
            )
        ) {
            Text("选择文件", fontSize = 14.sp)
        }

        Text("已选择：${selectedFiles.size} 个文件", style = MaterialTheme.typography.body2)

        if (selectedFiles.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF5F7FF))
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                val displayFiles = selectedFiles.take(5)
                displayFiles.forEach { path ->
                    Text(
                        text = path.fileName.toString(),
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
                if (selectedFiles.size > 5) {
                    Text(
                        "... 共 ${selectedFiles.size} 个文件",
                        style = MaterialTheme.typography.body2.copy(color = Color.Gray)
                    )
                }
            }
        }
    }
}

// 2. 规则配置区域
@Composable
fun RuleConfigArea(appState: AppState) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "重命名规则",
            style = MaterialTheme.typography.h6.copy(fontSize = 18.sp),
            modifier = Modifier.padding(bottom = 4.dp)
        )

        // 前缀
        RuleRow(label = "前缀") {
            OutlinedTextField(
                value = appState.prefixText.value,
                onValueChange = { appState.prefixText.value = it },
                modifier = Modifier.weight(1f).height(50.dp),
                shape = RoundedCornerShape(12.dp),
                textStyle = TextStyle(fontSize = 14.sp)
            )

            Spacer(Modifier.width(12.dp))

            Button(
                onClick = {
                    appState.activeRules.add(RenameRule(RenameRuleType.PREFIX, appState.prefixText.value))
                    appState.refreshPreview()
                },
                modifier = Modifier.height(50.dp).clip(RoundedCornerShape(12.dp))
            ) {
                Text("添加前缀", fontSize = 14.sp)
            }
        }

        // 后缀
        RuleRow(label = "后缀") {
            OutlinedTextField(
                value = appState.suffixText.value,
                onValueChange = { appState.suffixText.value = it },
                modifier = Modifier.weight(1f).height(50.dp),
                shape = RoundedCornerShape(12.dp),
                textStyle = TextStyle(fontSize = 14.sp)
            )

            Spacer(Modifier.width(12.dp))

            Button(
                onClick = {
                    appState.activeRules.add(RenameRule(RenameRuleType.SUFFIX, appState.suffixText.value))
                    appState.refreshPreview()
                },
                modifier = Modifier.height(50.dp).clip(RoundedCornerShape(12.dp))
            ) {
                Text("添加后缀", fontSize = 14.sp)
            }
        }

        // 替换
        RuleRow(label = "替换") {
            OutlinedTextField(
                value = appState.replaceTarget.value,
                onValueChange = { appState.replaceTarget.value = it },
                modifier = Modifier.weight(1f).height(50.dp),
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text("目标文本", fontSize = 13.sp) },
                textStyle = TextStyle(fontSize = 14.sp)
            )

            Spacer(Modifier.width(12.dp))

            OutlinedTextField(
                value = appState.replaceValue.value,
                onValueChange = { appState.replaceValue.value = it },
                modifier = Modifier.weight(1f).height(50.dp),
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text("替换为", fontSize = 13.sp) },
                textStyle = TextStyle(fontSize = 14.sp)
            )

            Spacer(Modifier.width(12.dp))

            Button(
                onClick = {
                    appState.activeRules.add(
                        RenameRule(
                            type = RenameRuleType.REPLACE,
                            value = appState.replaceValue.value,
                            replaceTarget = appState.replaceTarget.value
                        )
                    )
                    appState.refreshPreview()
                },
                modifier = Modifier.height(50.dp).clip(RoundedCornerShape(12.dp))
            ) {
                Text("替换", fontSize = 14.sp)
            }
        }

        // 序号
        RuleRow(label = "序号") {
            OutlinedTextField(
                value = appState.numberStart.value.toString(),
                onValueChange = { appState.numberStart.value = it.toIntOrNull() ?: 1 },
                modifier = Modifier.weight(1f).height(50.dp),
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text("起始值", fontSize = 13.sp) },
                textStyle = TextStyle(fontSize = 14.sp)
            )

            Spacer(Modifier.width(12.dp))

            OutlinedTextField(
                value = appState.numberPadding.value.toString(),
                onValueChange = { appState.numberPadding.value = it.toIntOrNull() ?: 2 },
                modifier = Modifier.weight(1f).height(50.dp),
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text("补零位数", fontSize = 13.sp) },
                textStyle = TextStyle(fontSize = 14.sp)
            )

            Spacer(Modifier.width(12.dp))

            Button(
                onClick = {
                    appState.activeRules.add(
                        RenameRule(
                            type = RenameRuleType.NUMBERING,
                            numberStart = appState.numberStart.value,
                            numberPadding = appState.numberPadding.value
                        )
                    )
                    appState.refreshPreview()
                },
                modifier = Modifier.height(50.dp).clip(RoundedCornerShape(12.dp))
            ) {
                Text("添加序号", fontSize = 14.sp)
            }
        }

        // 大小写
        RuleRow(label = "大小写") {
            Button(
                onClick = {
                    appState.activeRules.add(RenameRule(RenameRuleType.UPPER_CASE))
                    appState.refreshPreview()
                },
                modifier = Modifier.height(50.dp).clip(RoundedCornerShape(12.dp))
            ) {
                Text("转为大写", fontSize = 14.sp)
            }

            Spacer(Modifier.width(12.dp))

            Button(
                onClick = {
                    appState.activeRules.add(RenameRule(RenameRuleType.LOWER_CASE))
                    appState.refreshPreview()
                },
                modifier = Modifier.height(50.dp).clip(RoundedCornerShape(12.dp))
            ) {
                Text("转为小写", fontSize = 14.sp)
            }
        }

        // 已添加规则标签
        if (appState.activeRules.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("已应用规则", style = MaterialTheme.typography.subtitle1)

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    appState.activeRules.forEachIndexed { index, rule ->
                        RuleTag(
                            rule = rule,
                            onDelete = {
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

// 规则行统一样式
@Composable
fun RuleRow(
    label: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(label, style = MaterialTheme.typography.body2.copy(color = Color(0xFF666666)))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            content()
        }
    }
}

// 规则标签（美观版）
@Composable
fun RuleTag(
    rule: RenameRule,
    onDelete: () -> Unit
) {
    val tagColor = Color(0xFFE3F2FD)
    val textColor = MaterialTheme.colors.primary

    Surface(
        modifier = Modifier.shadow(1.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        color = tagColor
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(
                text = when (rule.type) {
                    RenameRuleType.PREFIX -> "前缀：${rule.value}"
                    RenameRuleType.SUFFIX -> "后缀：${rule.value}"
                    RenameRuleType.REPLACE -> "替换文本"
                    RenameRuleType.NUMBERING -> "序号"
                    RenameRuleType.UPPER_CASE -> "大写"
                    RenameRuleType.LOWER_CASE -> "小写"
                },
                style = TextStyle(fontSize = 13.sp, color = textColor)
            )

            Spacer(Modifier.width(6.dp))

            Box(
                modifier = Modifier
                    .size(18.dp)
                    .clip(CircleShape)
                    .clickable { onDelete() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "×",
                    style = TextStyle(fontSize = 14.sp, color = Color(0xFFD32F2F))
                )
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
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "预览与执行",
            style = MaterialTheme.typography.h6.copy(fontSize = 18.sp),
            modifier = Modifier.padding(bottom = 4.dp)
        )

        // 预览列表
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF9FAFC))
                .border(1.dp, Color(0xFFEAEAEA), RoundedCornerShape(12.dp)),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(renameFiles) { file ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        file.originalName,
                        modifier = Modifier.weight(1f),
                        style = TextStyle(fontSize = 14.sp)
                    )
                    Text(
                        "→",
                        modifier = Modifier.padding(horizontal = 12.dp),
                        color = Color.Gray
                    )
                    Text(
                        file.newName,
                        modifier = Modifier.weight(1f),
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = if (file.isValid) Color.Black else Color(0xFFD32F2F)
                        )
                    )
                    if (file.errorMsg.isNotEmpty()) {
                        Text(
                            "(${file.errorMsg})",
                            color = Color(0xFFD32F2F),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
                if (renameFiles.last() != file) {
                    Divider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = Color(0xFFEEEEEE)
                    )
                }
            }
        }

        // 执行按钮
        Button(
            onClick = { onExecute(renameFiles) },
            enabled = renameFiles.isNotEmpty() && renameFiles.all { it.isValid },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(12.dp)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (renameFiles.isNotEmpty() && renameFiles.all { it.isValid }) {
                    MaterialTheme.colors.primary
                } else {
                    Color(0xFFBDBDBD)
                }
            )
        ) {
            Text("执行批量重命名", fontSize = 15.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Medium)
        }

        // 操作结果
        if (operationResult.isNotEmpty()) {
            Text(
                operationResult,
                color = Color(0xFF2E7D32),
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}