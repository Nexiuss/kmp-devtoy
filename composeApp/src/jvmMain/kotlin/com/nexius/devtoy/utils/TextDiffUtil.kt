package com.nexius.devtoy.utils

import com.nexius.devtoy.components.textcompare.model.CharDiffPart
import com.nexius.devtoy.components.textcompare.model.DiffLine
import com.nexius.devtoy.components.textcompare.model.DiffLineType

object TextDiffUtil {

    fun computeDiff(
        source: String,
        target: String,
        ignoreTrim: Boolean = false,
        ignoreEmptyLine: Boolean = false
    ): List<DiffLine> {
        var srcLines = source.lines()
        var tgtLines = target.lines()

        if (ignoreTrim) {
            srcLines = srcLines.map { it.trimEnd() }
            tgtLines = tgtLines.map { it.trimEnd() }
        }
        if (ignoreEmptyLine) {
            srcLines = srcLines.filter { it.isNotBlank() }
            tgtLines = tgtLines.filter { it.isNotBlank() }
        }

        val lcsPairs = getLcsIndexPairs(srcLines, tgtLines)
        val result = mutableListOf<DiffLine>()

        var i = 0
        var j = 0
        var lineNo = 1

        while (i < srcLines.size || j < tgtLines.size) {
            val match = lcsPairs.firstOrNull { it.first == i && it.second == j }
            if (match != null) {
                val sLine = srcLines[i]
                val tLine = tgtLines[j]
                val charParts = diffSingleLineChar(sLine, tLine)
                val lineType = if (charParts.all { it.type == DiffLineType.SAME }) DiffLineType.SAME else DiffLineType.MODIFY
                result.add(
                    DiffLine(
                        lineNo = lineNo++,
                        sourceContent = sLine,
                        targetContent = tLine,
                        lineType = lineType,
                        charDiffParts = charParts
                    )
                )
                i++
                j++
            } else {
                val hasSrc = i < srcLines.size
                val hasTgt = j < tgtLines.size
                when {
                    hasSrc && !hasTgt -> {
                        result.add(
                            DiffLine(
                                lineNo = lineNo++,
                                sourceContent = srcLines[i],
                                targetContent = null,
                                lineType = DiffLineType.DELETE,
                                charDiffParts = diffSingleLineChar(srcLines[i], "")
                            )
                        )
                        i++
                    }
                    !hasSrc && hasTgt -> {
                        result.add(
                            DiffLine(
                                lineNo = lineNo++,
                                sourceContent = null,
                                targetContent = tgtLines[j],
                                lineType = DiffLineType.ADD,
                                charDiffParts = diffSingleLineChar("", tgtLines[j])
                            )
                        )
                        j++
                    }
                    else -> {
                        val sLine = srcLines[i]
                        val tLine = tgtLines[j]
                        val charParts = diffSingleLineChar(sLine, tLine)
                        result.add(
                            DiffLine(
                                lineNo = lineNo++,
                                sourceContent = sLine,
                                targetContent = tLine,
                                lineType = DiffLineType.MODIFY,
                                charDiffParts = charParts
                            )
                        )
                        i++
                        j++
                    }
                }
            }
        }
        return result
    }

    private fun getLcsIndexPairs(a: List<String>, b: List<String>): List<Pair<Int, Int>> {
        val n = a.size
        val m = b.size
        val dp = Array(n + 1) { IntArray(m + 1) }

        for (i in 1..n) {
            for (j in 1..m) {
                dp[i][j] = if (a[i - 1] == b[j - 1]) dp[i - 1][j - 1] + 1 else maxOf(dp[i - 1][j], dp[i][j - 1])
            }
        }

        val pairs = mutableListOf<Pair<Int, Int>>()
        var i = n
        var j = m
        while (i > 0 && j > 0) {
            if (a[i - 1] == b[j - 1]) {
                pairs.add(i - 1 to j - 1)
                i--
                j--
            } else if (dp[i - 1][j] > dp[i][j - 1]) i-- else j--
        }
        return pairs.reversed()
    }

    // LCS单行字符级拆分，输出连续片段，支持插入、删除、不变
    private fun diffSingleLineChar(old: String, new: String): List<CharDiffPart> {
        val oldChars = old.toCharArray()
        val newChars = new.toCharArray()
        val n = oldChars.size
        val m = newChars.size

        val dp = Array(n + 1) { IntArray(m + 1) }
        for (i in 1..n) {
            for (j in 1..m) {
                dp[i][j] = if (oldChars[i - 1] == newChars[j - 1]) dp[i - 1][j - 1] + 1 else maxOf(dp[i - 1][j], dp[i][j - 1])
            }
        }

        val result = mutableListOf<CharDiffPart>()
        var i = n
        var j = m

        while (i > 0 || j > 0) {
            when {
                i > 0 && j > 0 && oldChars[i - 1] == newChars[j - 1] -> {
                    result.add(CharDiffPart(oldChars[i - 1].toString(), DiffLineType.SAME))
                    i--; j--
                }
                j > 0 && (i == 0 || dp[i][j - 1] >= dp[i - 1][j]) -> {
                    result.add(CharDiffPart(newChars[j - 1].toString(), DiffLineType.ADD))
                    j--
                }
                i > 0 -> {
                    result.add(CharDiffPart(oldChars[i - 1].toString(), DiffLineType.DELETE))
                    i--
                }
            }
        }
        return result.reversed()
    }

    fun calcStat(diffList: List<DiffLine>): String {
        var add = 0
        var del = 0
        var modify = 0
        var same = 0
        diffList.forEach {
            when (it.lineType) {
                DiffLineType.ADD -> add++
                DiffLineType.DELETE -> del++
                DiffLineType.MODIFY -> modify++
                DiffLineType.SAME -> same++
            }
        }
        return "比对完成：总行数${diffList.size}，新增${add}行，删除${del}行，修改${modify}行，完全一致${same}行"
    }
}