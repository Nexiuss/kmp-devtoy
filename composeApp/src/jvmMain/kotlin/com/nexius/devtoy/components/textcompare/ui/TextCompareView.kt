package com.nexius.devtoy.components.textcompare.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexius.devtoy.components.textcompare.model.CompareMode
import com.nexius.devtoy.components.textcompare.model.DiffLine
import com.nexius.devtoy.components.textcompare.model.DiffLineType
import com.nexius.devtoy.utils.TextDiffUtil
import kotlinx.coroutines.delay

// ===================== 状态类（对齐原有AppState架构） =====================
class TextCompareState {
    val sourceText = mutableStateOf("")
    val targetText = mutableStateOf("")
    val compareMode = mutableStateOf(CompareMode.SIDE_BY_SIDE)

    val ignoreTrim = mutableStateOf(false)
    val ignoreEmptyLine = mutableStateOf(false)

    val diffResult = mutableStateOf<List<DiffLine>>(emptyList())
    val statMsg = mutableStateOf("")

    // 防抖锁，防止连续多次重复计算diff
    private var isCalculating = false

    fun refreshDiff() {
        if (isCalculating) return
        isCalculating = true

        try {
            if (sourceText.value.isBlank() && targetText.value.isBlank()) {
                diffResult.value = emptyList()
                statMsg.value = ""
                return
            }
            val diff = TextDiffUtil.computeDiff(
                source = sourceText.value,
                target = targetText.value,
                ignoreTrim = ignoreTrim.value,
                ignoreEmptyLine = ignoreEmptyLine.value
            )
            diffResult.value = diff
            statMsg.value = TextDiffUtil.calcStat(diff)
        } finally {
            isCalculating = false
        }
    }

    fun swapText() {
        val tmp = sourceText.value
        sourceText.value = targetText.value
        targetText.value = tmp
        refreshDiff()
    }

    fun clearAll() {
        sourceText.value = ""
        targetText.value = ""
        diffResult.value = emptyList()
        statMsg.value = ""
    }
}

// ===================== 顶层入口Composable =====================
@Composable
fun TextCompareView() {
    val state = remember { TextCompareState() }
    val scrollState = rememberScrollState()

    val primaryColor = Color(0xFF4A6FFF)
    val surfaceColor = Color(0xFFFAFAFA)
    val cardColor = Color.White

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
            // 卡片1：输入配置区
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = 2.dp,
                backgroundColor = cardColor
            ) {
                Column(Modifier.padding(20.dp)) {
                    InputConfigArea(state)
                }
            }

            // 卡片2：比对结果展示区
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = 2.dp,
                backgroundColor = cardColor
            ) {
                Column(Modifier.padding(20.dp)) {
                    DiffResultDisplayArea(state)
                }
            }

            // 卡片3：统计信息
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = 2.dp,
                backgroundColor = cardColor
            ) {
                Column(Modifier.padding(20.dp)) {
                    StatInfoArea(state.statMsg.value)
                }
            }
        }
    }
}

// ===================== 输入配置区域 =====================
@Composable
fun InputConfigArea(state: TextCompareState) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            "文本输入与比对配置",
            style = MaterialTheme.typography.h6.copy(fontSize = 18.sp)
        )

        // 双文本输入框（修复滚动嵌套异常）
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Column(Modifier.weight(1f)) {
                Text("原始文本", fontSize = 14.sp)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(BorderStroke(1.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.3f)), RoundedCornerShape(12.dp))
                        .padding(4.dp)
                ) {
                    val innerScroll = rememberScrollState()
                    BasicTextField(
                        value = state.sourceText.value,
                        onValueChange = {
                            state.sourceText.value = it
                            state.refreshDiff()
                        },
                        textStyle = TextStyle(fontSize = 14.sp),
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(innerScroll)
                            .padding(8.dp),
                        decorationBox = { innerTextField -> innerTextField() }
                    )
                }
            }
            Column(Modifier.weight(1f)) {
                Text("对比文本", fontSize = 14.sp)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(BorderStroke(1.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.3f)), RoundedCornerShape(12.dp))
                        .padding(4.dp)
                ) {
                    val innerScroll = rememberScrollState()
                    BasicTextField(
                        value = state.targetText.value,
                        onValueChange = {
                            state.targetText.value = it
                            state.refreshDiff()
                        },
                        textStyle = TextStyle(fontSize = 14.sp),
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(innerScroll)
                            .padding(8.dp),
                        decorationBox = { innerTextField -> innerTextField() }
                    )
                }
            }
        }

        // 模式切换行
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("展示模式：", fontSize = 14.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = state.compareMode.value == CompareMode.SIDE_BY_SIDE,
                    onClick = {
                        state.compareMode.value = CompareMode.SIDE_BY_SIDE
                        state.refreshDiff()
                    }
                )
                Text("非行内(左右分栏)")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = state.compareMode.value == CompareMode.INLINE,
                    onClick = {
                        state.compareMode.value = CompareMode.INLINE
                        state.refreshDiff()
                    }
                )
                Text("行内合并高亮")
            }
        }

        // 忽略选项
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = state.ignoreTrim.value,
                    onCheckedChange = {
                        state.ignoreTrim.value = it
                        state.refreshDiff()
                    }
                )
                Text("忽略首尾空格")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = state.ignoreEmptyLine.value,
                    onCheckedChange = {
                        state.ignoreEmptyLine.value = it
                        state.refreshDiff()
                    }
                )
                Text("忽略空行")
            }
        }

        // 操作按钮
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = state::clearAll,
                modifier = Modifier.height(44.dp).clip(RoundedCornerShape(12.dp))
            ) {
                Text("清空全部", fontSize = 14.sp)
            }
            Button(
                onClick = state::swapText,
                enabled = state.sourceText.value.isNotBlank() || state.targetText.value.isNotBlank(),
                modifier = Modifier.height(44.dp).clip(RoundedCornerShape(12.dp))
            ) {
                Text("交换两段文本", fontSize = 14.sp)
            }
        }
    }
}

// ===================== 结果总分发器 =====================
@Composable
fun DiffResultDisplayArea(state: TextCompareState) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            "比对结果预览",
            style = MaterialTheme.typography.h6.copy(fontSize = 18.sp)
        )

        if (state.diffResult.value.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF9FAFC))
                    .border(1.dp, Color(0xFFEAEAEA), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("请粘贴两段待比对文本", color = Color.Gray)
            }
            return
        }

        when (state.compareMode.value) {
            CompareMode.SIDE_BY_SIDE -> SideBySideDiffView(state.diffResult.value)
            CompareMode.INLINE -> InlineDiffView(state.diffResult.value)
        }
    }
}

// ===================== 模式1：左右分栏（非行内） =====================
@Composable
fun SideBySideDiffView(diffList: List<DiffLine>) {
    val srcScrollState = rememberLazyListState()
    val tgtScrollState = rememberLazyListState()

    // 单向滚动同步，无死循环
    LaunchedEffect(srcScrollState.firstVisibleItemIndex, srcScrollState.firstVisibleItemScrollOffset) {
        tgtScrollState.scrollToItem(srcScrollState.firstVisibleItemIndex, srcScrollState.firstVisibleItemScrollOffset)
    }
    LaunchedEffect(tgtScrollState.firstVisibleItemIndex, tgtScrollState.firstVisibleItemScrollOffset) {
        srcScrollState.scrollToItem(tgtScrollState.firstVisibleItemIndex, tgtScrollState.firstVisibleItemScrollOffset)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF9FAFC))
            .border(1.dp, Color(0xFFEAEAEA), RoundedCornerShape(12.dp))
    ) {
        // 左：原始文本（只渲染DELETE/SAME片段）
        LazyColumn(
            state = srcScrollState,
            modifier = Modifier.weight(1f).fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            items(diffList, key = { it.lineNo }) { line ->
                val annot = buildAnnotatedString {
                    line.charDiffParts.forEach { part ->
                        if (part.type == DiffLineType.DELETE || part.type == DiffLineType.SAME) {
                            withStyle(SpanStyle(background = getDiffBgColor(part.type), color = getDiffTextColor(part.type))) {
                                append(part.text)
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .background(if (line.lineType == DiffLineType.DELETE) getDiffBgColor(line.lineType).copy(alpha = 0.1f) else Color.Transparent),
                    verticalAlignment = Alignment.Top
                ) {
                    Text("${line.lineNo}", Modifier.width(40.dp), color = Color.Gray, fontSize = 12.sp)
                    Text(annot, fontSize = 14.sp)
                }
            }
        }

        Divider(Modifier.fillMaxHeight().width(1.dp))

        // 右：对比文本（只渲染ADD/SAME片段）
        LazyColumn(
            state = tgtScrollState,
            modifier = Modifier.weight(1f).fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            items(diffList, key = { it.lineNo }) { line ->
                val annot = buildAnnotatedString {
                    line.charDiffParts.forEach { part ->
                        if (part.type == DiffLineType.ADD || part.type == DiffLineType.SAME) {
                            withStyle(SpanStyle(background = getDiffBgColor(part.type), color = getDiffTextColor(part.type))) {
                                append(part.text)
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .background(if (line.lineType == DiffLineType.ADD) getDiffBgColor(line.lineType).copy(alpha = 0.1f) else Color.Transparent),
                    verticalAlignment = Alignment.Top
                ) {
                    Text("${line.lineNo}", Modifier.width(40.dp), color = Color.Gray, fontSize = 12.sp)
                    Text(annot, fontSize = 14.sp)
                }
            }
        }
    }
}

// ===================== 模式2：行内合并高亮 =====================
@Composable
fun InlineDiffView(diffList: List<DiffLine>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF9FAFC))
            .border(1.dp, Color(0xFFEAEAEA), RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(diffList, key = { it.lineNo }) { line ->
            val annot = buildAnnotatedString {
                line.charDiffParts.forEach { part ->
                    withStyle(SpanStyle(background = getDiffBgColor(part.type), color = getDiffTextColor(part.type))) {
                        append(part.text)
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(getDiffBgColor(line.lineType).copy(alpha = 0.15f))
                    .padding(vertical = 6.dp, horizontal = 8.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text("${line.lineNo}: ", fontSize = 12.sp, color = Color.Gray)
                Text(annot, fontSize = 14.sp)
            }
        }
    }
}

// ===================== 统计信息展示 =====================
@Composable
fun StatInfoArea(msg: String) {
    Column {
        Text(
            "比对统计",
            style = MaterialTheme.typography.h6.copy(fontSize = 18.sp),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        if (msg.isNotBlank()) {
            Text(msg, color = Color(0xFF2E7D32), fontSize = 14.sp)
        } else {
            Text("暂无统计数据", color = Color.Gray, fontSize = 14.sp)
        }
    }
}

// ===================== 颜色工具 =====================
fun getDiffBgColor(type: DiffLineType): Color {
    return when (type) {
        DiffLineType.DELETE -> Color(0xFFFFEAEA)
        DiffLineType.ADD -> Color(0xFFE7F5E9)
        DiffLineType.MODIFY -> Color(0xFFFFF8E1)
        DiffLineType.SAME -> Color.Transparent
    }
}

fun getDiffTextColor(type: DiffLineType): Color {
    return when (type) {
        DiffLineType.DELETE -> Color(0xFFC62828)
        DiffLineType.ADD -> Color(0xFF2E7D32)
        DiffLineType.MODIFY -> Color(0xFFEF6C00)
        DiffLineType.SAME -> Color(0xFF333333)
    }
}