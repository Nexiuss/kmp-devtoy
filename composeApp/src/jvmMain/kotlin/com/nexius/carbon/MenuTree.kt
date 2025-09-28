package com.nexius.carbon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gabrieldrn.carbon.foundation.color.button.ButtonColors


data class MenuItem(
    val title: String,
    val children: List<MenuItem> = emptyList()
)


@Composable
fun MenuTree(menuItems: List<MenuItem>, level: Int = 0) {
    Column {
        for (item in menuItems) {
            // 展开状态只给有子菜单的项
            var expanded by remember { mutableStateOf(false) }
            if (item.children.isNotEmpty()) {
                Text(
                    text = (if (expanded) "▼ " else "▶ ") + item.title,
                    modifier = Modifier
                        .padding(start = (level * 16).dp)
                        .clickable { expanded = !expanded }
                )
                if (expanded) {
                    MenuTree(item.children, level + 1)
                }
            } else {
                /*Button(onClick = { *//*TODO*//* }, modifier = Modifier.padding(start = (level * 16).dp), content = {
                    Text(text = item.title)
                })*/
                /*Text(
                    text = item.title,
                    modifier = Modifier.padding(start = (level * 16).dp)
                )*/
                com.gabrieldrn.carbon.button.Button(item.title, {})

            }
        }
    }
}

@Composable
fun MainMenuSample() {
    val menuData = listOf(
        MenuItem("文件", listOf(
            MenuItem("新建"),
            MenuItem("打开"),
            MenuItem("保存")
        )),
        MenuItem("编辑", listOf(
            MenuItem("撤销"),
            MenuItem("重做")
        )),
        MenuItem("帮助")
    )
    MenuTree(menuData)
}