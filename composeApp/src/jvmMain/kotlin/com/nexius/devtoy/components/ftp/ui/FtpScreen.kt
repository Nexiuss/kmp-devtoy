package com.nexius.devtoy.components.ftp.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.InsertDriveFile
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.nexius.devtoy.components.ftp.FtpViewModel
import com.nexius.devtoy.components.ftp.ProtocolType
import java.io.File

@Composable
fun FtpScreen(viewModel: FtpViewModel = remember { FtpViewModel() }) {
    var host by remember { mutableStateOf("") }
    var port by remember { mutableStateOf("21") }
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var selectedProtocol by remember { mutableStateOf(ProtocolType.FTP) }

    Column {
        // 顶部连接相关输入
        Row {
            DropdownMenu(
                expanded = false,
                onDismissRequest = { /* ... */ }
            ) {
                ProtocolType.values().forEach { type ->
                    DropdownMenuItem(onClick = { selectedProtocol = type; viewModel.protocolType = type }) {
                        Text(type.name)
                    }
                }
            }
            OutlinedTextField(host, { host = it }, label = { androidx.compose.material3.Text("Host") })
            OutlinedTextField(port, { port = it }, label = { Text("Port") })
            OutlinedTextField(user, { user = it }, label = { Text("Username") })
            OutlinedTextField(
                pass,
                { pass = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            Button(onClick = { viewModel.connect(host, port.toIntOrNull() ?: 21, user, pass) }) {
                Text("连接")
            }
            if (viewModel.connected) Button(onClick = { viewModel.disconnect() }) { Text("断开") }
        }

        Divider()

        // 文件列表
        Text("当前目录: " + viewModel.currentPath)
        LazyColumn {
            items(viewModel.files) { file ->
                Row(
                    Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onDoubleTap = {
                                if (file.isDirectory) {
                                    viewModel.listFiles(file.path)
                                }
                            }
                        )
                    }.padding(8.dp)
                ) {
                    Icon(
                        if (file.isDirectory) Icons.Default.Folder else Icons.Default.InsertDriveFile,
                        contentDescription = null
                    )
                    Text(file.name)
                    Spacer(Modifier.weight(1f))
                    Text("${file.size} bytes")
                    if (!file.isDirectory) {
                        Button(onClick = { viewModel.download(file, File("./${file.name}")) }) { Text("下载") }
                    }
                    Button(onClick = { viewModel.delete(file) }) { Text("删除") }
                    Button(onClick = { /* 重命名实现 */ }) { Text("重命名") }
                }
            }
        }
        // 上传、本地文件选择等可以自行扩展
    }
}