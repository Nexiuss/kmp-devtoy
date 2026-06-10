package com.nexius.devtoy.components.textcompare.model

enum class DiffLineType {
    SAME,    // 内容一致
    ADD,     // 新增行
    DELETE,  // 删除行
    MODIFY   // 修改行
}