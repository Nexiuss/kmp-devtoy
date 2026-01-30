package com.nexius.devtoy.components.popup.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.nexius.devtoy.components.popup.PopupState

@Composable
fun BasicPopup(popupState: PopupState) {
    if (popupState.isShow) {
        Popup(
            alignment = Alignment.Center,
            onDismissRequest = { popupState.dismiss() }
        ) {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .border(1.dp, Color.Gray)
                    .padding(16.dp)
            ) {
                Text(popupState.text)
            }
        }
    }
}
