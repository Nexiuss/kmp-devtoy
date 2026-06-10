package com.nexius.devtoy.components.textcompare.model

data class CharDiffPart(
    val text: String,
    val type: DiffLineType
)

data class DiffLine(
    val lineNo: Int,
    val sourceContent: String?,
    val targetContent: String?,
    val lineType: DiffLineType,
    val charDiffParts: List<CharDiffPart> = emptyList()
)