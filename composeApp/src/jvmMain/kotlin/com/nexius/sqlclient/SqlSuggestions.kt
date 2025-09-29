package com.nexius.sqlclient

// 生成SQL提示（优化版）
internal fun generateSuggestions(
    currentWord: String, 
    metadata: DatabaseMetadata,
    sqlQuery: String = "" // 可选参数，用于上下文分析
): List<String> {
    val lowerWord = currentWord.lowercase()
    val suggestions = mutableListOf<SuggestionItem>()

    // 1. 分析SQL上下文，确定当前可能需要的提示类型
    val contextType = determineContextType(sqlQuery)

    // 2. 按上下文优先级添加不同类型的提示
    when (contextType) {
        ContextType.COLUMN -> {
            // 在需要列名的上下文（如SELECT后、WHERE后）优先显示列提示
            addColumnSuggestions(lowerWord, metadata, suggestions)
            addKeywordSuggestions(lowerWord, suggestions, columnRelatedKeywords)
            addFunctionSuggestions(lowerWord, suggestions)
        }
        ContextType.TABLE -> {
            // 在需要表名的上下文（如FROM后、JOIN后）优先显示表提示
            addTableSuggestions(lowerWord, metadata, suggestions)
            addKeywordSuggestions(lowerWord, suggestions, tableRelatedKeywords)
        }
        ContextType.FUNCTION -> {
            // 在可能需要函数的上下文（如SELECT后、聚合函数场景）优先显示函数提示
            addFunctionSuggestions(lowerWord, suggestions)
            addColumnSuggestions(lowerWord, metadata, suggestions)
            addKeywordSuggestions(lowerWord, suggestions, functionRelatedKeywords)
        }
        else -> {
            // 通用上下文，按关键字>函数>表>列的顺序
            addKeywordSuggestions(lowerWord, suggestions)
            addFunctionSuggestions(lowerWord, suggestions)
            addTableSuggestions(lowerWord, metadata, suggestions)
            addColumnSuggestions(lowerWord, metadata, suggestions)
        }
    }

    // 3. 过滤重复项并按综合评分排序
    return suggestions
        .distinctBy { it.text.lowercase() } // 去重（不区分大小写）
        .sortedWith(compareBy(
            { -it.priority }, // 优先级高的在前
            { it.matchQuality }, // 匹配质量高的在前
            { it.text.length }, // 短文本在前
            { it.text } // 按字母顺序
        ))
        .map { it.text }
        .take(15) // 限制最多15个提示，避免过多
}

// 提示项数据类（包含排序所需的元数据）
private data class SuggestionItem(
    val text: String,
    val priority: Int, // 优先级：10（高）-1（低）
    val matchQuality: Int // 匹配质量：10（完全匹配）-1（部分匹配）
)

// SQL上下文类型枚举
private enum class ContextType {
    TABLE,      // 需要表名的上下文（如FROM后）
    COLUMN,     // 需要列名的上下文（如SELECT后）
    FUNCTION,   // 需要函数的上下文（如聚合场景）
    GENERAL     // 通用上下文
}

// 分析SQL上下文，确定当前需要的提示类型
private fun determineContextType(sqlQuery: String): ContextType {
    val lowerQuery = sqlQuery.lowercase().trimEnd()
    
    // 检查是否在需要表名的位置（FROM/JOIN后）
    if (lowerQuery.matches(Regex(".*\\b(from|join)\\s+[^;]*$"))) {
        return ContextType.TABLE
    }
    
    // 检查是否在需要列名的位置（SELECT/WHERE后）
    if (lowerQuery.matches(Regex(".*\\b(select|where|set|having)\\s+[^;]*$")) ||
        lowerQuery.contains("=") || lowerQuery.contains(">") || lowerQuery.contains("<")) {
        return ContextType.COLUMN
    }
    
    // 检查是否在可能需要函数的位置（聚合函数场景）
    if (lowerQuery.matches(Regex(".*\\b(group by|order by)\\s+[^;]*$")) ||
        lowerQuery.matches(Regex(".*\\b(select)\\s+.*,\\s*[^;]*$"))) {
        return ContextType.FUNCTION
    }
    
    return ContextType.GENERAL
}

// 添加表名提示
private fun addTableSuggestions(
    lowerWord: String,
    metadata: DatabaseMetadata,
    suggestions: MutableList<SuggestionItem>
) {
    metadata.tables.forEach { table ->
        val tableLower = table.name.lowercase()
        if (tableLower.startsWith(lowerWord) || lowerWord in tableLower) {
            // 计算匹配质量：前缀匹配 > 包含匹配
            val matchQuality = if (tableLower.startsWith(lowerWord)) 8 else 5
            // 表名优先级：有主键的表 > 普通表
            val priority = if (table.primaryKey != null) 7 else 6
            suggestions.add(SuggestionItem(table.name, priority, matchQuality))
        }
    }
}

// 添加列名提示
private fun addColumnSuggestions(
    lowerWord: String,
    metadata: DatabaseMetadata,
    suggestions: MutableList<SuggestionItem>
) {
    metadata.tables.forEach { table ->
        table.columns.forEach { column ->
            val columnLower = column.name.lowercase()
            // 支持两种提示格式："column" 和 "table.column"
            val baseMatch = columnLower.startsWith(lowerWord) || lowerWord in columnLower
            
            if (baseMatch) {
                val matchQuality = if (columnLower.startsWith(lowerWord)) 9 else 6
                // 列优先级：主键 > 非空列 > 普通列
                val priority = when {
                    column.isPrimaryKey -> 9
                    !column.isNullable -> 8
                    else -> 7
                }
                
                // 添加纯列名提示
                suggestions.add(SuggestionItem(column.name, priority, matchQuality))
                
                // 添加带表名的列提示（如 "users.id"）
                val qualifiedName = "${table.name}.${column.name}"
                suggestions.add(SuggestionItem(qualifiedName, priority - 1, matchQuality))
            }
        }
    }
}

// 添加SQL函数提示
private fun addFunctionSuggestions(
    lowerWord: String,
    suggestions: MutableList<SuggestionItem>
) {
    SQL_FUNCTIONS.forEach { function ->
        val functionLower = function.lowercase()
        if (functionLower.startsWith(lowerWord) || lowerWord in functionLower) {
            val matchQuality = if (functionLower.startsWith(lowerWord)) 8 else 5
            // 常用函数优先级更高
            val priority = if (isCommonFunction(function)) 8 else 7
            suggestions.add(SuggestionItem("$function()", priority, matchQuality))
        }
    }
}

// 添加SQL关键字提示
private fun addKeywordSuggestions(
    lowerWord: String,
    suggestions: MutableList<SuggestionItem>,
    keywordSubset: Set<String> = SQL_KEYWORDS
) {
    keywordSubset.forEach { keyword ->
        val keywordLower = keyword.lowercase()
        if (keywordLower.startsWith(lowerWord) || lowerWord in keywordLower) {
            val matchQuality = if (keywordLower.startsWith(lowerWord)) 7 else 4
            // 关键字优先级：上下文相关 > 通用关键字
            val priority = if (keyword in contextSensitiveKeywords) 6 else 5
            suggestions.add(SuggestionItem(keyword, priority, matchQuality))
        }
    }
}

// 常用函数列表（优先级更高）
private val commonFunctions = setOf(
    "COUNT", "SUM", "AVG", "MIN", "MAX", "SELECT", "FROM", "WHERE"
)

private fun isCommonFunction(function: String) = function in commonFunctions

// 上下文敏感关键字（优先级更高）
private val contextSensitiveKeywords = setOf(
    "SELECT", "FROM", "WHERE", "JOIN", "ON", "AS", "GROUP", "BY", "ORDER", "LIMIT"
)

// 与表相关的关键字
private val tableRelatedKeywords = setOf(
    "FROM", "JOIN", "INNER", "LEFT", "RIGHT", "FULL", "ON", "AS", "USING"
)

// 与列相关的关键字
private val columnRelatedKeywords = setOf(
    "WHERE", "AND", "OR", "NOT", "IN", "LIKE", "BETWEEN", "IS", "NULL", "ASC", "DESC"
)

// 与函数相关的关键字
private val functionRelatedKeywords = setOf(
    "COUNT", "SUM", "AVG", "MIN", "MAX", "DISTINCT", "AS", "GROUP", "BY", "HAVING"
)

// 原有的SQL关键字和函数定义（保持不变）
private val SQL_KEYWORDS = setOf(
    "SELECT", "FROM", "WHERE", "AND", "OR", "NOT", "INSERT", "UPDATE", "DELETE",
    "CREATE", "ALTER", "DROP", "TABLE", "DATABASE", "INDEX", "PRIMARY", "KEY",
    "FOREIGN", "REFERENCES", "IN", "LIKE", "BETWEEN", "NULL", "IS", "JOIN",
    "INNER", "LEFT", "RIGHT", "FULL", "ON", "AS", "GROUP", "BY", "HAVING",
    "ORDER", "ASC", "DESC", "LIMIT", "OFFSET", "VALUES", "SET", "DISTINCT",
    "ALL", "ANY", "UNION", "EXCEPT", "INTERSECT", "CASE", "WHEN", "THEN", "ELSE", "END"
)

private val SQL_FUNCTIONS = setOf(
    "COUNT", "SUM", "AVG", "MIN", "MAX", "DISTINCT", "UPPER", "LOWER",
    "SUBSTRING", "CONCAT", "CAST", "DATE", "NOW", "CURRENT_DATE", "CURRENT_TIME",
    "LENGTH", "TRIM", "ROUND", "FLOOR", "CEIL", "COALESCE", "NULLIF"
)
