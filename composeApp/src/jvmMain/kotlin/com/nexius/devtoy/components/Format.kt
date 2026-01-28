package com.nexius.devtoy.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.nexius.devtoy.utils.SqlFormatter
import com.nexius.devtoy.utils.SqlSyntaxHighlightTransformation
import compose.icons.FeatherIcons
import compose.icons.feathericons.Tool
import kotlinx.serialization.json.Json
import org.w3c.dom.Document
import java.io.ByteArrayInputStream
import java.io.StringWriter
import java.nio.charset.StandardCharsets
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

@Composable
fun JsonFormat() {
    var jsonStr by remember { mutableStateOf("{}") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 输入框区域
        OutlinedTextField(
            value = jsonStr,
            onValueChange = { jsonStr = it },
            modifier = Modifier.weight(1f),
            label = { Text("JSON输入/输出") },
            singleLine = false  // 允许多行显示
        )

        // 格式化按钮
        IconButton(
            onClick = {
                try {
                    // 尝试解析当前JSON字符串后重新格式化
                    jsonStr = Json { prettyPrint = true }.encodeToString(
                        Json.parseToJsonElement(jsonStr)
                    )
                } catch (e: Exception) {
                    // 解析失败时保持原样
                    jsonStr = "{}"
                }
            },
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Icon(FeatherIcons.Tool,
                contentDescription = "格式化JSON"
            )
        }
    }
}


@Composable
fun XmlFormat() {
    var xmlStr by remember { mutableStateOf("<root>test</root>") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 输入框区域
        OutlinedTextField(
            value = xmlStr,
            onValueChange = { xmlStr = it },
            modifier = Modifier.weight(1f),
            label = { Text("Xml输入/输出") },
            singleLine = false  // 允许多行显示
        )

        // 格式化按钮
        IconButton(
            onClick = {
                // 尝试解析当前JSON字符串后重新格式化
                xmlStr = xmlStr.formatXml()
            },
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Icon(FeatherIcons.Tool,
                contentDescription = "格式化Xml"
            )
        }
    }


}

fun String.formatXml(
    indent: Int = 4,
    omitDeclaration: Boolean = false
): String = try {
    // 解析XML，使用ByteArrayInputStream替代过时的StringBufferInputStream
    val document: Document = DocumentBuilderFactory.newInstance()
        .newDocumentBuilder()
        .parse(ByteArrayInputStream(this.toByteArray(StandardCharsets.UTF_8)))

    // 配置转换器
    val transformer = TransformerFactory.newInstance().newTransformer().apply {
        setOutputProperty(OutputKeys.INDENT, "yes")
        setOutputProperty(OutputKeys.METHOD, "xml")
        setOutputProperty(OutputKeys.ENCODING, "UTF-8")
        setOutputProperty("{http://xml.apache.org/xslt}indent-amount", indent.toString())
        if (omitDeclaration) {
            setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes")
        }
    }

    // 执行转换并获取结果
    val writer = StringWriter()
    transformer.transform(DOMSource(document), StreamResult(writer))
    writer.toString()
} catch (e: Exception) {
    // 格式化失败时返回原始字符串
    e.printStackTrace()
    this
}


@Composable
fun SqlFormat() {
    // 改用TextFieldValue保存输入状态，更好支持光标位置、文本选择等编辑特性
    var sqlInput by remember { mutableStateOf(TextFieldValue("SELECT * FROM table;")) }
    var formattedSql by remember { mutableStateOf("") }

    // 整体布局：垂直排列，占满可用空间，内边距16dp
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 1. 左侧SQL输入框
        // 1. 左侧SQL输入框
        BasicTextField(
            value = sqlInput,
            onValueChange = { sqlInput = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // 占满剩余垂直空间，与右侧输出框等高
                .border(1.dp, Color.Gray, MaterialTheme.shapes.small)
                .padding(8.dp),
            textStyle = MaterialTheme.typography.bodyLarge,
            visualTransformation = SqlSyntaxHighlightTransformation()
        )
        Row(Modifier.align(Alignment.CenterHorizontally), horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            // 2. 格式化按钮：居中显示，添加点击反馈
            Button(
                onClick = {
                    try {
                        formattedSql = SqlFormatter.format(sqlInput.text) ?: ""
                    } catch (e: Exception) {
                        formattedSql = "SQL格式错误，格式化失败：${e.message}"
                    }
                }) {
                Text(text = "格式化SQL")
            }

            Button(onClick = { formattedSql = SqlFormatter.compress(sqlInput.text) ?: "" }) { Text("压缩") }
            Button(onClick = { formattedSql = SqlFormatter.validateSql(sqlInput.text)}) { Text("验证") }
            Button(onClick = { sqlInput = TextFieldValue(""); formattedSql = "" }) { Text("清空") }
        }

        // 3. 右侧SQL输出框（只读，显示格式化结果）
        BasicTextField(
            value = formattedSql,
            onValueChange = {}, // 空实现，设为只读
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .border(1.dp, Color.Gray, MaterialTheme.shapes.small)
                .padding(8.dp),
            textStyle = MaterialTheme.typography.bodyLarge.copy(Color(0xFF006600)), // 绿色字体更易读
            enabled = false, // 禁用编辑，保留文本选择/复制功能
            visualTransformation = SqlSyntaxHighlightTransformation()
        )
    }
}