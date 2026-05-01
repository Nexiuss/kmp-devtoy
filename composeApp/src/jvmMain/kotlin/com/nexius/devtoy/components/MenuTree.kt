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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.CoffeeMaker
import androidx.compose.material.icons.filled.DataArray
import androidx.compose.material.icons.filled.DriveFolderUpload
import androidx.compose.material.icons.filled.FilePresent
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.FormatPaint
import androidx.compose.material.icons.filled.GppGood
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Html
import androidx.compose.material.icons.filled.Http
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.NetworkPing
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.TextFormat
import androidx.compose.material.icons.filled.Transform
import androidx.compose.material.icons.filled.Verified
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
        icon = Icons.Default.Home ,
    ),
    MenuItem(
        id = "endecode",
        path = "root/endecode",
        name = "编解码器",
        icon = Icons.Default.Transform,
        expandedIcon = Icons.Default.FolderOpen,
        children = listOf(
            MenuItem(
                id = "base64",
                path = "root/endecode/base64",
                name = "base64文本",
                icon = Icons.Default.Code),
            MenuItem(
                id = "html",
                path = "root/endecode/html",
                name = "HTML",
                icon = Icons.Default.Html,
                expandedIcon = Icons.Default.FolderOpen
            ),
            MenuItem(
                id = "url",
                path =  "root/endecode/url",
                name = "URL",
                icon = Icons.Default.Link,
                expandedIcon = Icons.Default.FolderOpen
            ),
            MenuItem(
                id = "qrCode",
                path =  "root/endecode/qrCode",
                name = "二维码",
                icon = Icons.Default.QrCode,
                expandedIcon = Icons.Default.FolderOpen
            ),
            MenuItem(
                id = "jwt",
                path =  "root/endecode/jwt",
                name = "JWT",
                icon = Icons.Default.Verified,
                expandedIcon = Icons.Default.FolderOpen
            )
        )
    ),
    MenuItem(
        id = "format",
        name =  "格式化工具",
        path =  "root/format",
        icon = Icons.Default.FormatPaint,
        expandedIcon = Icons.Default.FolderOpen,
        children = listOf(
            MenuItem(
                id = "json",
                path = "root/format/json",
                name = "JSON",
                icon = Icons.Default.DataArray),
            MenuItem(
                id = "sql",
                path = "root/format/sql",
                name = "SQL",
                icon = Icons.Default.Storage,
                expandedIcon = Icons.Default.FolderOpen
            ),
            MenuItem(
                id = "xml",
                path =  "root/format/xml",
                name = "XML",
                icon = Icons.Default.Html,
                expandedIcon = Icons.Default.FolderOpen
            )
        )
    ),
    MenuItem(
        id = "generator",
        name =  "生成器",
        path =  "root/generator",
        icon = Icons.Default.AddBox,
        expandedIcon = Icons.Default.FolderOpen,
        children = listOf(
            MenuItem(
                id = "uuid",
                path = "root/generator/uuid",
                name = "UUID",
                icon = Icons.Default.GppGood)
        )
    ),
    MenuItem(
        id = "text",
        name =  "文本处理",
        path =  "root/text",
        icon = Icons.Default.TextFormat,
        expandedIcon = Icons.Default.FolderOpen,
        children = listOf(
            MenuItem(
                id = "markdown",
                path = "root/text/markdown",
                name = "Markdown",
                icon = Icons.Filled.Article),
            MenuItem(
                id = "markitdown",
                path = "root/text/markitdown",
                name = "markitdown",
                icon = Icons.Filled.CoffeeMaker),
        )
    ),
    MenuItem(
        id = "net",
        name =  "网络",
        path =  "root/net",
        icon = Icons.Default.NetworkPing,
        expandedIcon = Icons.Default.FolderOpen,
        children = listOf(
            MenuItem(
                id = "httpClient",
                path = "root/net/httpClient",
                name = "Http客户端",
                icon = Icons.Default.Http)
        )
    ),
    MenuItem(
        id = "ftp",
        path = "root/ftp",
        name = "FTP",
        icon = Icons.Default.DriveFolderUpload,
    ),
    MenuItem(
        id = "fileRename",
        path = "root/fileRename",
        name = "文件重命名",
        icon = Icons.Filled.FilePresent,
    ),
    MenuItem(
        id = "loanCalculator",
        path = "root/LoanCalculator",
        name = "贷款计算",
        icon = Icons.Default.Calculate,
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
                    imageVector = if (isExpanded) Icons.Default.ArrowDropDown else Icons.Default.ChevronRight,
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
/**
 * 递归遍历菜单树，获取所有叶子节点（无子菜单的MenuItem）
 * @param tree 菜单树（顶层或某一级子菜单列表）
 * @return 所有叶子节点组成的列表
 */
fun getAllLeafMenuItems(tree: List<MenuItem>): List<MenuItem> {
    // 用于收集所有叶子节点
    val leafItems = mutableListOf<MenuItem>()

    tree.forEach { menuItem ->
        // 判定叶子节点：children为空列表
        if (menuItem.children.isEmpty()) {
            leafItems.add(menuItem)
        } else {
            // 非叶子节点，递归遍历其子菜单，收集子菜单中的叶子节点
            leafItems.addAll(getAllLeafMenuItems(menuItem.children))
        }
    }

    return leafItems
}