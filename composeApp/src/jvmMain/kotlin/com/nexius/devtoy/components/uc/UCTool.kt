package com.nexius.devtoy.components.uc

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.delay
import androidx.compose.material3.Text as Material3Text
import kotlinx.coroutines.launch
import kotlin.math.abs

// 定义数据模型，对应图片中的字段配置
data class FieldConfig(
    val inputOutput: String = "0",
    val sqlField: String = "",
    val fieldDesc: String = "",
    val programAttr: String = "",
    val length: String = "",
    val decimalPlaces: String = "",
    val dbType: String = "", // S-字符串 D-日期 F-浮点数
    val nonNull: String = "0",
    val fieldCategory: String = "2",
    val condition: String = "", // >= <= = in lrlike 等
    val serialNo: String = "",
    val inputShowLevel: String = "0",
    val inputType: String = "", // S-字符串 N-字典 F-浮点数 D-日期
    val dictName: String = "",
    val auxiliaryQuery: String = "",
    val viewInitCondition: String = "",
    val viewFieldInit: String = ""
)

// 列配置：定义表头、默认宽度、可拖拽调整
data class ColumnConfig(
    val key: String,
    val title: String,
    var width: Dp,
    val minWidth: Dp = 50.dp,
    val maxWidth: Dp = 300.dp
)

// 初始化示例数据
val initialFieldConfigs = listOf(
    FieldConfig(
        sqlField = "t1.postdate",
        fieldDesc = "开始日期",
        programAttr = "postdatestart",
        length = "30",
        decimalPlaces = "0",
        dbType = "D",
        condition = ">=",
        serialNo = "0",
        inputType = "D"
    ),
    FieldConfig(
        sqlField = "t1.postdate",
        fieldDesc = "结束日期",
        programAttr = "postdateend",
        length = "30",
        decimalPlaces = "0",
        dbType = "D",
        condition = "<=",
        serialNo = "0",
        inputType = "D"
    ),
    FieldConfig(
        sqlField = "t1.abstract",
        fieldDesc = "对账码",
        programAttr = "abstract",
        length = "128",
        decimalPlaces = "0",
        dbType = "S",
        condition = "=",
        serialNo = "1",
        inputShowLevel = "1",
        inputType = "S"
    ),
    FieldConfig(
        sqlField = "t1.L_BANKSOURCE",
        fieldDesc = "数据来源",
        programAttr = "banksource",
        length = "0",
        decimalPlaces = "0",
        dbType = "S",
        condition = "=",
        serialNo = "100",
        inputType = "N",
        auxiliaryQuery = "数据来源"
    )
)

// 下拉选项配置
val dbTypeOptions = listOf("S", "D", "F")
val conditionOptions = listOf(">=", "<=", "=", "in", "lrlike")
val inputTypeOptions = listOf("S", "N", "F", "D")

@Composable
fun EditableFieldConfigList() {
    // 可编辑的数据源
    val fieldList = remember { mutableStateListOf<FieldConfig>() }
    // 选中状态集合（存储选中的索引）
    val selectedIndices = remember { mutableStateSetOf<Int>() }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var showSaveSuccess by remember { mutableStateOf(false) }

    // 表格列配置（支持拖拽调整宽度+移动顺序）
    val columnConfigs = remember {
        mutableStateListOf(
            ColumnConfig("select", "选择", 50.dp),
            ColumnConfig("sqlField", "SQL字段", 140.dp),
            ColumnConfig("fieldDesc", "字段描述", 100.dp),
            ColumnConfig("programAttr", "程序属性", 120.dp),
            ColumnConfig("length", "长度", 60.dp),
            ColumnConfig("dbType", "数据库类型", 100.dp),
            ColumnConfig("condition", "条件", 100.dp),
            ColumnConfig("inputType", "输入类型", 100.dp),
            ColumnConfig("auxiliaryQuery", "辅助查询", 120.dp)
        )
    }

    LaunchedEffect(Unit) {
        fieldList.addAll(initialFieldConfigs)
    }

    // 判断是否全选
    val isAllSelected = fieldList.isNotEmpty() && selectedIndices.size == fieldList.size

    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        // 标题 + 操作按钮行
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Material3Text(
                "字段配置可编辑表格（支持调整宽度+移动列）",
                style = MaterialTheme.typography.h6,
            )

            // 操作按钮组
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // 全选复选框
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isAllSelected,
                        onCheckedChange = { checked ->
                            if (checked) {
                                selectedIndices.addAll(fieldList.indices)
                            } else {
                                selectedIndices.clear()
                            }
                        },
                        colors = CheckboxDefaults.colors(checkedColor = Color(0xFF2196F3))
                    )
                    Material3Text("全选")
                }

                // 新增按钮
                Button(
                    onClick = {
                        fieldList.add(FieldConfig())
                        scope.launch { listState.scrollToItem(fieldList.lastIndex) }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
                ) {
                    Text("新增行", color = Color.White)
                }

                // 批量删除按钮
                Button(
                    onClick = {
                        if (selectedIndices.isNotEmpty()) {
                            val sortedToRemove = selectedIndices.sortedDescending()
                            sortedToRemove.forEach { index ->
                                if (index < fieldList.size) {
                                    fieldList.removeAt(index)
                                }
                            }
                            selectedIndices.clear()
                        }
                    },
                    enabled = selectedIndices.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF44336))
                ) {
                    Text("批量删除", color = Color.White)
                }

                // 保存按钮
                Button(
                    onClick = {
                        saveFieldConfigs(fieldList)
                        showSaveSuccess = true
                        scope.launch {
                            delay(2000)
                            showSaveSuccess = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2196F3))
                ) {
                    Text("保存数据", color = Color.White)
                }
            }
        }

        // 保存成功提示
        if (showSaveSuccess) {
            Text(
                "✅ 数据保存成功！",
                color = Color.Green,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        // 可拖拽调整宽度+移动的表头
        DraggableHeader(
            columnConfigs = columnConfigs,
            onColumnsReordered = { newColumns ->
                columnConfigs.clear()
                columnConfigs.addAll(newColumns)
            }
        )

        // 可滚动表格内容
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxWidth().weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(fieldList.size) { item ->
                EditableConfigRow(
                    config = fieldList[item],
                    columnConfigs = columnConfigs,
                    isSelected = selectedIndices.contains(item),
                    onSelectionChange = { checked ->
                        if (checked) selectedIndices.add(item)
                        else selectedIndices.remove(item)
                    },
                    onConfigUpdated = { updated ->
                        fieldList[item] = updated
                    }
                )
            }
        }
    }
}

/**
 * 可拖拽表头：支持调整宽度 + 移动列顺序
 */
@Composable
fun DraggableHeader(
    columnConfigs: List<ColumnConfig>,
    onColumnsReordered: (List<ColumnConfig>) -> Unit
) {
    var dragOffset by remember { mutableStateOf(Offset.Zero) }
    var draggedColumnIndex by remember { mutableStateOf(-1) }
    var hoverResizeIndex by remember { mutableStateOf(-1) }

    Row(
        modifier = Modifier.fillMaxWidth()
            .background(Color.LightGray)
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        columnConfigs.forEachIndexed { index, column ->
            // 列标题（支持拖拽移动）
            Box(
                modifier = Modifier
                    .width(column.width)
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = {
                                draggedColumnIndex = index
                                dragOffset = Offset.Zero
                            },
                            onDrag = { change, dragAmount ->
                                change.consume()
                                dragOffset += dragAmount
                                if (abs(dragOffset.x) > 20) {
                                    val targetIndex = (draggedColumnIndex + dragOffset.x / 100).toInt()
                                        .coerceIn(0, columnConfigs.size - 1)
                                    if (targetIndex != draggedColumnIndex) {
                                        val newList = columnConfigs.toMutableList()
                                        val dragged = newList.removeAt(draggedColumnIndex)
                                        newList.add(targetIndex, dragged)
                                        onColumnsReordered(newList)
                                        draggedColumnIndex = targetIndex
                                        dragOffset = Offset.Zero
                                    }
                                }
                            },
                            onDragEnd = {
                                draggedColumnIndex = -1
                                dragOffset = Offset.Zero
                            }
                        )
                    }
            ) {
                TableHeaderText(column.title)
            }

            // 列宽调整拖拽手柄
            if (index < columnConfigs.size - 1) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(24.dp)
                        .background(if (hoverResizeIndex == index) Color.Blue else Color.Transparent)
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragStart = { hoverResizeIndex = index },
                                onDrag = { change, dragAmount ->
                                    change.consume()
                                    val current = columnConfigs[index]
                                    val newWidth = (current.width + dragAmount.x.dp)
                                        .coerceIn(current.minWidth, current.maxWidth)
                                    (columnConfigs as MutableList)[index] = current.copy(width = newWidth)
                                },
                                onDragEnd = { hoverResizeIndex = -1 }
                            )
                        }
                )
            }
        }
    }
}

/**
 * 可编辑行组件（适配动态列）
 */
@Composable
fun EditableConfigRow(
    config: FieldConfig,
    columnConfigs: List<ColumnConfig>,
    isSelected: Boolean,
    onSelectionChange: (Boolean) -> Unit,
    onConfigUpdated: (FieldConfig) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(if (isSelected) Color(0xFFE3F2FD) else Color.White)
            .padding(2.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        columnConfigs.forEach { column ->
            when (column.key) {
                "select" -> Checkbox(
                    checked = isSelected,
                    onCheckedChange = onSelectionChange,
                    modifier = Modifier.width(column.width),
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFF2196F3))
                )
                "sqlField" -> EditTextField(
                    value = config.sqlField,
                    width = column.width,
                    onValueChange = { onConfigUpdated(config.copy(sqlField = it)) }
                )
                "fieldDesc" -> EditTextField(
                    value = config.fieldDesc,
                    width = column.width,
                    onValueChange = { onConfigUpdated(config.copy(fieldDesc = it)) }
                )
                "programAttr" -> EditTextField(
                    value = config.programAttr,
                    width = column.width,
                    onValueChange = { onConfigUpdated(config.copy(programAttr = it)) }
                )
                "length" -> EditTextField(
                    value = config.length,
                    width = column.width,
                    onValueChange = { onConfigUpdated(config.copy(length = it)) }
                )
                "dbType" -> DropDownSelector(
                    selected = config.dbType,
                    options = dbTypeOptions,
                    width = column.width,
                    onSelected = { onConfigUpdated(config.copy(dbType = it)) }
                )
                "condition" -> DropDownSelector(
                    selected = config.condition,
                    options = conditionOptions,
                    width = column.width,
                    onSelected = { onConfigUpdated(config.copy(condition = it)) }
                )
                "inputType" -> DropDownSelector(
                    selected = config.inputType,
                    options = inputTypeOptions,
                    width = column.width,
                    onSelected = { onConfigUpdated(config.copy(inputType = it)) }
                )
                "auxiliaryQuery" -> EditTextField(
                    value = config.auxiliaryQuery,
                    width = column.width,
                    onValueChange = { onConfigUpdated(config.copy(auxiliaryQuery = it)) }
                )
            }
        }
    }
}

/**
 * 表格标题文本
 */
@Composable
fun TableHeaderText(text: String) {
    Material3Text(
        text = text,
        style = MaterialTheme.typography.body2,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
    )
}

/**
 * 可编辑文本输入框
 */
@Composable
fun EditTextField(
    value: String,
    width: Dp,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.width(width),
        textStyle = MaterialTheme.typography.body2.copy(color = Color.Black),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            textColor = Color.Black,
            focusedIndicatorColor = Color.Blue,
            unfocusedIndicatorColor = Color.Gray,
            cursorColor = Color.Black
        )
    )
}

/**
 * 下拉选择器
 */
@Composable
fun DropDownSelector(
    selected: String,
    options: List<String>,
    width: Dp,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.width(width).height(40.dp),
        contentAlignment = Alignment.Center
    ) {
        TextButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxSize()
        ) {
            Material3Text(selected.ifEmpty { "请选择" })
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                ) {
                    Material3Text(option)
                }
            }
        }
    }
}

/**
 * 保存数据方法
 */
fun saveFieldConfigs(configList: List<FieldConfig>) {
    println("==================== 开始保存字段配置 ====================")
    configList.forEachIndexed { index, config ->
        println("第${index + 1}行：SQL=${config.sqlField}, 描述=${config.fieldDesc}, 程序属性=${config.programAttr}")
    }
    println("==================== 保存完成 ====================")
}

fun main() = application {
    val windowState = rememberWindowState(
        position = WindowPosition.Aligned(Alignment.Center),
        size = DpSize(1300.dp, 800.dp)
    )

    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = "KMP Desktop 字段配置编辑列表"
    ) {
        MaterialTheme {
            EditableFieldConfigList()
        }
    }
}