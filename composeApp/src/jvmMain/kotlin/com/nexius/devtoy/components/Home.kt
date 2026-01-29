package com.nexius.devtoy.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(navController: NavHostController) {
    val filteredMenus = getAllLeafMenuItems(menuItems).filter { it.id != "home" }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = Modifier.padding(16.dp),
        // 现代化间距：行列分离，提升视觉呼吸感（垂直12dp、水平8dp）
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        // 移除默认内边距，避免双重间距导致布局松散
        contentPadding = PaddingValues(0.dp)
    ) {
        items(items = filteredMenus, key = { it.id }) { menu ->
            Button(
                onClick ={
                    navController.navigate(menu.id)
                }
            ){
                Text(menu.name)
            }
        }
    }
}
