package com.nexius.carbon.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import com.gabrieldrn.carbon.textinput.TextArea
import compose.icons.FeatherIcons
import androidx.compose.material3.Icon
import compose.icons.feathericons.ChevronsDown
import compose.icons.feathericons.ChevronsUp
import java.net.URLDecoder
import java.net.URLEncoder
import java.util.Base64

@Composable
fun UrlDeEncode() {
    //左边输入框
    var leftTextFieldValue by remember { mutableStateOf("") }
    //右边输入框
    var rightTextFieldValue by remember { mutableStateOf("") }


    TextArea(
        value = leftTextFieldValue,
        onValueChange = { leftTextFieldValue = it },
        label = "明文"
    )
    Row {
        IconButton(onClick = {
            rightTextFieldValue = URLEncoder.encode(leftTextFieldValue, "utf-8")
        }){
            Icon(FeatherIcons.ChevronsDown, contentDescription = "编码")
        }
        IconButton(onClick = {
            leftTextFieldValue = URLDecoder.decode(rightTextFieldValue, "utf-8")
        }){
            Icon(FeatherIcons.ChevronsUp, contentDescription = "编码")
        }
    }
    TextArea(
        value = rightTextFieldValue,
        onValueChange = { rightTextFieldValue = it },
        label = "密文"
    )
}

@Composable
fun HtmlDeEncode() {
    //左边输入框
    var leftTextFieldValue by remember { mutableStateOf("") }
    //右边输入框
    var rightTextFieldValue by remember { mutableStateOf("") }


    TextArea(
        value = leftTextFieldValue,
        onValueChange = { leftTextFieldValue = it },
        label = "明文"
    )
    Row {
        IconButton(onClick = {
            rightTextFieldValue = encodeHtml(leftTextFieldValue)
        }){
            Icon(FeatherIcons.ChevronsDown, contentDescription = "编码")
        }
        IconButton(onClick = {
            leftTextFieldValue =decodeHtml(rightTextFieldValue)
        }){
            Icon(FeatherIcons.ChevronsUp, contentDescription = "编码")
        }
    }
    TextArea(
        value = rightTextFieldValue,
        onValueChange = { rightTextFieldValue = it },
        label = "密文"
    )
}


@Composable
fun Base64DeEncode() {
    //左边输入框
    var leftTextFieldValue by remember { mutableStateOf("") }
    //右边输入框
    var rightTextFieldValue by remember { mutableStateOf("") }


    TextArea(
        value = leftTextFieldValue,
        onValueChange = { leftTextFieldValue = it },
        label = "明文"
    )
    Row {
        IconButton(onClick = {
            rightTextFieldValue = Base64.getEncoder().encodeToString(leftTextFieldValue.toByteArray())
        }){
            Icon(FeatherIcons.ChevronsDown, contentDescription = "编码")
        }
        IconButton(onClick = {
            leftTextFieldValue =String(Base64.getDecoder().decode(rightTextFieldValue))
        }){
            Icon(FeatherIcons.ChevronsUp, contentDescription = "编码")
        }
    }
    TextArea(
        value = rightTextFieldValue,
        onValueChange = { rightTextFieldValue = it },
        label = "密文"
    )
}

fun decodeHtml(input: String): String {
    val entities = mapOf(
        "&amp;" to '&',
        "&lt;" to '<',
        "&gt;" to '>',
        "&quot;" to '"',
        "&#39;" to '\''
    )
    var output = input
    for ((key, value) in entities) {
        output = output.replace(key, value.toString())
    }
    return output
}

fun encodeHtml(input: String): String {
    val entities = mapOf(
        '&' to "&amp;",
        '<' to "&lt;",
        '>' to "&gt;",
        '"' to "&quot;",
        '\'' to "&#39;"
    )
    val sb = StringBuilder()
    for (char in input) {
        sb.append(entities[char] ?: char)
    }
    return sb.toString()
}