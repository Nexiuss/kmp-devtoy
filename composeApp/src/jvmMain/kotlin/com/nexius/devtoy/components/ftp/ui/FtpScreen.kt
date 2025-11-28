package com.nexius.devtoy.components.ftp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.InsertDriveFile
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexius.devtoy.components.ftp.FtpFile
import com.nexius.devtoy.components.ftp.FtpViewModel
import com.nexius.devtoy.components.ftp.ProtocolType
import compose.icons.FeatherIcons
import compose.icons.feathericons.*
import java.io.File
import java.text.DecimalFormat

// 文件大小格式化工具
private val fileSizeFormat = DecimalFormat("#.##")

// 格式化文件大小（B -> KB -> MB -> GB）
fun formatFileSize(bytes: Long): String {
    return when {
        bytes >= 1024 * 1024 * 1024 -> "${fileSizeFormat.format(bytes.toDouble() / (1024 * 1024 * 1024))} GB"
        bytes >= 1024 * 1024 -> "${fileSizeFormat.format(bytes.toDouble() / (1024 * 1024))} MB"
        bytes >= 1024 -> "${fileSizeFormat.format(bytes.toDouble() / 1024)} KB"
        else -> "$bytes B"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FtpScreen(
    viewModel: FtpViewModel = remember { FtpViewModel() }
) {
    // 状态管理
    var host by remember { mutableStateOf("47.120.59.111") }
    var port by remember { mutableStateOf("22") }
    var user by remember { mutableStateOf("root") }
    var pass by remember { mutableStateOf("Nexius@123") }
    var selectedProtocol by remember { mutableStateOf(ProtocolType.FTP) }
    var expandedProtocol by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    // 新增：控制配置界面显示/隐藏的状态
    var isConfigVisible by remember { mutableStateOf(true) }

    // 监听连接状态
    LaunchedEffect(viewModel.connected) {
        if (viewModel.connected) {
            viewModel.pwd()
            viewModel.listFiles(viewModel.currentPath)
        }
    }

    // 监听加载状态
    LaunchedEffect(viewModel.isLoading) {
        isLoading = viewModel.isLoading
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("FTP 客户端") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                // 新增：在顶部栏添加显示/隐藏配置按钮
                actions = {
                    IconButton(
                        onClick = { isConfigVisible = !isConfigVisible },
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f))
                    ) {
                        Icon(
                            imageVector = if (isConfigVisible) FeatherIcons.ChevronUp else FeatherIcons.ChevronDown,
                            contentDescription = if (isConfigVisible) "隐藏配置" else "显示配置",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // 连接配置卡片 - 新增可见性控制
            if (isConfigVisible) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // 新增：配置标题行（包含隐藏按钮）
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "连接配置",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                            IconButton(
                                onClick = { isConfigVisible = false },
                                modifier = Modifier.size(28.dp)
                            ) {
                                Icon(
                                    imageVector = FeatherIcons.X,
                                    contentDescription = "关闭配置",
                                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                )
                            }
                        }

                        // 协议选择
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = "协议:", modifier = Modifier.width(60.dp))
                            Button(
                                onClick = { expandedProtocol = true },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(text = selectedProtocol.name)
                            }
                            DropdownMenu(
                                expanded = expandedProtocol,
                                onDismissRequest = { expandedProtocol = false }
                            ) {
                                ProtocolType.values().forEach { protocol ->
                                    DropdownMenuItem(
                                        text = { Text(protocol.name) },
                                        onClick = {
                                            selectedProtocol = protocol
                                            expandedProtocol = false
                                        }
                                    )
                                }
                            }
                        }

                        // 连接参数输入行
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = host,
                                onValueChange = { host = it },
                                label = { Text("主机") },
                                modifier = Modifier.weight(1.5f),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    focusedLabelColor = MaterialTheme.colorScheme.primary
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )

                            OutlinedTextField(
                                value = port,
                                onValueChange = { port = it },
                                label = { Text("端口") },
                                modifier = Modifier.weight(0.8f),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    focusedLabelColor = MaterialTheme.colorScheme.primary
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                        }

                        // 认证信息输入行
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = user,
                                onValueChange = { user = it },
                                label = { Text("用户名") },
                                modifier = Modifier.weight(1f),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    focusedLabelColor = MaterialTheme.colorScheme.primary
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )

                            OutlinedTextField(
                                value = pass,
                                onValueChange = { pass = it },
                                label = { Text("密码") },
                                modifier = Modifier.weight(1f),
                                singleLine = true,
                                visualTransformation = PasswordVisualTransformation(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    focusedLabelColor = MaterialTheme.colorScheme.primary
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                        }

                        // 连接控制按钮
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {
                                    viewModel.connect(
                                        host = host,
                                        port = port.toIntOrNull() ?: 22,
                                        user = user,
                                        pass = pass
                                    )
                                },
                                modifier = Modifier.weight(1f),
                                enabled = !viewModel.connected && !isLoading,
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                )
                            ) {
                                if (isLoading) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(20.dp),
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        strokeWidth = 2.dp
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                }
                                Text(text = if (isLoading) "连接中..." else "连接")
                            }

                            Button(
                                onClick = { viewModel.disconnect() },
                                modifier = Modifier.weight(1f),
                                enabled = viewModel.connected && !isLoading,
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.error,
                                    contentColor = MaterialTheme.colorScheme.onError
                                )
                            ) {
                                Text(text = "断开连接")
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            } else {
                // 配置隐藏时显示的折叠提示条
                if (!viewModel.connected) {
                    // 未连接时显示提示条，方便用户重新打开配置
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.8f)
                        ),
                        onClick = { isConfigVisible = true }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                imageVector = FeatherIcons.ChevronUp,
                                contentDescription = "显示配置",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "点击展开连接配置",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // 目录导航栏
            if (viewModel.connected) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // 导航按钮组
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            IconButton(
                                onClick = { viewModel.cdp() },
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                            ) {
                                Icon(
                                    imageVector = FeatherIcons.ArrowLeft,
                                    contentDescription = "后退",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }

                            IconButton(
                                onClick = { viewModel.pwd() },
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                            ) {
                                Icon(
                                    imageVector = FeatherIcons.MousePointer,
                                    contentDescription = "当前路径",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }

                            IconButton(
                                onClick = { viewModel.listFiles(viewModel.currentPath) },
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "刷新",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        // 当前路径显示
                        Box(
                            modifier = Modifier
                                .weight(3f)
                                .padding(8.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.background,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .padding(8.dp)
                        ) {
                            Text(
                                text = viewModel.currentPath,
                                style = MaterialTheme.typography.bodyMedium,
                                maxLines = 1,
                                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
            }

            // 文件列表区域
            if (viewModel.connected) {
                Text(
                    text = "文件列表",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    if (isLoading) {
                        // 加载中状态
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = MaterialTheme.colorScheme.primary
                        )
                    } else if (viewModel.files.isEmpty()) {
                        // 空列表状态
                        Text(
                            text = "当前目录为空",
                            modifier = Modifier.align(Alignment.Center),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        )
                    } else {
                        // 文件列表
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(viewModel.files) { file ->
                                FileItem(
                                    file = file,
                                    onDoubleClick = {
                                        if (file.isDirectory) {
                                            viewModel.listFiles(file.path)
                                        }
                                    },
                                    onDownload = { viewModel.download(file, File("./${file.name}")) },
                                    onDelete = { viewModel.delete(file) },
                                    onRename = { /* 实现重命名逻辑 */ }
                                )
                            }
                        }
                    }
                }
            } else {
                // 未连接状态提示
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                                    MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0f)
                                )
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "请先配置连接信息并建立连接",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            fontSize = 18.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun FileItem(
    file: FtpFile,
    onDoubleClick: () -> Unit,
    onDownload: () -> Unit,
    onDelete: () -> Unit,
    onRename: () -> Unit
) {
    var expandedMenu by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {onDoubleClick.invoke()},
                    onTap = { expandedMenu = false }
                )
            }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 文件图标
        Icon(
            imageVector = if (file.isDirectory) {
                Icons.Default.FolderOpen
            } else {
                Icons.Default.InsertDriveFile
            },
            contentDescription = if (file.isDirectory) "文件夹" else "文件",
            modifier = Modifier.size(24.dp),
            tint = if (file.isDirectory) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            }
        )

        // 文件名
        Text(
            text = file.name,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = if (file.isDirectory) FontWeight.SemiBold else FontWeight.Normal
            ),
            maxLines = 1,
            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
        )

        // 文件大小
        Text(
            text = if (file.isDirectory) "文件夹" else formatFileSize(file.size),
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            ),
            modifier = Modifier.width(100.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.End
        )

        // 操作按钮
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            if (!file.isDirectory) {
                IconButton(
                    onClick = onDownload,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = FeatherIcons.Download,
                        contentDescription = "下载",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // 更多操作菜单
            IconButton(
                onClick = { expandedMenu = true },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = FeatherIcons.MoreVertical,
                    contentDescription = "更多操作",
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            DropdownMenu(
                expanded = expandedMenu,
                onDismissRequest = { expandedMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text("重命名") },
                    onClick = {
                        onRename()
                        expandedMenu = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("删除", color = MaterialTheme.colorScheme.error) },
                    onClick = {
                        onDelete()
                        expandedMenu = false
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = FeatherIcons.Trash2,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                )
            }
        }
    }

    // 分隔线
    Divider(
        modifier = Modifier.padding(start = 48.dp),
        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
    )
}