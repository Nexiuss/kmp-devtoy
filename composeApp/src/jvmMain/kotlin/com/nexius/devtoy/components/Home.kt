package com.nexius.devtoy.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun Home(){
    /*Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
            for (item in getAllLeafMenuItems(menuItems)) {
                if(item.id == "home")continue
                item {
                    Button(onClick = {
                    }) {
                        Text(item.name)
                    }
                }
            }
        }
    }*/
    NavigationContainer()
}