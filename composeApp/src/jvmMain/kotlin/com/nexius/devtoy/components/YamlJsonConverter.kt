package com.nexius.devtoy.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexius.devtoy.components.Icons.ArrowDownward
import com.nexius.devtoy.components.Icons.ArrowUpward
import kotlinx.serialization.json.*
import org.yaml.snakeyaml.DumperOptions
import org.yaml.snakeyaml.Yaml
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

@Composable
fun YamlJsonConverter() {
    var jsonText by remember { mutableStateOf("") }
    var yamlText by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val clipboard = remember { Toolkit.getDefaultToolkit().systemClipboard }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 输入输出区域
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // JSON 输入区
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "JSON",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        IconButton(
                            onClick = {
                                val selection = StringSelection(jsonText)
                                clipboard.setContents(selection, selection)
                            },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.ContentCopy,
                                contentDescription = "复制 JSON",
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                jsonText = ""
                                yamlText = ""
                                errorMessage = ""
                            },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Clear,
                                contentDescription = "清空",
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
                OutlinedTextField(
                    value = jsonText,
                    onValueChange = {
                        jsonText = it
                        errorMessage = ""
                    },
                    modifier = Modifier.fillMaxSize(),
                    placeholder = { Text("在此输入 JSON...") },
                    singleLine = false,
                    textStyle = MaterialTheme.typography.bodyMedium
                )
            }

            // 中间按钮区
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = {
                    try {
                        yamlText = jsonToYaml(jsonText)
                        errorMessage = ""
                    } catch (e: Exception) {
                        errorMessage = "JSON → YAML 失败: ${e.message}"
                    }
                }) {
                    Icon(Icons.ArrowRight, contentDescription = "正向转换")
                }
                IconButton(onClick = {
                    try {
                        jsonText = yamlToJson(yamlText)
                        errorMessage = ""
                    } catch (e: Exception) {
                        errorMessage = "YAML → JSON 失败: ${e.message}"
                    }
                }) {
                    Icon(Icons.ArrowLeft, contentDescription = "反向转换")
                }
            }

            // YAML 输入区
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "YAML",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    IconButton(
                        onClick = {
                            val selection = StringSelection(yamlText)
                            clipboard.setContents(selection, selection)
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.ContentCopy,
                            contentDescription = "复制 YAML",
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                OutlinedTextField(
                    value = yamlText,
                    onValueChange = {
                        yamlText = it
                        errorMessage = ""
                    },
                    modifier = Modifier.fillMaxSize(),
                    placeholder = { Text("在此输入 YAML...") },
                    singleLine = false,
                    textStyle = MaterialTheme.typography.bodyMedium
                )
            }
        }

        // 错误信息
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

private fun jsonToYaml(jsonStr: String): String {
    val json = Json { prettyPrint = false }
    val element = json.parseToJsonElement(jsonStr.trim())
    val native = element.toNative()
    val options = DumperOptions().apply {
        defaultFlowStyle = DumperOptions.FlowStyle.BLOCK
    }
    return Yaml(options).dump(native).trimEnd()
}

private fun yamlToJson(yamlStr: String): String {
    val yaml = Yaml()
    val loaded = yaml.load<Any>(yamlStr.trim())
    val jsonElement = loaded.toJsonElement()
    return Json { prettyPrint = true }.encodeToString(jsonElement)
}

private fun JsonElement.toNative(): Any? {
    return when (this) {
        is JsonObject -> LinkedHashMap(this.mapValues { it.value.toNative() })
        is JsonArray -> ArrayList(this.map { it.toNative() })
        is JsonPrimitive -> when {
            this.isString -> this.content
            this.booleanOrNull != null -> this.boolean
            this.intOrNull != null -> this.int
            this.longOrNull != null -> this.long
            this.doubleOrNull != null -> this.double
            else -> this.content
        }

        else -> null
    }
}

private fun Any?.toJsonElement(): JsonElement {
    return when (this) {
        is Map<*, *> -> JsonObject(
            this.mapKeys { it.key.toString() }
                .mapValues { it.value.toJsonElement() }
        )
        is List<*> -> JsonArray(this.map { it.toJsonElement() })
        is String -> JsonPrimitive(this)
        is Number -> JsonPrimitive(this)
        is Boolean -> JsonPrimitive(this)
        null -> JsonNull
        else -> JsonPrimitive(this.toString())
    }
}
