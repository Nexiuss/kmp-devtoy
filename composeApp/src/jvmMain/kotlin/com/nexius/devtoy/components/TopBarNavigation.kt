package com.nexius.devtoy.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
// 注意：确保 Icons 可正常引用，若缺少可使用系统内置 Icons
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack


// 优化后：支持承载自定义内容，接收后退回调
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNavigation(
    navigateBack: () -> Unit,
    pageTitle: String = "Navigation example",
    content: @Composable (innerPadding: Modifier) -> Unit // 自定义页面内容
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(pageTitle) },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack, // 改用系统内置 Icon 确保可运行
                            contentDescription = "Back to previous page" // 更清晰的内容描述
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        // 将 Scaffold 的内边距传递给自定义内容，避免内容被 TopBar 遮挡
        content(Modifier.padding(innerPadding))
    }
}