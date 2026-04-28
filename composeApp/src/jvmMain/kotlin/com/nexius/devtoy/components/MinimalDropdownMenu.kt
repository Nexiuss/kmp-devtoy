package com.nexius.devtoy.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexius.devtoy.config.BuildConfig

enum class DropDownMenu(var string: String) {
    Setting("设置"),
    About("关于"),
}
@Composable
fun MinimalDropdownMenu() {
    var expanded by remember { mutableStateOf(false) }
    var selectMenu by remember { mutableStateOf(DropDownMenu.About) }
    var openAlertDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            for (menu in DropDownMenu.entries) {
                DropdownMenuItem(
                    text = { Text(menu.name) },
                    onClick = {
                        selectMenu = menu
                        openAlertDialog = true
                    }
                )
            }
        }
    }
    if(openAlertDialog){
        MinimalDialog(onDismissRequest = {
            openAlertDialog = false
        }){
            when(selectMenu){
                DropDownMenu.About->AboutPage()
                DropDownMenu.Setting->setting()
            }
        }
    }
}
@Composable
fun AboutPage() {
    // 卡片主体：圆角 + 阴影 + 内边距
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.padding(24.dp), // 卡片内部边距
            verticalArrangement = Arrangement.spacedBy(16.dp) // 文本之间均匀间距
        ) {
            // 页面标题
            Text(
                text = "关于",
                fontSize = 24.sp,
                style = MaterialTheme.typography.headlineSmall
            )
            Text("${BuildConfig.PACKAGE_NAME} ${BuildConfig.PACKAGE_VERSION}")
            val gitUrl = BuildConfig.GIT_ADDR
            val linkText = buildAnnotatedString {
                // 给链接添加：蓝色 + 下划线样式
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF2196F3),
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    // 核心：添加可点击链接
                    withLink(LinkAnnotation.Url(url = gitUrl)) {
                        append(gitUrl)
                    }
                }
            }
            // 直接使用普通 Text 即可！
            Text(
                text = linkText,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

data class Settings(val language: String)

@Composable
fun setting() = Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
    Text("敬请期待")

}
