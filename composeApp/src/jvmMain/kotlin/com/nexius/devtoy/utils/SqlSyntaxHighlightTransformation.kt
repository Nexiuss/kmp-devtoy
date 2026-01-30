package com.nexius.devtoy.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.util.regex.Pattern
// SQL 各语法元素的高亮样式
object SqlHighlightStyles {
    // 关键字（SELECT/FROM/WHERE/INSERT/UPDATE 等）- 蓝色+加粗
    val KEYWORD = SpanStyle(color = Color(0xFF2962FF), fontWeight = FontWeight.Bold)
    // 内置函数（SUM/COUNT/MAX/CAST 等）- 深紫色+加粗
    val FUNCTION = SpanStyle(color = Color(0xFFAA00FF), fontWeight = FontWeight.Bold)
    // 字符串值（单/双引号包裹）- 绿色
    val STRING = SpanStyle(color = Color(0xFF00C853))
    // 数字（整数/小数）- 橙色
    val NUMBER = SpanStyle(color = Color(0xFFFF9100))
    // 符号（, . ; = () [] * / + - 等）- 深灰色
    val SYMBOL = SpanStyle(color = Color(0xFF424242))
    // 表/列名等普通文本 - 默认黑色
    val NORMAL = SpanStyle(color = Color.Black)
}
/**
 * SQL 语法高亮的 VisualTransformation
 * 支持关键字、函数、字符串、数字、符号的差异化高亮
 */
class SqlSyntaxHighlightTransformation : VisualTransformation {
    // 1. 定义 SQL 关键字（可根据需求扩展）
    private val sqlKeywords = setOf(
        "SELECT", "FROM", "WHERE", "INSERT", "INTO", "UPDATE", "DELETE", "CREATE", "TABLE",
        "ALTER", "DROP", "JOIN", "LEFT", "RIGHT", "INNER", "OUTER", "ON", "AND", "OR",
        "NOT", "NULL", "VALUES", "SET", "GROUP", "BY", "HAVING", "ORDER", "ASC", "DESC",
        "LIMIT", "OFFSET", "AS", "DISTINCT", "UNION", "ALL"
    )

    // 2. 定义 SQL 内置函数（可根据需求扩展）
    private val sqlFunctions = setOf(
        "SUM", "COUNT", "AVG", "MAX", "MIN", "ROUND", "CAST", "CONCAT", "SUBSTRING",
        "LENGTH", "NOW", "DATE", "TIME", "YEAR", "MONTH", "DAY", "UPPER", "LOWER"
    )

    // 3. 正则表达式：匹配不同 SQL 元素（优先级：字符串 > 数字 > 符号 > 单词）
    private val pattern = Pattern.compile(
        """(?:"[^"]*"|'[^']*')|""" + // 双/单引号包裹的字符串
                """\d+\.?\d*|\.?\d+|""" +   // 数字（整数/小数）
                """[,.;=+\-*/()\[\]]|""" +  // 常见符号
                """\w+"""                   // 单词（关键字/函数/表名/列名）
    )

    override fun filter(text: AnnotatedString): TransformedText {
        // 构建带高亮样式的 AnnotatedString
        val highlightedText = AnnotatedString.Builder().apply {
            append(text.text) // 先追加原始文本
            val matcher = pattern.matcher(text.text)

            // 遍历所有匹配的 SQL 元素，为其添加样式标签
            while (matcher.find()) {
                val start = matcher.start()
                val end = matcher.end()
                val token = matcher.group().uppercase() // 统一转大写，避免大小写敏感

                // 根据 token 类型选择对应的样式
                val style = when {
                    // 匹配字符串（单/双引号包裹）
                    token.startsWith("\"") || token.startsWith("'") -> SqlHighlightStyles.STRING
                    // 匹配数字
                    token.all { it.isDigit() || it == '.' } -> SqlHighlightStyles.NUMBER
                    // 匹配符号
                    token.length == 1 && !token[0].isLetterOrDigit() -> SqlHighlightStyles.SYMBOL
                    // 匹配 SQL 关键字
                    sqlKeywords.contains(token) -> SqlHighlightStyles.KEYWORD
                    // 匹配 SQL 函数
                    sqlFunctions.contains(token) -> SqlHighlightStyles.FUNCTION
                    // 普通文本（表名/列名等）
                    else -> SqlHighlightStyles.NORMAL
                }

                // 为当前匹配区间添加样式标签
                addStyle(style, start, end)
            }
        }.toAnnotatedString()

        // 4. OffsetMapping：原始文本和转换后文本的光标位置映射（此处一一对应，无删减/新增字符）
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int = offset
            override fun transformedToOriginal(offset: Int): Int = offset
        }

        // 返回转换后的文本和光标映射规则
        return TransformedText(highlightedText, offsetMapping)
    }
}