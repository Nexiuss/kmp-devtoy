package com.nexius.devtoy.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarkDownPreview() {
    var markdown by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val richTextState = rememberRichTextState()

    LaunchedEffect(markdown.text) {
        richTextState.setMarkdown(markdown.text)

        println("Html: \n${richTextState.toHtml()}")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        // 输入框区域
        OutlinedTextField(
            value = markdown,
            onValueChange = { markdown = it },
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(end = 8.dp),
            placeholder = { Text("请输入 Markdown 内容...") },
            maxLines = Int.MAX_VALUE,
            singleLine = false,
            textStyle = TextStyle(
                fontSize = 14.sp,
                // 修正行高：使用具体的 TextUnit 值（如 24.sp，约为 14*1.7 倍行高）
                lineHeight = 24.sp
            ),
            shape = MaterialTheme.shapes.extraSmall,
        )

        // 预览区域
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = MaterialTheme.shapes.extraSmall
                )
                .padding(16.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.extraSmall
                ),
            verticalArrangement = Arrangement.Top
        ) {
            RichText(
                state = richTextState,
                modifier = Modifier.fillMaxSize(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    // 预览区行高：根据字体大小设置合适的值（如 28.sp）
                    lineHeight = 28.sp
                )
            )
        }
    }
}
