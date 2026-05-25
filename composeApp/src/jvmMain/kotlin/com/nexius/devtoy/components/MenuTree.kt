package com.nexius.devtoy.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import com.nexius.devtoy.components.Icons.ArrowDropDown
import com.nexius.devtoy.components.Icons.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.nexius.devtoy.menuItems

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

// 菜单树组件
@Composable
fun FontAwesomeMenuTree(
    modifier: Modifier = Modifier,
    menuItems: List<MenuItem> = com.nexius.devtoy.menuItems,
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
                    imageVector = if (isExpanded) ArrowDropDown else ChevronRight,
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