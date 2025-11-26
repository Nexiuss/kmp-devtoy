package com.nexius.devtoy.components.ftp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import androidx.compose.runtime.*
import java.io.File
enum class ProtocolType { FTP, SFTP }

class FtpViewModel : ViewModel() {
    var protocolType by mutableStateOf(ProtocolType.FTP)
    var files by mutableStateOf<List<FtpFile>>(emptyList())
    var connected by mutableStateOf(false)
    var currentPath by mutableStateOf("/")
    var ftp = FtpManager()
    var sft = SftpManager()
    private val fileManager: RemoteFileManager
        get() = if (protocolType == ProtocolType.SFTP) ftp else sft
    fun connect(host: String, port: Int, user: String, pass: String) {
        connected = fileManager.connect(host, port, user, pass)
        if (connected) listFiles("/")
    }

    fun listFiles(path: String) {
        files = fileManager.listFiles(path)
        currentPath = path
    }

    fun upload(local: File, remoteName: String) {
        fileManager.upload(local, "$currentPath/$remoteName")
        listFiles(currentPath)
    }

    fun download(remote: FtpFile, local: File) {
        fileManager.download(remote.path, local)
    }

    fun delete(file: FtpFile) {
        fileManager.delete(file.path)
        listFiles(currentPath)
    }

    fun rename(from: FtpFile, toName: String) {
        fileManager.rename(from.path, "$currentPath/$toName")
        listFiles(currentPath)
    }

    fun disconnect() {
        fileManager.disconnect()
        connected = false
        files = emptyList()
        currentPath = "/"
    }
}