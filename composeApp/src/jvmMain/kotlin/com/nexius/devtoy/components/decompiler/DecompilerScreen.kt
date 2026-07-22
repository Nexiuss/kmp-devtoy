package com.nexius.devtoy.components.decompiler

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexius.devtoy.components.Icons
import com.nexius.devtoy.components.decompiler.model.FileTreeNode
import java.awt.FileDialog
import java.awt.Frame
import java.io.File

@Composable
fun DecompilerScreen() {
    val viewModel = remember { DecompilerViewModel() }
    var showReplaceDialog by remember { mutableStateOf(false) }
    var selectedClassPath by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 顶部文件选择区域
        FileSelectionArea(viewModel)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // 压缩信息
        if (viewModel.compressionInfo.isNotEmpty()) {
            Text(
                text = viewModel.compressionInfo,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        
        // 下方左右分栏（支持拖拽调整宽度）
        var splitRatio by remember { mutableStateOf(0.35f) }
        val minSplitRatio = 0.15f
        val maxSplitRatio = 0.7f
        val density = LocalDensity.current
        
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            val totalWidthPx = with(density) { maxWidth.toPx() }
            
            Row(modifier = Modifier.fillMaxSize()) {
                // 左侧文件树
                FileTreePanel(
                    modifier = Modifier.fillMaxHeight().weight(splitRatio),
                    viewModel = viewModel,
                    onFileSelected = { filePath, isClass ->
                        selectedClassPath = filePath
                        if (isClass) {
                            viewModel.decompileClass(filePath)
                        } else {
                            viewModel.loadTextFile(filePath)
                        }
                    },
                    onReplaceRequest = { classPath ->
                        selectedClassPath = classPath
                        showReplaceDialog = true
                    }
                )
                
                // 可拖拽分隔条
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(8.dp)
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                change.consume()
                                val deltaRatio = dragAmount.x / totalWidthPx
                                splitRatio = (splitRatio + deltaRatio).coerceIn(minSplitRatio, maxSplitRatio)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    VerticalDivider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }
                
                // 右侧代码显示
                CodePanel(
                    modifier = Modifier.fillMaxHeight().weight(1f - splitRatio),
                    viewModel = viewModel,
                    title = if (selectedClassPath.endsWith(".class")) "反编译代码" else "文件内容"
                )
            }
        }
        
        // 状态栏
        Text(
            text = viewModel.statusMessage,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
    
    // 替换对话框
    if (showReplaceDialog) {
        ReplaceClassDialog(
            targetClassPath = selectedClassPath,
            onConfirm = { newFile ->
                viewModel.replaceClassInJar(selectedClassPath, newFile)
                showReplaceDialog = false
            },
            onDismiss = { showReplaceDialog = false }
        )
    }
}

@Composable
private fun FileSelectionArea(viewModel: DecompilerViewModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "选择 JAR/WAR/ZIP/Class 文件",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
            )
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = viewModel.selectedFilePath,
                    onValueChange = { },
                    readOnly = true,
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("选择要反编译的文件") },
                    singleLine = true
                )
                
                Button(
                    onClick = {
                        val fileDialog = FileDialog(Frame(), "选择文件", FileDialog.LOAD).apply {
                            file = "*.jar;*.war;*.zip;*.class"
                            isVisible = true
                        }
                        fileDialog.file?.let { fileName ->
                            fileDialog.directory?.let { dir ->
                                val file = File(dir, fileName)
                                viewModel.loadFile(file)
                            }
                        }
                    }
                ) {
                    Icon(Icons.FolderOpen, contentDescription = "浏览")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("浏览")
                }
                
                IconButton(
                    onClick = { viewModel.clearAll() }
                ) {
                    Icon(Icons.Clear, contentDescription = "清空")
                }
            }
        }
    }
}

@Composable
private fun FileTreePanel(
    modifier: Modifier = Modifier,
    viewModel: DecompilerViewModel,
    onFileSelected: (String, Boolean) -> Unit,
    onReplaceRequest: (String) -> Unit
) {
    Card(
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
            Text(
                text = "文件结构",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
                modifier = Modifier.padding(8.dp)
            )
            
            val root = viewModel.fileTreeRoot
            if (root != null) {
                val horizontalScrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .horizontalScroll(horizontalScrollState)
                ) {
                    root.children.sortedWith(compareByDescending<FileTreeNode> { it.isDirectory }.thenBy { it.name }).forEach { node ->
                        FileTreeItem(
                            node = node,
                            level = 0,
                            viewModel = viewModel,
                            onClick = { clickedNode ->
                                if (clickedNode.isClassFile) {
                                    onFileSelected(clickedNode.fullPath, true)
                                } else if (!clickedNode.isDirectory && viewModel.isTextFile(clickedNode.name)) {
                                    onFileSelected(clickedNode.fullPath, false)
                                }
                            },
                            onReplace = { clickedNode ->
                                if (clickedNode.isClassFile) {
                                    onReplaceRequest(clickedNode.fullPath)
                                }
                            }
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "请选择文件",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun FileTreeItem(
    node: FileTreeNode,
    level: Int,
    viewModel: DecompilerViewModel,
    onClick: (FileTreeNode) -> Unit,
    onReplace: (FileTreeNode) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val hasChildren = node.children.isNotEmpty()
    
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = (level * 16).dp)
                .clickable {
                    if (hasChildren) {
                        expanded = !expanded
                    }
                    onClick(node)
                }
                .padding(vertical = 4.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 展开/折叠图标
            if (hasChildren) {
                Icon(
                    imageVector = if (expanded) Icons.KeyboardArrowDown else Icons.ChevronRight,
                    contentDescription = if (expanded) "折叠" else "展开",
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                Spacer(modifier = Modifier.width(16.dp))
            }
            
            // 文件图标
            val icon = when {
                node.isDirectory -> Icons.Folder
                node.isClassFile -> Icons.Code
                else -> Icons.Attachment
            }
            
            val iconColor = when {
                node.isDirectory -> Color(0xFFFFB300)
                node.isClassFile -> Color(0xFF7C4DFF)
                else -> MaterialTheme.colorScheme.onSurfaceVariant
            }
            
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = iconColor
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            // 文件名
            Text(
                text = node.name,
                style = MaterialTheme.typography.bodySmall,
                color = if (node.isClassFile) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.widthIn(max = 300.dp)
            )
            
            // 文件大小
            if (node.size > 0 && !node.isDirectory) {
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "(${formatFileSize(node.size)})",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            // 替换按钮（仅 class 文件）
            if (node.isClassFile) {
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { onReplace(node) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        Icons.SwapHoriz,
                        contentDescription = "替换",
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
        
        // 子节点
        if (expanded && hasChildren) {
            node.children.sortedWith(compareByDescending<FileTreeNode> { it.isDirectory }.thenBy { it.name }).forEach { child ->
                FileTreeItem(
                    node = child,
                    level = level + 1,
                    viewModel = viewModel,
                    onClick = onClick,
                    onReplace = onReplace
                )
            }
        }
    }
}

@Composable
private fun CodePanel(
    modifier: Modifier = Modifier,
    viewModel: DecompilerViewModel,
    title: String = "反编译代码"
) {
    Card(
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
            // 标题栏
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
                )

                // 复制按钮
                IconButton(
                    onClick = {
                        if (viewModel.decompiledCode.isNotEmpty()) {
                            java.awt.Toolkit.getDefaultToolkit().systemClipboard.setContents(
                                java.awt.datatransfer.StringSelection(viewModel.decompiledCode),
                                null
                            )
                        }
                    }
                ) {
                    Icon(Icons.ContentCopy, contentDescription = "复制代码")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 代码显示区域
            if (viewModel.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (viewModel.decompiledCode.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF1E1E1E))
                        .padding(12.dp)
                ) {
                    val scrollState = rememberScrollState()
                    Text(
                        text = viewModel.decompiledCode,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = FontFamily.Monospace,
                            color = Color(0xFFD4D4D4),
                            lineHeight = 20.sp
                        ),
                        modifier = Modifier.verticalScroll(scrollState)
                    )
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "选择文件查看内容",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}


@Composable
private fun ReplaceClassDialog(
    targetClassPath: String,
    onConfirm: (File) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("替换 Class 文件") },
        text = {
            Column {
                Text("目标: $targetClassPath")
                Spacer(modifier = Modifier.height(16.dp))
                Text("请选择要替换的 class 文件，新文件的类名和包路径必须与目标一致。")
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val fileDialog = FileDialog(Frame(), "选择 Class 文件", FileDialog.LOAD).apply {
                        file = "*.class"
                        isVisible = true
                    }
                    fileDialog.file?.let { fileName ->
                        fileDialog.directory?.let { dir ->
                            onConfirm(File(dir, fileName))
                        }
                    }
                }
            ) {
                Text("选择文件")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("取消")
            }
        }
    )
}

private fun formatFileSize(size: Long): String {
    return when {
        size < 1024 -> "$size B"
        size < 1024 * 1024 -> String.format("%.2f KB", size / 1024.0)
        else -> String.format("%.2f MB", size / (1024.0 * 1024.0))
    }
}

