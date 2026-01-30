package com.nexius.devtoy.components.ftp

interface RemoteFileManager {
    fun connect(host: String, port: Int, user: String, pass: String): Boolean
    fun listFiles(path: String): List<FtpFile>
    fun upload(local: java.io.File, remotePath: String): Boolean
    fun download(remoteFile: String, localPath: java.io.File): Boolean
    fun delete(remotePath: String): Boolean
    fun rename(from: String, to: String): Boolean
    fun disconnect()
    fun pwd(): String?
    fun cd(path: String)
    fun cdp()
}