package com.nexius.devtoy.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * 通用双向文本转换组件
 * @param forwardConvert 正向转换（输入 → 输出）
 * @param backwardConvert 反向转换（输出 → 输入）
 * @param inputLabel 输入框提示（可选）
 * @param outputLabel 输出框提示（可选）
 */
@Composable
fun TextConvertComponent(
    forwardConvert: (String) -> String,
    backwardConvert: (String) -> String,
    inputLabel: String = "输入内容",
    outputLabel: String = "转换结果"
) {
    // 输入/输出状态
    var inputText by remember { mutableStateOf("") }
    var outputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // 占满剩余垂直空间，与右侧输出框等高
                .padding(1.dp),
            label = { Text(inputLabel) },
            singleLine = true
        )
        // 转换按钮行
        Row(
            Modifier.align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 正向转换按钮
            IconButton(onClick = {
                outputText = try {
                    forwardConvert(inputText)
                } catch (e: Exception) {
                    "转换失败：${e.message ?: "未知错误"}"
                }
            }) {
                Icon(Icons.Default.ArrowDownward, contentDescription = "正向转换")
            }

            // 反向转换按钮
            IconButton(onClick = {
                inputText = try {
                    backwardConvert(outputText)
                } catch (e: Exception) {
                    "转换失败：${e.message ?: "未知错误"}"
                }
            }) {
                Icon(Icons.Default.ArrowUpward, contentDescription = "反向转换")
            }
        }
        OutlinedTextField(
            value = outputText,
            onValueChange = { outputText = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // 占满剩余垂直空间，与右侧输出框等高
                .padding(1.dp),
            label = { Text(outputLabel) },
            singleLine = true
        )
    }
}