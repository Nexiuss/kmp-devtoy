package com.nexius.devtoy.components.filerename.model

import java.nio.file.Path
import java.util.UUID

// 待重命名的文件信息
data class RenameFile(
    val id: String = UUID.randomUUID().toString(),
    val originalPath: Path,          // 原文件路径
    var newName: String,             // 新文件名（含扩展名）
    val isValid: Boolean = true,     // 名称是否合法
    val errorMsg: String = ""        // 错误信息（如名称重复、含非法字符）
) {
    // 获取文件名（不含路径）
    val originalName: String get() = originalPath.fileName.toString()
    // 获取文件扩展名
    val extension: String get() = originalName.substringAfterLast(".", "")
    // 获取文件所在目录
    val parentDir: Path get() = originalPath.parent ?: Path.of(".")
}

// 重命名规则类型
enum class RenameRuleType {
    PREFIX,        // 前缀
    SUFFIX,        // 后缀
    REPLACE,       // 文本替换
    NUMBERING,     // 数字序号
    UPPER_CASE,    // 大写
    LOWER_CASE     // 小写
}

// 规则配置
data class RenameRule(
    val type: RenameRuleType,
    val value: String = "",          // 前缀/后缀/替换的文本
    val replaceTarget: String = "",  // 替换的目标文本（仅REPLACE规则用）
    val numberStart: Int = 1,        // 序号起始值（仅NUMBERING规则用）
    val numberPadding: Int = 2       // 序号补零位数（仅NUMBERING规则用）
)