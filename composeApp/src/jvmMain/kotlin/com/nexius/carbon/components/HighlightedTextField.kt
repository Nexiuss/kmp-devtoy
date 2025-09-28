package com.nexius.carbon.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun HighlightedTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    suggestions: List<String>,
    onSuggestionClick: (String) -> Unit
) {
    // 定义 SQL 关键字
    val keywords = listOf("SELECT", "FROM", "WHERE", "INSERT", "UPDATE", "DELETE", "CREATE", "DROP", "ALTER", "JOIN")
    val annotatedString = buildAnnotatedString {
        val words = value.text.split(" ")
        for (word in words) {
            if (keywords.contains(word.uppercase())) {
                withStyle(SpanStyle(color = Color.Blue)) { append(word) }
            } else {
                append(word)
            }
            append(" ")
        }
    }
    Column {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle.Default.copy(color = Color.Transparent, fontSize = MaterialTheme.typography.body1.fontSize),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.White),
            decorationBox = { innerTextField ->
                Box(Modifier.padding(8.dp)) {
                    // 1. 显示高亮文本
                    Text(annotatedString)
                    // 2. 让真实输入框不可见，只处理输入焦点和光标
                    innerTextField()
                }
            }
        )
        // 自动补全建议
        if (suggestions.isNotEmpty()) {
            Column(Modifier.background(Color.LightGray).fillMaxWidth()) {
                suggestions.forEach { suggestion ->
                    Text(
                        suggestion,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .clickable { onSuggestionClick(suggestion) }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun App() {
    var textState by remember { mutableStateOf(TextFieldValue()) }
    val keywords = listOf("SELECT", "FROM", "WHERE", "INSERT", "UPDATE", "DELETE", "CREATE", "DROP", "ALTER", "JOIN")
    val lastWord = textState.text.split(" ").lastOrNull() ?: ""
    val suggestions = if (lastWord.length > 1)
        keywords.filter { it.startsWith(lastWord.uppercase()) && it != lastWord.uppercase() }
    else emptyList()

    Surface(modifier = Modifier.fillMaxSize()) {
        HighlightedTextField(
            value = textState,
            onValueChange = { textState = it },
            suggestions = suggestions,
            onSuggestionClick = { suggestion ->
                val newText = textState.text.substringBeforeLast(lastWord) + suggestion + " "
                textState = TextFieldValue(newText)
            }
        )
    }
}