package com.nexius.devtoy.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import compose.icons.FeatherIcons
import compose.icons.FontAwesomeIcons
import compose.icons.feathericons.FileText
import compose.icons.feathericons.Maximize
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.brands.Html5
import compose.icons.fontawesomeicons.solid.ChevronDown
import compose.icons.fontawesomeicons.solid.ChevronRight
import compose.icons.fontawesomeicons.solid.Cog
import compose.icons.fontawesomeicons.solid.FileUpload
import compose.icons.fontawesomeicons.solid.FileVideo
import compose.icons.fontawesomeicons.solid.FileWord
import compose.icons.fontawesomeicons.solid.FolderOpen
import compose.icons.fontawesomeicons.solid.Forward
import compose.icons.fontawesomeicons.solid.Genderless
import compose.icons.fontawesomeicons.solid.Home
import compose.icons.fontawesomeicons.solid.MoneyBillWaveAlt
import compose.icons.fontawesomeicons.solid.Qrcode

// 菜单项数据类
data class MenuItem(
    val id: String,
    val path: String,
    val name: String,
    val icon: ImageVector,
    val expandedIcon: ImageVector? = null, // 可选的展开状态图标
    val children: List<MenuItem> = emptyList()
)

fun getById(id: String): MenuItem {
    return getItem(tree = menuItems, filter = { it.id == id })
        ?: throw IllegalArgumentException("No menu item found with id $id")
}

fun getByName(name: String): MenuItem {
    return getItem(tree = menuItems, filter = { it.name == name })
        ?: throw IllegalArgumentException("No menu item found with name $name")
}

fun getItem(tree: List<MenuItem>, filter:(MenuItem)-> Boolean): MenuItem? {
    tree.forEach {
        if (filter(it)) {
            return it
        }
        if (it.children.isNotEmpty()) {
            val child = getItem(it.children, filter)
            if (child != null) {
                return child
            }
        }
    }
    return null
}

// 示例菜单数据 - 使用Font Awesome图标
val menuItems = listOf(
    MenuItem(
        id = "home",
        path = "root/home",
        name = "首页",
        icon = FontAwesomeIcons.Solid.Home ,
    ),
    MenuItem(
        id = "endecode",
        path = "root/endecode",
        name = "编解码器",
        icon = FontAwesomeIcons.Solid.MoneyBillWaveAlt,
        expandedIcon = FontAwesomeIcons.Solid.FolderOpen,
        children = listOf(
            MenuItem(
                id = "base64",
                path = "root/endecode/base64",
                name = "base64文本",
                icon = FontAwesomeIcons.Solid.FileWord),
            MenuItem(
                id = "html",
                path = "root/endecode/html",
                name = "HTML",
                icon = FontAwesomeIcons.Brands.Html5,
                expandedIcon = FontAwesomeIcons.Solid.FolderOpen
            ),
            MenuItem(
                id = "url",
                path =  "root/endecode/url",
                name = "URL",
                icon = FontAwesomeIcons.Solid.FileVideo,
                expandedIcon = FontAwesomeIcons.Solid.FolderOpen
            ),
            MenuItem(
                id = "qrCode",
                path =  "root/endecode/qrCode",
                name = "二维码",
                icon = FontAwesomeIcons.Solid.Qrcode,
                expandedIcon = FontAwesomeIcons.Solid.FolderOpen
            )
        )
    ),
    MenuItem(
        id = "format",
        name =  "格式化工具",
        path =  "root/format",
        icon = FontAwesomeIcons.Solid.Forward,
        expandedIcon = FontAwesomeIcons.Solid.FolderOpen,
        children = listOf(
            MenuItem(
                id = "json",
                path = "root/format/json",
                name = "JSON",
                icon = FontAwesomeIcons.Solid.FileWord),
            MenuItem(
                id = "sql",
                path = "root/format/sql",
                name = "SQL",
                icon = FontAwesomeIcons.Brands.Html5,
                expandedIcon = FontAwesomeIcons.Solid.FolderOpen
            ),
            MenuItem(
                id = "xml",
                path =  "root/format/xml",
                name = "XML",
                icon = FontAwesomeIcons.Solid.FileVideo,
                expandedIcon = FontAwesomeIcons.Solid.FolderOpen
            )
        )
    ),
    MenuItem(
        id = "generator",
        name =  "生成器",
        path =  "root/generator",
        icon = FontAwesomeIcons.Solid.Genderless,
        expandedIcon = FontAwesomeIcons.Solid.FolderOpen,
        children = listOf(
            MenuItem(
                id = "uuid",
                path = "root/generator/uuid",
                name = "UUID",
                icon = FontAwesomeIcons.Solid.FileWord)
        )
    ),
    MenuItem(
        id = "text",
        name =  "文本处理",
        path =  "root/text",
        icon = FeatherIcons.FileText,
        expandedIcon = FontAwesomeIcons.Solid.FolderOpen,
        children = listOf(
            MenuItem(
                id = "markdown",
                path = "root/text/markdown",
                name = "Markdown预览",
                icon = FontAwesomeIcons.Solid.FileWord)
        )
    ),
    MenuItem(
        id = "ftp",
        path = "root/ftp",
        name = "FTP",
        icon = FontAwesomeIcons.Solid.FileUpload,
    ),
)

// 菜单树组件
@Composable
fun FontAwesomeMenuTree(
    modifier: Modifier = Modifier,
    menuItems: List<MenuItem> = com.nexius.devtoy.components.menuItems,
    level: Int = 0,
    onClick: (MenuItem) -> Unit= {}
) {
    Column(modifier = modifier) {
        menuItems.forEach { menuItem ->
            MenuItem(
                menuItem = menuItem,
                level = level,
                onClick = onClick
            )
        }
    }
}

// 单个菜单项组件
@Composable
private fun MenuItem(
    menuItem: MenuItem,
    level: Int,
    onClick: (MenuItem) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    val hasChildren = menuItem.children.isNotEmpty()

    // 根据展开状态选择图标
    val displayIcon = if (hasChildren && isExpanded && menuItem.expandedIcon != null) {
        menuItem.expandedIcon
    } else {
        menuItem.icon
    }

    Column {
        // 菜单项标题行
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    if (hasChildren) {
                        isExpanded = !isExpanded
                    }
                    // 处理菜单项点击事件
                    onClick.invoke(menuItem)
                }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 层级缩进
            Spacer(modifier = Modifier.width(level.times(16).dp))

            // 展开/折叠指示器
            if (hasChildren) {
                Image(
                    imageVector = if (isExpanded) FontAwesomeIcons.Solid.ChevronDown else FontAwesomeIcons.Solid.ChevronRight,
                    contentDescription = if (isExpanded) "折叠" else "展开",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            } else {
                Spacer(modifier = Modifier.width(24.dp)) // 与有指示器的项对齐
            }

            // 菜单项图标 (使用Font Awesome)
            Image(
                imageVector = displayIcon,
                contentDescription = menuItem.name,
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // 菜单项文本
            Text(
                text = menuItem.name,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // 子菜单（根据展开状态显示/隐藏）
        if (hasChildren && isExpanded) {
            FontAwesomeMenuTree(
                menuItems = menuItem.children,
                level = level + 1,
                onClick = onClick
            )
        }
    }
}

// 菜单项点击处理
private fun onMenuItemClick(menuItem: MenuItem) {
    // 处理菜单项点击逻辑
    println("点击了菜单项: ${menuItem.name} (${menuItem.id})")
}

// 菜单树容器组件
@Composable
fun FontAwesomeMenuTreeContainer() {
    Card(
        modifier = Modifier
            .width(200.dp)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(
                text = "DevToy",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
            ) {
                FontAwesomeMenuTree(menuItems = menuItems)
            }
        }
    }
}
