package com.nexius.devtoy.components.ftp

import org.apache.commons.net.ftp.FTPClient
import java.io.File

class FtpManager : RemoteFileManager{
    private val client = FTPClient()

    override fun connect(host: String, port: Int, user: String, pass: String): Boolean {
        client.connect(host, port)
        return client.login(user, pass)
    }

    override fun listFiles(path: String): List<FtpFile> {
        return client.listFiles(path).map {
            FtpFile(
                name = it.name,
                isDirectory = it.isDirectory,
                size = it.size,
                path = path + "/" + it.name
            )
        }
    }

    override fun upload(local: File, remotePath: String): Boolean {
        local.inputStream().use {
            return client.storeFile(remotePath, it)
        }
    }

    override fun download(remoteFile: String, localPath: File): Boolean {
        localPath.outputStream().use {
            return client.retrieveFile(remoteFile, it)
        }
        return true
    }

    override fun delete(remotePath: String): Boolean {
        return client.deleteFile(remotePath)
    }

    override fun rename(from: String, to: String): Boolean {
        return client.rename(from, to)
    }

    override fun disconnect() {
        client.logout()
        client.disconnect()
    }
}

data class FtpFile(val name: String, val isDirectory: Boolean, val size: Long, val path: String)