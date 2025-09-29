package com.nexius.sqlclient

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import kotlinx.coroutines.launch
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.IntOffset

/**
 * 数据库元数据模型
 * 存储整个数据库的结构信息（包含所有表）
 */
data class DatabaseMetadata(
    /** 数据库名称（可选，用于多数据库场景标识） */
    val databaseName: String? = null,
    /** 数据库版本（可选，用于版本管理） */
    val databaseVersion: Int? = null,
    /** 数据库包含的所有表元数据 */
    val tables: List<TableMetadata> = emptyList()
)

/**
 * 数据表元数据模型
 * 存储单个表的结构信息（表属性 + 列信息）
 */
data class TableMetadata(
    /** 表名（核心字段，唯一标识表） */
    val name: String,
    /** 表注释（可选，描述表的用途） */
    val comment: String? = null,
    /** 表的主键列名（可选，用于快速定位主键） */
    val primaryKey: String? = null,
    /** 表包含的所有列元数据 */
    val columns: List<ColumnMetadata> = emptyList()
)

/**
 * 数据表列元数据模型
 * 存储单个列的详细属性（用于SQL提示和语法校验）
 */
data class ColumnMetadata(
    /** 列名（核心字段，唯一标识列） */
    val name: String,
    /** 列的数据类型（如 INT、VARCHAR、TIMESTAMP 等，用于提示兼容性） */
    val dataType: String,
    /** 列注释（可选，描述列的含义） */
    val comment: String? = null,
    /** 是否允许为空（可选，默认允许） */
    val isNullable: Boolean = true,
    /** 默认值（可选，列的默认填充值） */
    val defaultValue: String? = null,
    /** 是否为主键（可选，默认不是） */
    val isPrimaryKey: Boolean = false
)

// SQL关键字（用于语法高亮和提示）
private val SQL_KEYWORDS = setOf(
    "SELECT", "FROM", "WHERE", "AND", "OR", "NOT", "INSERT", "UPDATE", "DELETE",
    "CREATE", "ALTER", "DROP", "TABLE", "DATABASE", "INDEX", "PRIMARY", "KEY",
    "FOREIGN", "REFERENCES", "IN", "LIKE", "BETWEEN", "NULL", "IS", "JOIN",
    "INNER", "LEFT", "RIGHT", "FULL", "ON", "AS", "GROUP", "BY", "HAVING",
    "ORDER", "ASC", "DESC", "LIMIT", "OFFSET", "VALUES", "SET", "DISTINCT",
    "ALL", "ANY", "UNION", "EXCEPT", "INTERSECT", "CASE", "WHEN", "THEN", "ELSE", "END"
)

// SQL函数（用于语法高亮和提示）
private val SQL_FUNCTIONS = setOf(
    "COUNT", "SUM", "AVG", "MIN", "MAX", "DISTINCT", "UPPER", "LOWER",
    "SUBSTRING", "CONCAT", "CAST", "DATE", "NOW", "CURRENT_DATE", "CURRENT_TIME",
    "LENGTH", "TRIM", "ROUND", "FLOOR", "CEIL", "COALESCE", "NULLIF"
)

/**
 * SQL语法高亮转换器
 * 将输入的SQL文本按关键字、函数、字符串等规则进行颜色标记
 */
private val sqlVisualTransformation = VisualTransformation { text ->
    val annotatedString = buildAnnotatedString {
        // 按非单词字符分割（保留符号，如逗号、括号等）
        val tokens = text.text.split(Regex("(?<=\\W)|(?=\\W)"))

        tokens.forEach { token ->
            when {
                // 1. 高亮SQL关键字（深蓝色加粗）
                token.uppercase() in SQL_KEYWORDS -> {
                    withStyle(SpanStyle(
                        color = Color(0xFF0000CC),
                        fontWeight = FontWeight.Bold
                    )) {
                        append(token)
                    }
                }
                // 2. 高亮SQL函数（紫色加粗）
                token.uppercase() in SQL_FUNCTIONS -> {
                    withStyle(SpanStyle(
                        color = Color(0xFF7F007F),
                        fontWeight = FontWeight.Bold
                    )) {
                        append(token)
                    }
                }
                // 3. 高亮字符串（绿色，包含单/双引号包裹的内容）
                token.matches(Regex("(['\"]).*\\1")) -> {  // 结尾引号（如 abc'）
                    withStyle(SpanStyle(color = Color(0xFF008000))) {
                        append(token)
                    }
                }
                // 4. 高亮数字（青色，包含整数、小数）
                token.matches(Regex("\\d+")) ||  // 整数（如 123）
                        token.matches(Regex("\\d+\\.\\d+")) ||  // 小数（如 3.14）
                        token.matches(Regex("\\.\\d+")) -> {  // 以小数点开头的小数（如 .5）
                    withStyle(SpanStyle(color = Color(0xFF008080))) {
                        append(token)
                    }
                }
                // 5. 高亮注释（灰色，-- 开头的内容）
                token.startsWith("--") -> {
                    withStyle(SpanStyle(
                        color = Color(0xFF808080),
                        fontWeight = FontWeight.Light
                    )) {
                        append(token)
                    }
                }
                // 6. 默认文本（黑色）
                else -> append(token)
            }
        }
    }

    // 偏移映射：保持原始文本与高亮文本的光标位置一致
    val offsetMapping = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int = offset
        override fun transformedToOriginal(offset: Int): Int = offset
    }

    TransformedText(annotatedString, offsetMapping)
}

/**
 * 增强型SQL编辑器
 * 支持语法高亮、自动提示（关键字/函数/表/列）、键盘导航
 *
 * @param sqlQuery 当前输入的SQL文本
 * @param onSqlChanged SQL文本变化回调
 * @param databaseMetadata 数据库元数据（用于表/列提示）
 * @param modifier 布局修饰符
 * @param textStyle 文本样式（默认等宽字体，适合代码编辑）
 * @param placeholder 空输入时的提示文本
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EnhancedSqlEditor(
    sqlQuery: TextFieldValue,
    onSqlChanged: (TextFieldValue) -> Unit,
    databaseMetadata: DatabaseMetadata,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current.copy(
        fontSize = 14.sp,
        fontFamily = FontFamily.Monospace, // 等宽字体，代码编辑更友好
        lineHeight = 18.sp // 调整行高，提升可读性
    ),
    placeholder: String = "Enter SQL query here... (e.g. SELECT * FROM users)"
) {
    // 自动提示相关状态
    var showSuggestions by remember { mutableStateOf(false) }
    var suggestions by remember { mutableStateOf(emptyList<String>()) }
    var cursorPosition by remember { mutableStateOf(sqlQuery.selection.start) }
    var selectedSuggestionIndex by remember { mutableStateOf(-1) }
    var suggestionPopupOffsetY by remember { mutableStateOf(0.dp) }
    var suggestionPopupOffsetX by remember { mutableStateOf(0.dp) }

    // 组件依赖状态
    val focusRequester = remember { FocusRequester() }
    val coroutineScope = rememberCoroutineScope()
    val scrollState = remember { ScrollState(0) } // 编辑器滚动状态
    val suggestionScrollState: LazyListState = remember { LazyListState(0) } // 提示框滚动状态

    // 提取光标所在的当前单词（用于生成提示）
    val currentWord = remember(sqlQuery.text, cursorPosition) {
        extractCurrentWord(sqlQuery.text, cursorPosition)
    }

    // 1. 监听SQL文本变化，动态生成提示
    LaunchedEffect(sqlQuery, databaseMetadata) {
        cursorPosition = sqlQuery.selection.start
        // 输入长度 >=2 时才生成提示（避免频繁触发）
        if (currentWord.length >= 2) {
            suggestions = generateSuggestions(currentWord, databaseMetadata)
            showSuggestions = suggestions.isNotEmpty()
            selectedSuggestionIndex = if (suggestions.isNotEmpty()) 0 else -1
        } else {
            showSuggestions = false
            selectedSuggestionIndex = -1
        }
    }

    // 2. 处理提示框的键盘导航（上下键选择，Enter/Tab确认，ESC关闭）
    fun handleSuggestionNavigation(key: Key) {
        if (suggestions.isEmpty()) return

        when (key) {
            Key.DirectionDown -> {
                selectedSuggestionIndex = (selectedSuggestionIndex + 1) % suggestions.size
                // 滚动到选中项（每次滚动一个项的高度，约30dp）
                coroutineScope.launch {
                    suggestionScrollState.scrollBy(30f)
                }
            }
            Key.DirectionUp -> {
                selectedSuggestionIndex = (selectedSuggestionIndex - 1 + suggestions.size) % suggestions.size
                coroutineScope.launch {
                    suggestionScrollState.scrollBy(-30f)
                }
            }
            Key.Enter, Key.Tab -> {
                // 确认选择的提示，替换当前单词
                val selectedSuggestion = suggestions[selectedSuggestionIndex]
                replaceCurrentWordWithSuggestion(
                    sqlQuery = sqlQuery,
                    currentWord = currentWord,
                    suggestion = selectedSuggestion,
                    cursorPosition = cursorPosition,
                    onSqlChanged = onSqlChanged
                )
                showSuggestions = false
            }
            Key.Escape -> {
                // 关闭提示框
                showSuggestions = false
            }
        }
    }

    Column(modifier = modifier) {
        // 核心：SQL输入框（带语法高亮）
        BasicTextField(
            value = sqlQuery,
            onValueChange = onSqlChanged,
            textStyle = textStyle.copy(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // 占满剩余高度，适配不同屏幕
                .focusRequester(focusRequester)
                .focusable()
                .verticalScroll(scrollState)
                .onKeyEvent { event ->
                    // 仅在提示框显示时处理导航按键
                    if (showSuggestions && event.type == KeyEventType.KeyDown) {
                        handleSuggestionNavigation(event.key)
                        return@onKeyEvent true // 消费按键事件，避免传递给其他组件
                    }
                    false
                },
            visualTransformation = sqlVisualTransformation,
            decorationBox = { innerTextField ->
                // 输入框容器（白色背景 + 内边距）
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(12.dp) // 增加内边距，提升输入体验
                ) {
                    // 空输入时显示提示文本
                    if (sqlQuery.text.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = textStyle.copy(color = Color.LightGray),
                            modifier = Modifier.align(Alignment.TopStart)
                        )
                    }
                    // 实际的文本输入区域
                    innerTextField()
                }
            },
            onTextLayout = { layoutResult ->
                // 计算提示框的位置（基于光标所在行的底部）
                if (cursorPosition in 0..sqlQuery.text.length) {
                    val lineNumber = layoutResult.getLineForOffset(cursorPosition)
                    val lineBottom = layoutResult.getLineBottom(lineNumber).dp // 行底部Y坐标
                    val cursorRect = layoutResult.getCursorRect(cursorPosition)
                    val cursorOffsetX = cursorRect.left.dp // 光标X坐标

                    // 更新提示框偏移量（确保提示框在光标下方）
                    suggestionPopupOffsetY = lineBottom + 4.dp // 增加4dp间距，避免重叠
                    suggestionPopupOffsetX = cursorOffsetX
                }
            },
            maxLines = Int.MAX_VALUE, // 支持无限换行
            singleLine = false // 关闭单行模式
        )

        // 3. 自动提示弹窗（仅在满足条件时显示）
        if (showSuggestions && cursorPosition > 0 && suggestions.isNotEmpty()) {
            Popup(
                alignment = Alignment.TopStart,
                offset = IntOffset(
                    x = suggestionPopupOffsetX.value.toInt(),
                    y = suggestionPopupOffsetY.value.toInt()
                ),
                properties = PopupProperties(
                    focusable = false, // 不抢占焦点，确保输入框仍能接收按键
                    dismissOnBackPress = true, // 按返回键关闭
                    dismissOnClickOutside = true // 点击外部关闭
                )
            ) {
                Card(
                    elevation = 6.dp, // 增加阴影，提升视觉层次
                    modifier = Modifier
                        .width(300.dp) // 固定宽度，避免过宽/过窄
                        .heightIn(max = 200.dp) // 最大高度200dp，超出滚动
                ) {
                    LazyColumn(
                        state = suggestionScrollState,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(suggestions) { suggestion ->
                            val isSelected = suggestions.indexOf(suggestion) == selectedSuggestionIndex
                            Text(
                                text = highlightSuggestionMatch(suggestion, currentWord),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(if (isSelected) Color(0xFFF0F0F0) else Color.Transparent)
                                    .padding(10.dp) // 增加内边距，提升点击区域
                                    .pointerHoverIcon(PointerIcon.Hand) // 鼠标悬浮时显示手型
                                    .clickable {
                                        // 点击提示项，替换当前单词
                                        replaceCurrentWordWithSuggestion(
                                            sqlQuery = sqlQuery,
                                            currentWord = currentWord,
                                            suggestion = suggestion,
                                            cursorPosition = cursorPosition,
                                            onSqlChanged = onSqlChanged
                                        )
                                        showSuggestions = false
                                        focusRequester.requestFocus() // 重新聚焦输入框
                                    },
                                style = textStyle.copy(
                                    color = if (isSelected) MaterialTheme.colors.primary else Color.DarkGray,
                                    fontSize = 13.sp // 提示文本稍小，避免拥挤
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    // 4. 初始聚焦：组件加载后自动聚焦到输入框
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}


/**
 * 高亮提示项中与当前输入匹配的部分
 * @param suggestion 完整的提示文本
 * @param currentWord 当前输入的单词（用于匹配）
 * @return 带高亮标记的AnnotatedString
 */
private fun highlightSuggestionMatch(suggestion: String, currentWord: String): AnnotatedString {
    val lowerSuggestion = suggestion.lowercase()
    val lowerWord = currentWord.lowercase()
    val matchIndex = lowerSuggestion.indexOf(lowerWord)

    return buildAnnotatedString {
        if (matchIndex != -1) {
            // 1. 追加匹配前的文本（无高亮）
            append(suggestion.substring(0, matchIndex))
            // 2. 追加匹配的文本（加粗高亮）
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                append(suggestion.substring(matchIndex, matchIndex + lowerWord.length))
            }
            // 3. 追加匹配后的文本（无高亮）
            append(suggestion.substring(matchIndex + lowerWord.length))
        } else {
            // 无匹配时直接返回原文本
            append(suggestion)
        }
    }
}

/**
 * 用选中的提示替换当前单词，并更新光标位置
 * @param sqlQuery 当前SQL文本状态
 * @param currentWord 光标所在的当前单词
 * @param suggestion 选中的提示文本
 * @param cursorPosition 当前光标位置
 * @param onSqlChanged 文本更新回调
 */
private fun replaceCurrentWordWithSuggestion(
    sqlQuery: TextFieldValue,
    currentWord: String,
    suggestion: String,
    cursorPosition: Int,
    onSqlChanged: (TextFieldValue) -> Unit
) {
    if (currentWord.isEmpty() || suggestion.isEmpty()) return

    // 计算当前单词的起始位置
    val wordStart = cursorPosition - currentWord.length
    // 拼接新文本（替换当前单词）
    val newText = sqlQuery.text.substring(0, wordStart) + suggestion + sqlQuery.text.substring(cursorPosition)
    // 新光标位置（在提示文本末尾）
    val newCursorPosition = wordStart + suggestion.length

    // 通知外部更新SQL文本
    onSqlChanged(
        TextFieldValue(
            text = newText,
            selection = TextRange(newCursorPosition) // 光标定位到提示末尾
        )
    )
}

/**
 * 从文本中提取光标所在的当前单词
 * 单词分隔符：空格、逗号、分号、括号、等号等符号
 * @param text 完整文本
 * @param cursorPosition 光标位置
 * @return 光标所在的单词（空字符串表示无有效单词）
 */
private fun extractCurrentWord(text: String, cursorPosition: Int): String {
    if (cursorPosition <= 0 || cursorPosition > text.length) return ""

    // 定义单词分隔符（非字母数字的字符视为分隔符）
    val separatorRegex = Regex("[^a-zA-Z0-9_]") // 包含下划线（SQL标识符允许下划线）

    // 1. 从光标位置向左查找单词起始点
    val wordStart = text.substring(0, cursorPosition)
        .indexOfLast { separatorRegex.matches(it.toString()) } + 1

    // 2. 从光标位置向右查找单词结束点（可选，此处仅提取到光标位置的前缀）
    // （注：若需支持"部分输入"提示，可扩展为提取完整单词前缀）

    return text.substring(wordStart, cursorPosition).trim()
}

/**
 * 预览函数：快速查看EnhancedSqlEditor的效果
 */
@Composable
fun PreviewEnhancedSqlEditor() {
    MaterialTheme {
        // 1. 构造示例数据库元数据（模拟users和orders表）
        val sampleMetadata = DatabaseMetadata(
            databaseName = "ecommerce_db",
            databaseVersion = 1,
            tables = listOf(
                // users表（用户表）
                TableMetadata(
                    name = "users",
                    comment = "存储系统用户信息",
                    primaryKey = "id",
                    columns = listOf(
                        ColumnMetadata(
                            name = "id",
                            dataType = "INT",
                            comment = "用户ID（主键）",
                            isNullable = false,
                            isPrimaryKey = true
                        ),
                        ColumnMetadata(
                            name = "name",
                            dataType = "VARCHAR(50)",
                            comment = "用户姓名",
                            isNullable = false
                        ),
                        ColumnMetadata(
                            name = "email",
                            dataType = "VARCHAR(100)",
                            comment = "用户邮箱（唯一）",
                            isNullable = false
                        ),
                        ColumnMetadata(
                            name = "created_at",
                            dataType = "TIMESTAMP",
                            comment = "创建时间",
                            isNullable = false,
                            defaultValue = "CURRENT_TIMESTAMP"
                        )
                    )
                ),
                // orders表（订单表）
                TableMetadata(
                    name = "orders",
                    comment = "存储用户订单信息",
                    primaryKey = "id",
                    columns = listOf(
                        ColumnMetadata(
                            name = "id",
                            dataType = "INT",
                            comment = "订单ID（主键）",
                            isNullable = false,
                            isPrimaryKey = true
                        ),
                        ColumnMetadata(
                            name = "user_id",
                            dataType = "INT",
                            comment = "关联用户ID（外键）",
                            isNullable = false
                        ),
                        ColumnMetadata(
                            name = "amount",
                            dataType = "DECIMAL(10,2)",
                            comment = "订单金额",
                            isNullable = false
                        ),
                        ColumnMetadata(
                            name = "order_date",
                            dataType = "DATE",
                            comment = "下单日期",
                            isNullable = false
                        ),
                        ColumnMetadata(
                            name = "status",
                            dataType = "VARCHAR(20)",
                            comment = "订单状态（PENDING/PAID/SHIPPED）",
                            isNullable = false,
                            defaultValue = "PENDING"
                        )
                    )
                )
            )
        )

        // 2. 模拟SQL输入状态
        var currentSql by remember {
            mutableStateOf(TextFieldValue("SELECT us FROM users WHERE "))
        }

        // 3. 渲染SQL编辑器
        EnhancedSqlEditor(
            sqlQuery = currentSql,
            onSqlChanged = { currentSql = it }, // 实时更新SQL文本
            databaseMetadata = sampleMetadata,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color(0xFFF8F8F8)) // 浅灰色背景，区分编辑器区域
        )
    }
}