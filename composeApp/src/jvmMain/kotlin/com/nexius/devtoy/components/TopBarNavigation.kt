package com.nexius.devtoy.components

// 注意：确保 Icons 可正常引用，若缺少可使用系统内置 Icons
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nexius.devtoy.getContent

/**
 * 创建导航,需要传递一个 rememberNavController()
 */
@Composable
fun Navigation(navController: NavHostController,leafMenus: List<MenuItem> =getAllLeafMenuItems(menuItems)){
    NavHost(navController = navController, startDestination = "home") {
        for (menu in leafMenus) {
            composable(menu.id) {
                // 原有内容逻辑保持不变
                if (menu.id == "home") {
                    Home(navController)
                } else {
                    Column {
                        getContent(menu)
                    }
                }
            }
        }
    }
}