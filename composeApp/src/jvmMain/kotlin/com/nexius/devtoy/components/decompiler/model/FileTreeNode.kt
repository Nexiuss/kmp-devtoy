package com.nexius.devtoy.components.decompiler.model

data class FileTreeNode(
    val name: String,
    val fullPath: String = "",
    val isDirectory: Boolean = false,
    val isClassFile: Boolean = false,
    val isJarFile: Boolean = false,
    val size: Long = 0L,
    val children: MutableList<FileTreeNode> = mutableListOf()
)