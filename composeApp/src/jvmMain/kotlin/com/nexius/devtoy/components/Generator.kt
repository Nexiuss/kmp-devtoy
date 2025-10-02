
package com.nexius.devtoy.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Copy
import compose.icons.fontawesomeicons.solid.Check
import kotlinx.coroutines.delay
import java.util.UUID

@Composable
fun UuidGenerator() {
    val selectedOptions = remember {
        mutableStateListOf(false, false)
    }
    var uuid by remember { mutableStateOf(java.util.UUID.randomUUID().toString()) }
    var showCopied by remember { mutableStateOf(false) }
    val clipboardManager = LocalClipboardManager.current
    val options = listOf("连字符", "大写字符")
    // 处理选项变化
    LaunchedEffect(selectedOptions[0], selectedOptions[1],uuid) {
        var text = uuid
        if (selectedOptions[0]) {
            text = text.replace("-", "")
        }
        if (selectedOptions[1]) {
            text = text.uppercase()
        }
        uuid = text
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MultiChoiceSegmentedButtonRow {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = options.size
                    ),
                    checked = selectedOptions[index],
                    onCheckedChange = {
                        selectedOptions[index] = !selectedOptions[index]
                    },
                    icon = { SegmentedButtonDefaults.Icon(selectedOptions[index]) },
                    label = {
                        Text(label, modifier = Modifier.widthIn(max = 100.dp))
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // UUID显示区域
        OutlinedTextField(
            value = uuid,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("生成的UUID") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 操作按钮区域
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { uuid = UUID.randomUUID().toString() }
            ) {
                Text("生成新UUID")
            }

            Button(
                onClick = {
                    clipboardManager.setText(AnnotatedString(uuid))
                    showCopied = true
                }
            ) {
                Icon(
                    imageVector = if (showCopied) FontAwesomeIcons.Solid.Check
                    else FontAwesomeIcons.Solid.Copy,
                    contentDescription = "复制",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(if (showCopied) "已复制" else "复制")
            }
        }
    }

    // 自动隐藏复制成功提示
    if (showCopied) {
        LaunchedEffect(showCopied) {
            delay(2000)
            showCopied = false
        }
    }
}