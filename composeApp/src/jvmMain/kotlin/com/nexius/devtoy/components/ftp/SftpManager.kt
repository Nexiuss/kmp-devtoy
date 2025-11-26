package com.nexius.devtoy.components.ftp

import com.jcraft.jsch.*
import java.io.File
import java.util.Vector

class SftpManager : RemoteFileManager {
    private var session: Session? = null
    private var channel: ChannelSftp? = null

    override fun connect(host: String, port: Int, user: String, pass: String): Boolean {
        session = JSch().getSession(user, host, port)
        session?.setPassword(pass)
        session?.setConfig("StrictHostKeyChecking", "no")
        session?.connect()
        channel = session?.openChannel("sftp") as ChannelSftp
        channel?.connect()
        return channel?.isConnected == true
    }

    override fun listFiles(path: String): List<FtpFile> {
        val files = channel?.ls(path) as Vector<ChannelSftp.LsEntry>
        return files.map {
            FtpFile(
                name = it.filename,
                isDirectory = it.attrs.isDir,
                size = it.attrs.size,
                path = "$path/${it.filename}"
            )
        }
    }

    override fun upload(local: File, remotePath: String): Boolean {
        channel?.put(local.absolutePath, remotePath)
        return true
    }

    override fun download(remoteFile: String, localPath: File): Boolean {
        channel?.get(remoteFile, localPath.absolutePath)
        return true
    }

    override fun delete(remotePath: String): Boolean {
        channel?.rm(remotePath)
        return true
    }

    override fun rename(from: String, to: String): Boolean {
        channel?.rename(from, to)
        return true
    }

    override fun disconnect() {
        channel?.disconnect()
        session?.disconnect()
    }
}