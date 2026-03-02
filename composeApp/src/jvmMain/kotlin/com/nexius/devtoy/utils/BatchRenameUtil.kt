package com.nexius.devtoy.utils

import com.nexius.devtoy.components.filerename.model.RenameFile
import com.nexius.devtoy.components.filerename.model.RenameRule
import com.nexius.devtoy.components.filerename.model.RenameRuleType
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption

object BatchRenameUtil {
    // 系统非法字符（Windows/macOS/Linux通用）
    private val ILLEGAL_CHARS = setOf('/', '\\', ':', '*', '?', '"', '<', '>', '|')
    private const val MAX_FILENAME_LENGTH = 255

    /**
     * 校验文件名合法性
     */
    fun isFileNameValid(name: String): Pair<Boolean, String> {
        // 空名称
        if (name.isBlank()) return Pair(false, "文件名不能为空")
        // 长度超限
        if (name.length > MAX_FILENAME_LENGTH) return Pair(false, "文件名长度超过255字符")
        // 含非法字符
        if (name.any { it in ILLEGAL_CHARS }) return Pair(false, "包含非法字符: ${ILLEGAL_CHARS.joinToString("")}")
        // 合法
        return Pair(true, "")
    }

    /**
     * 应用重命名规则，生成预览结果
     */
    fun applyRules(files: List<Path>, rules: List<RenameRule>): List<RenameFile> {
        val renameFiles = files.map { path ->
            RenameFile(
                originalPath = path,
                newName = path.fileName.toString()
            )
        }.toMutableList()

        // 按顺序应用每个规则
        rules.forEach { rule ->
            when (rule.type) {
                RenameRuleType.PREFIX -> addPrefix(renameFiles, rule.value)
                RenameRuleType.SUFFIX -> addSuffix(renameFiles, rule.value)
                RenameRuleType.REPLACE -> replaceText(renameFiles, rule.replaceTarget, rule.value)
                RenameRuleType.NUMBERING -> addNumbering(renameFiles, rule.numberStart, rule.numberPadding)
                RenameRuleType.UPPER_CASE -> changeCase(renameFiles, toUpper = true)
                RenameRuleType.LOWER_CASE -> changeCase(renameFiles, toUpper = false)
            }
        }

        // 校验合法性+重复检查
        return validateRenameFiles(renameFiles)
    }

    /**
     * 执行实际重命名操作
     */
    fun executeRename(renameFiles: List<RenameFile>): Pair<Int, Int> {
        var success = 0
        var fail = 0

        renameFiles.filter { it.isValid }.forEach { file ->
            val newPath = file.parentDir.resolve(file.newName)
            try {
                // 移动/重命名文件（覆盖已存在的文件）
                Files.move(
                    file.originalPath,
                    newPath,
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE
                )
                success++
            } catch (e: Exception) {
                fail++
                println("重命名失败 ${file.originalName} → ${file.newName}: ${e.message}")
            }
        }

        return Pair(success, fail)
    }

    // --- 规则实现 ---
    private fun addPrefix(files: MutableList<RenameFile>, prefix: String) {
        files.forEach { it.newName = prefix + it.newName }
    }

    private fun addSuffix(files: MutableList<RenameFile>, suffix: String) {
        files.forEach { file ->
            val nameWithoutExt = file.newName.substringBeforeLast(".", "")
            val ext = file.newName.substringAfterLast(".", "")
            file.newName = if (ext.isBlank()) {
                nameWithoutExt + suffix
            } else {
                "$nameWithoutExt$suffix.$ext"
            }
        }
    }

    private fun replaceText(files: MutableList<RenameFile>, target: String, replacement: String) {
        if (target.isBlank()) return
        files.forEach { it.newName = it.newName.replace(target, replacement) }
    }

    private fun addNumbering(files: MutableList<RenameFile>, start: Int, padding: Int) {
        files.forEachIndexed { index, file ->
            val number = (start + index).toString().padStart(padding, '0')
            val nameWithoutExt = file.newName.substringBeforeLast(".", "")
            val ext = file.newName.substringAfterLast(".", "")
            file.newName = if (ext.isBlank()) {
                "${nameWithoutExt}_$number"
            } else {
                "${nameWithoutExt}_$number.$ext"
            }
        }
    }

    private fun changeCase(files: MutableList<RenameFile>, toUpper: Boolean) {
        files.forEach {
            it.newName = if (toUpper) it.newName.uppercase() else it.newName.lowercase()
        }
    }

    // --- 校验重复和合法性 ---
    private fun validateRenameFiles(files: List<RenameFile>): List<RenameFile> {
        val nameCount = mutableMapOf<String, Int>()
        // 统计名称出现次数
        files.forEach {
            nameCount[it.newName] = nameCount.getOrDefault(it.newName, 0) + 1
        }

        return files.map { file ->
            val (isValid, errorMsg) = isFileNameValid(file.newName)
            // 重复名称校验
            val finalErrorMsg = if (isValid && nameCount[file.newName] ?: 0 > 1) {
                "名称重复"
            } else if (!isValid) {
                errorMsg
            } else {
                ""
            }

            file.copy(
                isValid = isValid && (nameCount[file.newName] ?: 0) == 1,
                errorMsg = finalErrorMsg
            )
        }
    }
}