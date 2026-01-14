package com.nexius.devtoy.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// 定义页面路由（唯一标识，建议抽离为常量）
object PageRoutes {
    const val PAGE_A = "page_a" // 首页路由
    const val PAGE_B = "page_b" // 目标页面路由
}

/**
 * 导航总容器：初始化 NavHostController，配置 NavHost 和所有页面
 */
@Composable
fun NavigationContainer() {
    // 1. 创建 NavHostController（使用 rememberNavController 确保重组时状态不丢失）
    val navController = rememberNavController()

    // 2. 配置 NavHost：关联导航控制器，指定默认启动页面
    NavHost(
        navController = navController,
        startDestination = PageRoutes.PAGE_A // 默认启动首页 PageA
    ) {
        // 3. 注册页面 A（首页）
        composable(route = PageRoutes.PAGE_A) {
            PageA(
                onNavigateToPageB = {
                    // 前进操作：navigate() 方法跳转到目标页面（压入导航栈）
                    navController.navigate(PageRoutes.PAGE_B)
                }
            )
        }

        // 4. 注册页面 B（目标页面）
        composable(route = PageRoutes.PAGE_B) {
            PageB(
                onNavigateBack = {
                    // 后退操作：popBackStack() 从导航栈弹出当前页面，返回上一页
                    // 参数 inclusive = false：仅弹出当前页面（默认值，可省略）
                    navController.popBackStack()
                }
            )
        }
    }
}

/**
 * 页面 A（首页）：提供前进到 PageB 的按钮
 */
@Composable
fun PageA(onNavigateToPageB: () -> Unit) {
    TopBarNavigation(
        navigateBack = { /* 首页无上级页面，后退按钮可置空或关闭应用 */ },
        pageTitle = "首页（Page A）"
    ) { innerPadding ->
        // 自定义页面内容
        Column(
            modifier = innerPadding.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "这是首页（Page A）")
            Button(
                onClick = onNavigateToPageB,
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text(text = "前进到 Page B")
            }
        }
    }
}

/**
 * 页面 B（目标页面）：提供后退到 PageA 的按钮（同时支持 TopBar 后退图标）
 */
@Composable
fun PageB(onNavigateBack: () -> Unit) {
    TopBarNavigation(
        navigateBack = onNavigateBack, // 绑定后退逻辑到 TopBar 图标
        pageTitle = "目标页面（Page B）"
    ) { innerPadding ->
        // 自定义页面内容
        Column(
            modifier = innerPadding.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "这是目标页面（Page B）")
            Button(
                onClick = onNavigateBack,
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text(text = "后退到 Page A")
            }
        }
    }
}