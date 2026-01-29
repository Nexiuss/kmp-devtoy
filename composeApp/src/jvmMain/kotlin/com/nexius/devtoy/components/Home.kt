package com.nexius.devtoy.components

import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(navController: NavHostController) {
    FlowRow(modifier = Modifier.padding(8.dp)) {
        var leafMenus = getAllLeafMenuItems(menuItems)
        for (menu in leafMenus) {
            Chip(onClick = {
                navController.navigate(menu.id)
            }){
                Text(menu.name)
            }
        }
    }
}
