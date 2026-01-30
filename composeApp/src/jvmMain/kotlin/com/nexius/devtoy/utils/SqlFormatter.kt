package com.nexius.devtoy.utils

import java.awt.Container
import java.util.*
import java.util.function.IntPredicate
import kotlin.math.max

/**
 * SQL 格式化工具类
 * 参考 Druid SQLFormatter 实现，提供专业的 SQL 美化和压缩功能
 * 
 * @author laker
 */
object SqlFormatter {
    // SQL 关键字集合
    var KEYWORDS: MutableSet<String?> = HashSet<String?>()
    var LOGIC_OPERATORS: MutableSet<String?> = HashSet<String?>()
    var FUNCTIONS: MutableSet<String?> = HashSet<String?>()
    var CASE_KEYWORDS: MutableSet<String?> = HashSet<String?>()

    init {
        // DML 关键字
        KEYWORDS.addAll(
            mutableListOf<String?>(
                "SELECT", "FROM", "WHERE", "INSERT", "INTO", "VALUES", "UPDATE", "SET",
                "DELETE", "MERGE", "TRUNCATE"
            )
        )

        // DDL 关键字
        KEYWORDS.addAll(
            mutableListOf<String?>(
                "CREATE", "ALTER", "DROP", "TABLE", "INDEX", "VIEW", "DATABASE", "SCHEMA",
                "CONSTRAINT", "PRIMARY", "FOREIGN", "KEY", "REFERENCES", "UNIQUE", "CHECK"
            )
        )

        // JOIN 关键字
        KEYWORDS.addAll(
            mutableListOf<String?>(
                "JOIN", "INNER", "LEFT", "RIGHT", "FULL", "OUTER", "CROSS", "ON", "USING"
            )
        )

        // 子句关键字（添加 DESC 和 ASC）
        KEYWORDS.addAll(
            mutableListOf<String?>(
                "GROUP", "BY", "HAVING", "ORDER", "LIMIT", "OFFSET", "UNION", "ALL",
                "INTERSECT", "EXCEPT", "MINUS", "DISTINCT", "AS", "WITH", "RECURSIVE",
                "DESC", "ASC"
            )
        )

        // 逻辑运算符
        LOGIC_OPERATORS.addAll(
            mutableListOf<String?>(
                "AND", "OR", "NOT", "IN", "EXISTS", "BETWEEN", "LIKE", "IS", "NULL"
            )
        )

        // CASE 表达式关键字
        CASE_KEYWORDS.addAll(
            mutableListOf<String?>(
                "CASE", "WHEN", "THEN", "ELSE", "END"
            )
        )

        // 聚合函数和其他函数（这些应该保持小写）
        FUNCTIONS.addAll(
            mutableListOf<String?>(
                "COUNT", "SUM", "AVG", "MAX", "MIN", "FIRST", "LAST",
                "UPPER", "LOWER", "SUBSTR", "CONCAT", "LENGTH", "TRIM",
                "NOW", "DATE", "TIME", "YEAR", "MONTH", "DAY"
            )
        )
    }

    /**
     * 格式化 SQL（自定义选项）
     */
    /**
     * 格式化 SQL（标准模式）
     */
    @JvmOverloads
    fun format(sql: String?, option: FormatOption = FormatOption()): String? {
        var sql = sql
        if (sql == null || sql.trim { it <= ' ' }.isEmpty()) {
            return sql
        }

        try {
            // 1. 预处理：移除多余空白，保留字符串和注释
            sql = preprocessSql(sql)

            // 2. 词法分析：分解为 token
            val tokens = tokenize(sql)

            // 3. 格式化：根据规则重新组织
            var formatted = formatTokens(tokens, option)

            // 4. 后处理：清理多余空行等
            formatted = postprocessSql(formatted)

            // 5. 添加分号
            if (option.addSemicolon && !formatted.trim { it <= ' ' }.endsWith(";")) {
                formatted = formatted.trim { it <= ' ' } + ";"
            }

            return formatted
        } catch (e: Exception) {
            println("SQL format error")
            return sql // 格式化失败时返回原始 SQL
        }
    }

    /**
     * 压缩 SQL（移除多余空白）
     */
    fun compress(sql: String?): String? {
        var sql = sql
        if (sql == null || sql.trim { it <= ' ' }.isEmpty()) {
            return sql
        }

        try {
            // 移除注释
            sql = removeComments(sql)

            // 移除多余空白（保留字符串内的空白）
            sql = compressWhitespace(sql)

            return sql.trim { it <= ' ' }
        } catch (e: Exception) {
            println("SQL compress error")
            return sql
        }
    }

    /**
     * 转换关键字大小写
     */
    fun convertKeywords(sql: String?, toUppercase: Boolean): String? {
        if (sql == null || sql.trim { it <= ' ' }.isEmpty()) {
            return sql
        }

        val tokens = tokenize(sql)
        val result = StringBuilder()

        for (token in tokens) {
            if (token.type == TokenType.KEYWORD) {
                result.append(
                    if (toUppercase) token.value.uppercase(Locale.getDefault()) else token.value.lowercase(
                        Locale.getDefault()
                    )
                )
            } else {
                result.append(token.value)
            }
            if (token.afterSpace) {
                result.append(" ")
            }
        }

        return result.toString()
    }

    // ==================== 内部方法 ====================
    /**
     * 预处理 SQL
     */
    private fun preprocessSql(sql: String): String {
        // 标准化换行符
        var sql = sql
        sql = sql.replace("\\r\\n".toRegex(), "\n").replace("\\r".toRegex(), "\n")

        // 移除多余空白（非字符串内）
        sql = sql.replace("[ \\t]+".toRegex(), " ")

        return sql.trim { it <= ' ' }
    }

    /**
     * 后处理 SQL
     */
    private fun postprocessSql(sql: String): String {
        // 移除多余空行
        var sql = sql
        sql = sql.replace("\\n{3,}".toRegex(), "\n\n")

        // 清理每行末尾的空格
        sql = sql.replace("[ \\t]+\\n".toRegex(), "\n")

        return sql.trim { it <= ' ' }
    }

    /**
     * 词法分析：将 SQL 分解为 token
     */
    private fun tokenize(sql: String): MutableList<Token> {
        val tokens: MutableList<Token> = ArrayList<Token>()
        var pos = 0
        val len = sql.length

        while (pos < len) {
            val ch = sql.get(pos)

            // 跳过空白
            if (Character.isWhitespace(ch)) {
                pos++
                continue
            }

            // 字符串字面量
            if (ch == '\'' || ch == '"') {
                tokens.add(parseString(sql, pos))
                pos = tokens.get(tokens.size - 1).endPos
                continue
            }

            // 单行注释
            if (ch == '-' && pos + 1 < len && sql.get(pos + 1) == '-') {
                tokens.add(parseLineComment(sql, pos))
                pos = tokens.get(tokens.size - 1).endPos
                continue
            }

            // 多行注释
            if (ch == '/' && pos + 1 < len && sql.get(pos + 1) == '*') {
                tokens.add(parseBlockComment(sql, pos))
                pos = tokens.get(tokens.size - 1).endPos
                continue
            }

            // 数字
            if (Character.isDigit(ch)) {
                tokens.add(parseNumber(sql, pos))
                pos = tokens.get(tokens.size - 1).endPos
                continue
            }

            // 标识符或关键字
            if (Character.isLetter(ch) || ch == '_') {
                tokens.add(parseIdentifier(sql, pos))
                pos = tokens.get(tokens.size - 1).endPos
                continue
            }

            // 操作符和特殊符号
            tokens.add(parseOperator(sql, pos))
            pos = tokens.get(tokens.size - 1).endPos
        }

        return tokens
    }

    /**
     * 格式化 token 列表（改进版，更符合 Druid 标准）
     */
    private fun formatTokens(tokens: MutableList<Token>, option: FormatOption): String {
        val result = StringBuilder()
        var indentLevel = 0
        var needIndent = false
        var parenLevel = 0 // 括号嵌套级别
        var inCaseExpression = false
        var caseLevel = 0

        for (i in tokens.indices) {
            val token = tokens.get(i)
            val prevToken = if (i > 0) tokens.get(i - 1) else null
            val nextToken = if (i < tokens.size - 1) tokens.get(i + 1) else null

            val upper = token.value.uppercase(Locale.getDefault())

            // 处理 CASE 表达式
            if ("CASE" == upper) {
                inCaseExpression = true
                caseLevel++
            }
            if ("END" == upper) {
                caseLevel--
                if (caseLevel == 0) {
                    inCaseExpression = false
                }
            }

            // 处理左括号
            if ("(" == token.value) {
                parenLevel++
                result.append(token.value)
                continue
            }

            // 处理右括号
            if (")" == token.value) {
                parenLevel--
                result.append(token.value)
                continue
            }

            // 决定是否需要换行
            val needLineBreak = needsLineBreakBefore(token, prevToken, option, inCaseExpression, parenLevel)

            if (needLineBreak) {
                result.append("\n")
                needIndent = true
            }

            // 添加缩进
            if (needIndent) {
                result.append(option.indent.repeat(indentLevel))
                needIndent = false
            }

            // 输出 token
            var value = token.value
            if (token.type == TokenType.KEYWORD && option.uppercaseKeywords) {
                value = value.uppercase(Locale.getDefault())
            } else if (token.type == TokenType.FUNCTION) {
                // 函数名保持小写
                value = value.lowercase(Locale.getDefault())
            }
            result.append(value)

            // 主要子句后增加缩进（Druid 风格）- 只在顶层
            if (token.type == TokenType.KEYWORD && parenLevel == 0) {
                if ("SELECT" == upper || "FROM" == upper ||
                    "WHERE" == upper || "SET" == upper || "DELETE" == upper
                ) {
                    indentLevel++
                }
            }

            // 决定是否需要添加空格
            if (needsSpaceAfter(token, nextToken, option)) {
                result.append(" ")
            }

            // 逗号后换行并保持缩进
            if (needsLineBreakAfter(token, nextToken, option, parenLevel)) {
                result.append("\n")
                needIndent = true
            }

            // 某些关键字后恢复缩进（Druid 风格）- 只在顶层
            if (token.type == TokenType.KEYWORD && parenLevel == 0) {
                if (nextToken != null && needsLineBreakBefore(nextToken, token, option, inCaseExpression, parenLevel)) {
                    indentLevel = max(0, indentLevel - 1)
                }
            }
        }

        return result.toString()
    }

    /**
     * 判断 token 前是否需要换行
     */
    private fun needsLineBreakBefore(
        token: Token,
        prevToken: Token?,
        option: FormatOption,
        inCaseExpression: Boolean,
        parenLevel: Int
    ): Boolean {
        if (prevToken == null) return false

        val upper = token.value.uppercase(Locale.getDefault())
        val prevUpper = prevToken.value.uppercase(Locale.getDefault())

        // CASE 表达式内部换行规则
        if (inCaseExpression) {
            if ("CASE" == upper) return true
            if ("WHEN" == upper) return true
            if ("ELSE" == upper) return true
            if ("END" == upper) return true
        }

        // BETWEEN ... AND 不应该在 AND 前换行
        if ("AND" == upper && "BETWEEN" == prevUpper) {
            return false
        }

        // 如果前一个词是 LEFT/RIGHT/FULL/INNER/OUTER/CROSS，当前词是 JOIN，不换行
        if ("JOIN" == upper) {
            if ("LEFT" == prevUpper || "RIGHT" == prevUpper ||
                "FULL" == prevUpper || "INNER" == prevUpper ||
                "OUTER" == prevUpper || "CROSS" == prevUpper
            ) {
                return false // 保持 LEFT JOIN 在同一行
            }
        }

        // 主要子句前换行 (只在顶层，不在子查询中)
        if (parenLevel == 0) {
            if (option.lineBreakBeforeFrom && "FROM" == upper) return true

            // JOIN 前换行（但要排除上面的组合情况）
            if (option.lineBreakBeforeJoin && "JOIN" == upper) return true

            // LEFT/RIGHT/FULL/INNER 等修饰词前换行（它们通常在 JOIN 前）
            if (option.lineBreakBeforeJoin &&
                ("LEFT" == upper || "RIGHT" == upper ||
                        "FULL" == upper || "INNER" == upper)
            ) {
                return true
            }

            if (option.lineBreakBeforeWhere && "WHERE" == upper) return true
            if ("GROUP" == upper || "ORDER" == upper || "HAVING" == upper) return true
            if ("LIMIT" == upper || "OFFSET" == upper) return true

            // UNION/UNION ALL 前换行
            if ("UNION" == upper || "INTERSECT" == upper || "EXCEPT" == upper) return true

            // SET 子句换行 (UPDATE ... SET)
            if ("SET" == upper) return true

            // VALUES 子句换行 (INSERT INTO ... VALUES)
            if ("VALUES" == upper) return true

            // AND/OR 前换行
            if (option.lineBreakBeforeAnd && "AND" == upper) return true
            if (option.lineBreakBeforeOr && "OR" == upper) return true
        }

        return false
    }

    /**
     * 判断 token 后是否需要换行
     */
    private fun needsLineBreakAfter(token: Token, nextToken: Token?, option: FormatOption, parenLevel: Int): Boolean {
        if (nextToken == null) return false

        // 逗号后换行（在 SELECT 列表等，但不在子查询或函数参数中）
        if (option.lineBreakAfterComma && "," == token.value) {
            val nextUpper = nextToken.value.uppercase(Locale.getDefault())
            // 不在特定关键字前换行，也不在括号内换行
            return parenLevel == 0 && !("FROM" == nextUpper || "WHERE" == nextUpper || ")" == nextToken.value)
        }

        return false
    }

    /**
     * 判断 token 后是否需要空格
     */
    private fun needsSpaceAfter(token: Token, nextToken: Token?, option: FormatOption): Boolean {
        if (nextToken == null) return false

        // 下一个token是逗号或右括号，不需要空格
        if ("," == nextToken.value || ")" == nextToken.value) {
            return false
        }

        // 函数后面如果是左括号，不加空格
        if (token.type == TokenType.FUNCTION) {
            return "(" != nextToken.value
        }

        // 关键字后需要空格（除非下一个是操作符）
        if (token.type == TokenType.KEYWORD) {
            // 特殊关键字后不需要空格的情况
            if (nextToken.type == TokenType.OPERATOR) {
                // 左括号前不需要空格（例如 IN(subquery)）
                return "(" != nextToken.value
            }
            return true
        }

        // 标识符后需要空格
        if (token.type == TokenType.IDENTIFIER) {
            if (nextToken.type == TokenType.OPERATOR) {
                // 操作符前需要空格，除了左括号和点号
                return "(" != nextToken.value && "." != nextToken.value
            }
            // 其他情况需要空格（除非是逗号或括号，已在前面处理）
            return true
        }

        // 操作符后需要空格
        if (token.type == TokenType.OPERATOR) {
            val op = token.value

            // 这些操作符后面不需要空格
            if ("(" == op || "." == op) {
                return false
            }

            // 逗号后如果换行了就不需要空格
            if ("," == op && option.lineBreakAfterComma) {
                return false
            }

            // 其他操作符后需要空格（比如 =, >, <, >=, <=, !=）
            return true
        }

        // 数字和字符串后，如果下一个是操作符或关键字，需要空格
        if (token.type == TokenType.NUMBER || token.type == TokenType.STRING) {
            if (nextToken.type == TokenType.OPERATOR) {
                // 右括号和逗号前不需要空格（已在开头处理）
                return true
            }
            return nextToken.type == TokenType.KEYWORD
        }

        return false
    }


    // ==================== Token 解析方法 ====================
    private fun parseString(sql: String, start: Int): Token {
        val quote = sql.get(start)
        var pos = start + 1
        val value = StringBuilder().append(quote)

        while (pos < sql.length) {
            val ch = sql.get(pos)
            value.append(ch)
            if (ch == quote) {
                // 检查是否是转义的引号
                if (pos + 1 < sql.length && sql.get(pos + 1) == quote) {
                    value.append(quote)
                    pos += 2
                    continue
                }
                pos++
                break
            }
            pos++
        }

        return Token(TokenType.STRING, value.toString(), start, pos)
    }

    private fun parseLineComment(sql: String, start: Int): Token {
        var pos = start + 2
        while (pos < sql.length && sql.get(pos) != '\n') {
            pos++
        }
        return Token(TokenType.COMMENT, sql.substring(start, pos), start, pos)
    }

    private fun parseBlockComment(sql: String, start: Int): Token {
        var pos = start + 2
        while (pos < sql.length - 1) {
            if (sql.get(pos) == '*' && sql.get(pos + 1) == '/') {
                pos += 2
                break
            }
            pos++
        }
        return Token(TokenType.COMMENT, sql.substring(start, pos), start, pos)
    }

    private fun parseNumber(sql: String, start: Int): Token {
        var pos = start
        while (pos < sql.length && (Character.isDigit(sql.get(pos)) || sql.get(pos) == '.')) {
            pos++
        }
        return Token(TokenType.NUMBER, sql.substring(start, pos), start, pos)
    }

    private fun parseIdentifier(sql: String, start: Int): Token {
        var pos = start
        while (pos < sql.length) {
            val ch = sql.get(pos)
            if (!Character.isLetterOrDigit(ch) && ch != '_') {
                break
            }
            pos++
        }

        val value = sql.substring(start, pos)
        val upper = value.uppercase(Locale.getDefault())

        // 判断类型：函数、关键字还是标识符
        var type = TokenType.IDENTIFIER
        if (FUNCTIONS.contains(upper)) {
            type = TokenType.FUNCTION // 函数单独标记
        } else if (KEYWORDS.contains(upper) || LOGIC_OPERATORS.contains(upper) || CASE_KEYWORDS.contains(upper)) {
            type = TokenType.KEYWORD
        }

        return Token(type, value, start, pos)
    }

    private fun parseOperator(sql: String, start: Int): Token {
        val ch = sql.get(start)
        var pos = start + 1

        // 处理多字符操作符
        if (pos < sql.length) {
            val nextCh = sql.get(pos)
            // >=, <=, !=, <>, ||, &&
            if ((ch == '>' || ch == '<' || ch == '!' || ch == '|' || ch == '&') &&
                (nextCh == '=' || nextCh == '>' || (ch == '<' && nextCh == '>') ||
                        (ch == '|' && nextCh == '|') || (ch == '&' && nextCh == '&'))
            ) {
                pos++
            }
        }

        val value = sql.substring(start, pos)
        return Token(TokenType.OPERATOR, value, start, pos)
    }

    /**
     * 移除注释
     */
    private fun removeComments(sql: String): String {
        // 移除单行注释
        var sql = sql
        sql = sql.replace("--[^\n]*".toRegex(), "")
        // 移除多行注释
        sql = sql.replace("/\\*.*?\\*/".toRegex(), "")
        return sql
    }

    /**
     * 压缩空白字符
     */
    private fun compressWhitespace(sql: String): String {
        val result = StringBuilder()
        var inString = false
        var stringChar = 0.toChar()
        var lastWasSpace = false

        for (i in 0..<sql.length) {
            val ch = sql.get(i)

            // 处理字符串
            if ((ch == '\'' || ch == '"') && (i == 0 || sql.get(i - 1) != '\\')) {
                if (!inString) {
                    inString = true
                    stringChar = ch
                } else if (ch == stringChar) {
                    inString = false
                }
                result.append(ch)
                lastWasSpace = false
                continue
            }

            // 字符串内保留所有字符
            if (inString) {
                result.append(ch)
                continue
            }

            // 压缩空白
            if (Character.isWhitespace(ch)) {
                if (!lastWasSpace) {
                    result.append(' ')
                    lastWasSpace = true
                }
            } else {
                // 移除逗号和分号前的空格
                if ((ch == ',' || ch == ';') && lastWasSpace && result.length > 0) {
                    result.setLength(result.length - 1) // 删除最后的空格
                }
                result.append(ch)
                lastWasSpace = false
            }
        }

        return result.toString()
    }

    /**
     * 格式化选项
     */
    public class FormatOption {
        var indent = "  " // 缩进字符串
        var uppercaseKeywords = true // 关键字大写
        var addSemicolon = true // 添加分号
        var lineBreakBeforeFrom = true
        var lineBreakBeforeJoin = true
        val lineBreakBeforeWhere = true
        var lineBreakBeforeAnd = true
        var lineBreakBeforeOr = true
        var lineBreakAfterComma = true

        fun setIndent(spaces: Int): FormatOption {
            this.indent = " ".repeat(max(0, spaces))
            return this
        }

        fun setUppercaseKeywords(uppercase: Boolean): FormatOption {
            this.uppercaseKeywords = uppercase
            return this
        }

        fun setAddSemicolon(add: Boolean): FormatOption {
            this.addSemicolon = add
            return this
        }

        fun setLineBreakBeforeAnd(lineBreak: Boolean): FormatOption {
            this.lineBreakBeforeAnd = lineBreak
            return this
        }

        fun setLineBreakBeforeOr(lineBreak: Boolean): FormatOption {
            this.lineBreakBeforeOr = lineBreak
            return this
        }

        fun setLineBreakAfterComma(lineBreak: Boolean): FormatOption {
            this.lineBreakAfterComma = lineBreak
            return this
        }
    }

    // ==================== Token 类 ====================
    private enum class TokenType {
        KEYWORD, IDENTIFIER, STRING, NUMBER, OPERATOR, COMMENT, FUNCTION
    }

    private class Token(var type: TokenType?, var value: String, var startPos: Int, var endPos: Int) {
        var afterSpace: Boolean = false
    }

    /**
     * 验证SQL - 基本的语法检查
     */
    fun validateSql(input: String): String {
        if (input.isEmpty()) {
            return "⚠\uFE0F 输入为空";
        }

        try {
            val issues: MutableList<String?> = ArrayList<String?>()

            // 基本语法检查
            val chars = input.length
            val lines = input.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size
            val statements = input.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size

            // 检查括号匹配
            val openParen: Int = countOccurrences(input, '(')
            val closeParen: Int = countOccurrences(input, ')')
            if (openParen != closeParen) {
                issues.add("括号不匹配 - 左括号：$openParen，右括号：$closeParen")
            }

            // 检查引号匹配
            val singleQuotes: Int = countOccurrences(input, '\'')
            if (singleQuotes % 2 != 0) {
                issues.add("引号不匹配 - 发现奇数个单引号")
            }

            // 检查常见SQL关键字
            val hasSelect: Boolean = containsIgnoreCase(input, "SELECT")
            val hasInsert: Boolean = containsIgnoreCase(input, "INSERT")
            val hasUpdate: Boolean = containsIgnoreCase(input, "UPDATE")
            val hasDelete: Boolean = containsIgnoreCase(input, "DELETE")
            val hasCreate: Boolean = containsIgnoreCase(input, "CREATE")

            val hasValidKeyword = hasSelect || hasInsert || hasUpdate || hasDelete || hasCreate

            val info = java.lang.StringBuilder()
            if (issues.isEmpty() && hasValidKeyword) {
                info.append("✓ ").append("SQL 语法有效").append("\n\n")
            } else if (!hasValidKeyword) {
                info.append("⚠ ").append("未发现有效的 SQL 关键字")
                    .append("\n\n")
            } else {
                info.append("❌ ").append("发现语法问题").append("\n\n")
            }

            info.append("字符数").append(": ").append(chars)
                .append("\n")
            info.append("行数").append(": ").append(lines)
                .append("\n")
            info.append("语句数").append(": ")
                .append(statements).append("\n")

            return info.toString()
        } catch (ex: java.lang.Exception) {
            return "❌ SQL格式错误\n\n$ex.message"
        }
    }

    /**
     * 统计字符出现次数
     */
    private fun countOccurrences(str: String, ch: Char): Int {
        return str.chars().filter(IntPredicate { c: Int -> c == ch.code }).count().toInt()
    }

    /**
     * 忽略大小写检查包含
     */
    private fun containsIgnoreCase(str: String, search: String): Boolean {
        return str.lowercase(Locale.getDefault()).contains(search.lowercase(Locale.getDefault()))
    }
}

