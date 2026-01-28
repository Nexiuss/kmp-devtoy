package com.nexius.devtoy.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import compose.icons.FeatherIcons
import compose.icons.FontAwesomeIcons
import compose.icons.feathericons.Tool
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.RemoveFormat
import compose.icons.fontawesomeicons.solid.Search
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.dom.Document
import java.io.ByteArrayInputStream
import java.io.StringBufferInputStream
import java.io.StringReader
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
