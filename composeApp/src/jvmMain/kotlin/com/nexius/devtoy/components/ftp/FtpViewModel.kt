package com.nexius.devtoy.components.ftp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.io.File

enum class ProtocolType { FTP, SFTP }

class FtpViewModel : ViewModel() {
    var isLoading by mutableStateOf(false)
    var protocolType by mutableStateOf(ProtocolType.SFTP)
    var files by mutableStateOf<List<FtpFile>>(emptyList())
    var connected by mutableStateOf(false)
    var currentPath by mutableStateOf("")
    var ftp = FtpManager()
    var sft = SftpManager()
    private val fileManager: RemoteFileManager
        get() = if (protocolType == ProtocolType.SFTP) sft else ftp
    fun connect(host: String, port: Int, user: String, pass: String): String? {
        try {
            connected = fileManager.connect(host, port, user, pass)
        } catch (e: Exception) {
            println("连接失败: ${e.message}")
            return "连接失败: ${e.message}"
        }
        if (connected) listFiles("/")
        return null
    }

    fun listFiles(path: String) {
        files = fileManager.listFiles(path)
    }

    fun listFiles(path: String, nameLike:String) {
        files = fileManager.listFiles(path).filter { it.name.contains(nameLike) }
    }

    fun upload(local: File, remoteName: String) {
        fileManager.upload(local, "$currentPath/$remoteName")
        listFiles(currentPath)
    }

    fun download(remote: FtpFile, local: File): Boolean {
        return fileManager.download(remote.path, local)
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

    fun pwd() {
        currentPath =  fileManager.pwd()?:"";
    }
    fun cd(path: String){
        fileManager.cd( path)
    }

    fun cdp(){
        fileManager.cdp()
    }
    // 扩展 FtpViewModel 接口，添加所需的新方法（实际实现需要在 ViewModel 中完成）
    fun uploadLocalFile(targetPath: String) {
        // 实现本地文件选择和上传逻辑
        // 实际项目中需要：
        // 1. 使用 ActivityResultContracts.GetContent 或 GetMultipleContents 选择文件
        // 2. 获取选中文件的 Uri
        // 3. 将文件上传到 FTP 服务器的 targetPath 路径
    }

    fun createFile(parentPath: String, fileName: String) {
        // 实现创建远程文件的逻辑
    }

    fun createDirectory(parentPath: String, dirName: String) {
        // 实现创建远程文件夹的逻辑
    }

    fun packageAndTransfer(file: FtpFile) {
        // 实现打包传输逻辑：
        // 1. 如果是文件：直接压缩或作为单个文件传输
        // 2. 如果是文件夹：压缩整个文件夹
        // 3. 传输压缩包到本地或其他位置
    }
}