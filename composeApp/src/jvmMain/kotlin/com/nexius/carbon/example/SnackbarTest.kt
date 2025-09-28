package com.nexius.carbon.example

import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.nexius.carbon.IconImage
import com.nexius.carbon.loadIcon
import kotlinx.coroutines.launch

@Composable
fun SnackbarTest() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            Button(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("Snackbar")
                }
            }){
                Text("Show snackbar")
            }
        }
    ) { contentPadding ->
        // Screen content
    }
}