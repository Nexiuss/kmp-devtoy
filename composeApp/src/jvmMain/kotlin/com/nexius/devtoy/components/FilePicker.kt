package com.nexius.devtoy.components

import androidx.compose.foundation.content.PlatformTransferableContent
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.calf.core.LocalPlatformContext
import com.mohamedrejeb.calf.core.PlatformContext
import com.mohamedrejeb.calf.io.KmpFile
import com.mohamedrejeb.calf.io.getPath
import com.mohamedrejeb.calf.io.readByteArray
import com.mohamedrejeb.calf.picker.FilePickerFileType
import com.mohamedrejeb.calf.picker.FilePickerLauncher
import com.mohamedrejeb.calf.picker.FilePickerSelectionMode
import com.mohamedrejeb.calf.picker.rememberFilePickerLauncher
import kotlinx.coroutines.launch

@Composable
fun FilePicker(fileType:FilePickerFileType, onResult: (List<KmpFile>, PlatformContext) -> Unit,content: @Composable () -> Unit={}): FilePickerLauncher {
    val scope = rememberCoroutineScope()
    val context = LocalPlatformContext.current

    val pickerLauncher = rememberFilePickerLauncher(
        type = fileType,
        selectionMode = FilePickerSelectionMode.Single,
        onResult = { files ->
            scope.launch {
                onResult.invoke(files,context)
            }
        }
    )
    return pickerLauncher
}