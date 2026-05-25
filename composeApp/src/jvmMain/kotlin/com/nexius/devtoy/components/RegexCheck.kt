package com.nexius.devtoy.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexius.devtoy.components.Icons.Cancel
import com.nexius.devtoy.components.Icons.CheckCircle
import com.nexius.devtoy.components.Icons.ContentCopy
import com.nexius.devtoy.components.Icons.Search
import com.nexius.devtoy.components.Icons.FindInPage
import com.nexius.devtoy.components.Icons.KeyboardArrowDown
import com.nexius.devtoy.components.Icons.KeyboardArrowUp
import com.nexius.devtoy.components.Icons.PlayArrow
import com.nexius.devtoy.theme.AppColors

/**
 * 正则公式速查工具组件
 */
@Composable
fun RegexCheck(
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<RegexCategory?>(RegexCategory.VALIDATION) }
    var selectedRegex by remember { mutableStateOf<RegexItem?>(null) }
    var selectedLanguage by remember { mutableStateOf(ProgrammingLanguage.JAVA) }
    var testInput by remember { mutableStateOf("") }
    var showLanguageDropdown by remember { mutableStateOf(false) }
    var testResult by remember { mutableStateOf<TestResult?>(null) }
    
    Row(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.Gray50)
    ) {
        // 左侧边栏
        LeftSidebar(
            searchQuery = searchQuery,
            onSearchQueryChange = { 
                searchQuery = it
                // 当搜索时，如果有内容则显示所有分类的结果
                if (it.isNotEmpty()) {
                    selectedCategory = null
                }
            },
            selectedCategory = selectedCategory,
            onCategoryChange = { 
                selectedCategory = it
                searchQuery = "" // 切换分类时清空搜索
            },
            selectedRegex = selectedRegex,
            onRegexSelect = { selectedRegex = it },
            modifier = Modifier.width(320.dp).fillMaxHeight()
        )
        
        // 右侧详情面板
        RightPanel(
            selectedRegex = selectedRegex,
            selectedLanguage = selectedLanguage,
            onLanguageChange = { selectedLanguage = it },
            showLanguageDropdown = showLanguageDropdown,
            onShowLanguageDropdownChange = { showLanguageDropdown = it },
            testInput = testInput,
            onTestInputChange = { testInput = it },
            testResult = testResult,
            onTestResultChange = { testResult = it },
            modifier = Modifier.weight(1f).fillMaxHeight()
        )
    }
}

/**
 * 左侧边栏
 */
@Composable
private fun LeftSidebar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    selectedCategory: RegexCategory?,
    onCategoryChange: (RegexCategory) -> Unit,
    selectedRegex: RegexItem?,
    onRegexSelect: (RegexItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 标题
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = FindInPage,
                contentDescription = null,
                tint = AppColors.Primary,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "正则速查",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.TextPrimary
            )
        }
        
        // 搜索框
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            placeholder = { Text("搜索正则表达式...", fontSize = 14.sp) },
            leadingIcon = {
                Icon(
                    imageVector = Search,
                    contentDescription = null,
                    tint = AppColors.TextSecondary
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AppColors.Primary,
                unfocusedBorderColor = AppColors.BorderLight
            ),
            singleLine = true
        )
        
        // 分类标签
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RegexCategory.entries.forEach { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { onCategoryChange(category) },
                    label = {
                        Text(
                            text = category.displayName,
                            fontSize = 13.sp
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = AppColors.Primary,
                        selectedLabelColor = Color.White
                    )
                )
            }
        }
        
        // 正则列表
        val filteredRegexList = remember(selectedCategory, searchQuery) {
            if (searchQuery.isEmpty()) {
                // 没有搜索内容时，显示选中分类的内容
                selectedCategory?.let { getRegexListByCategory(it) } ?: emptyList()
            } else {
                // 有搜索内容时，在所有分类中搜索
                RegexCategory.entries.flatMap { category ->
                    getRegexListByCategory(category)
                }.filter {
                    it.name.contains(searchQuery, ignoreCase = true) ||
                    it.description.contains(searchQuery, ignoreCase = true)
                }
            }
        }
        
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredRegexList) { regex ->
                RegexListItem(
                    regex = regex,
                    isSelected = selectedRegex == regex,
                    onClick = { onRegexSelect(regex) }
                )
            }
        }
    }
}

/**
 * 正则列表项
 */
@Composable
private fun RegexListItem(
    regex: RegexItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = if (isSelected) AppColors.Blue50 else AppColors.Gray50
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = regex.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = AppColors.TextPrimary
            )
            Text(
                text = regex.description,
                fontSize = 12.sp,
                color = AppColors.TextSecondary
            )
        }
    }
}

/**
 * 右侧详情面板
 */
@Composable
private fun RightPanel(
    selectedRegex: RegexItem?,
    selectedLanguage: ProgrammingLanguage,
    onLanguageChange: (ProgrammingLanguage) -> Unit,
    showLanguageDropdown: Boolean,
    onShowLanguageDropdownChange: (Boolean) -> Unit,
    testInput: String,
    onTestInputChange: (String) -> Unit,
    testResult: TestResult?,
    onTestResultChange: (TestResult?) -> Unit,
    modifier: Modifier = Modifier
) {
    val clipboardManager = LocalClipboardManager.current
    
    if (selectedRegex == null) {
        // 空状态
        Box(
            modifier = modifier
                .background(AppColors.Gray50)
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = FindInPage,
                    contentDescription = null,
                    tint = AppColors.TextSecondary,
                    modifier = Modifier.size(64.dp)
                )
                Text(
                    text = "选择一个正则表达式查看详情",
                    fontSize = 16.sp,
                    color = AppColors.TextSecondary
                )
            }
        }
    } else {
        Column(
            modifier = modifier
                .background(AppColors.Gray50)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // 标题和描述
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = selectedRegex.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )
                Text(
                    text = selectedRegex.description,
                    fontSize = 14.sp,
                    color = AppColors.TextSecondary
                )
            }
            
            // 语言选择和正则显示
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "正则表达式",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = AppColors.TextPrimary
                        )
                        
                        // 语言选择下拉框
                        Box {
                            Surface(
                                onClick = { onShowLanguageDropdownChange(!showLanguageDropdown) },
                                shape = RoundedCornerShape(8.dp),
                                color = AppColors.Blue50
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = selectedLanguage.displayName,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = AppColors.Primary
                                    )
                                    Icon(
                                        imageVector = if (showLanguageDropdown) KeyboardArrowUp else KeyboardArrowDown,
                                        contentDescription = null,
                                        tint = AppColors.Primary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                            
                            DropdownMenu(
                                expanded = showLanguageDropdown,
                                onDismissRequest = { onShowLanguageDropdownChange(false) }
                            ) {
                                ProgrammingLanguage.entries.forEach { language ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = language.displayName,
                                                fontSize = 14.sp
                                            )
                                        },
                                        onClick = {
                                            onLanguageChange(language)
                                            onShowLanguageDropdownChange(false)
                                        }
                                    )
                                }
                            }
                        }
                    }
                    
                    // 正则代码显示
                    val regexCode = selectedRegex.getCodeForLanguage(selectedLanguage)
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        color = AppColors.Gray900
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = regexCode,
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Monospace,
                                color = Color.White,
                                modifier = Modifier.weight(1f)
                            )
                            
                            IconButton(
                                onClick = {
                                    clipboardManager.setText(AnnotatedString(regexCode))
                                }
                            ) {
                                Icon(
                                    imageVector = ContentCopy,
                                    contentDescription = "复制",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            }
            
            // 示例
            if (selectedRegex.examples.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "示例",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = AppColors.TextPrimary
                        )
                        
                        selectedRegex.examples.forEach { example ->
                            ExampleItem(example)
                        }
                    }
                }
            }
            
            // 测试匹配
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "测试匹配",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AppColors.TextPrimary
                    )
                    
                    OutlinedTextField(
                        value = testInput,
                        onValueChange = onTestInputChange,
                        placeholder = { Text("输入测试文本...") },
                        modifier = Modifier.fillMaxWidth().height(120.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = AppColors.Primary,
                            unfocusedBorderColor = AppColors.BorderLight
                        )
                    )
                    
                    Button(
                        onClick = {
                            // 实现正则测试功能
                            if (testInput.isNotEmpty()) {
                                try {
                                    val regex = Regex(selectedRegex.pattern)
                                    val matches = regex.findAll(testInput).toList()
                                    onTestResultChange(
                                        TestResult(
                                            isSuccess = true,
                                            hasMatch = matches.isNotEmpty(),
                                            matchCount = matches.size,
                                            matches = matches.map { it.value }
                                        )
                                    )
                                } catch (e: Exception) {
                                    onTestResultChange(
                                        TestResult(
                                            isSuccess = false,
                                            hasMatch = false,
                                            matchCount = 0,
                                            matches = emptyList(),
                                            errorMessage = "正则表达式错误: ${e.message}"
                                        )
                                    )
                                }
                            } else {
                                onTestResultChange(
                                    TestResult(
                                        isSuccess = false,
                                        hasMatch = false,
                                        matchCount = 0,
                                        matches = emptyList(),
                                        errorMessage = "请输入测试文本"
                                    )
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColors.Primary
                        )
                    ) {
                        Icon(
                            imageVector = PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("测试匹配")
                    }
                    
                    // 测试结果显示区域
                    if (testResult != null) {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            color = when {
                                !testResult.isSuccess -> AppColors.Red50
                                testResult.hasMatch -> AppColors.Green50
                                else -> AppColors.Gray50
                            }
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                if (!testResult.isSuccess) {
                                    // 错误信息
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Error,
                                            contentDescription = null,
                                            tint = AppColors.Error,
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Text(
                                            text = testResult.errorMessage ?: "测试失败",
                                            fontSize = 14.sp,
                                            color = AppColors.Error
                                        )
                                    }
                                } else if (testResult.hasMatch) {
                                    // 匹配成功
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Icon(
                                            imageVector = CheckCircle,
                                            contentDescription = null,
                                            tint = AppColors.Success,
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Text(
                                            text = "匹配成功！找到 ${testResult.matchCount} 个匹配项",
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = AppColors.Success
                                        )
                                    }
                                    
                                    // 显示匹配结果
                                    if (testResult.matches.isNotEmpty()) {
                                        Column(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            Text(
                                                text = "匹配内容:",
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = AppColors.TextPrimary
                                            )
                                            testResult.matches.forEachIndexed { index, match ->
                                                Surface(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    shape = RoundedCornerShape(4.dp),
                                                    color = Color.White
                                                ) {
                                                    Text(
                                                        text = "${index + 1}. \"$match\"",
                                                        fontSize = 13.sp,
                                                        fontFamily = FontFamily.Monospace,
                                                        color = AppColors.TextPrimary,
                                                        modifier = Modifier.padding(8.dp)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    // 无匹配
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Icon(
                                            imageVector = Cancel,
                                            contentDescription = null,
                                            tint = AppColors.TextSecondary,
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Text(
                                            text = "未找到匹配项",
                                            fontSize = 14.sp,
                                            color = AppColors.TextSecondary
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            color = AppColors.Gray50
                        ) {
                            Text(
                                text = "测试结果将显示在这里",
                                fontSize = 14.sp,
                                color = AppColors.TextSecondary,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * 示例项
 */
@Composable
private fun ExampleItem(example: RegexExample) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = if (example.isMatch) AppColors.Green50 else AppColors.Red50
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = example.text,
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace,
                color = AppColors.TextPrimary,
                modifier = Modifier.weight(1f)
            )
            
            Icon(
                imageVector = if (example.isMatch) CheckCircle else Cancel,
                contentDescription = null,
                tint = if (example.isMatch) AppColors.Success else AppColors.Error,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

// ==================== 数据模型 ====================

/**
 * 测试结果
 */
data class TestResult(
    val isSuccess: Boolean,
    val hasMatch: Boolean,
    val matchCount: Int,
    val matches: List<String>,
    val errorMessage: String? = null
)

/**
 * 正则分类
 */
enum class RegexCategory(val displayName: String) {
    VALIDATION("验证类"),
    EXTRACTION("提取类"),
    FORMATTING("格式化类")
}

/**
 * 编程语言
 */
enum class ProgrammingLanguage(val displayName: String) {
    JAVA("Java"),
    JAVASCRIPT("JavaScript"),
    KOTLIN("Kotlin"),
    DART("Dart"),
    PYTHON("Python")
}

/**
 * 正则项
 */
data class RegexItem(
    val name: String,
    val description: String,
    val pattern: String,
    val category: RegexCategory,
    val examples: List<RegexExample> = emptyList()
) {
    fun getCodeForLanguage(language: ProgrammingLanguage): String {
        return when (language) {
            ProgrammingLanguage.JAVA -> "Pattern.compile(\"$pattern\")"
            ProgrammingLanguage.JAVASCRIPT -> "/$pattern/"
            ProgrammingLanguage.KOTLIN -> "Regex(\"$pattern\")"
            ProgrammingLanguage.DART -> "RegExp(r'$pattern')"
            ProgrammingLanguage.PYTHON -> "re.compile(r\"$pattern\")"
        }
    }
}

/**
 * 正则示例
 */
data class RegexExample(
    val text: String,
    val isMatch: Boolean
)

/**
 * 根据分类获取正则列表
 */
private fun getRegexListByCategory(category: RegexCategory): List<RegexItem> {
    return when (category) {
        RegexCategory.VALIDATION -> listOf(
            RegexItem(
                name = "邮箱验证",
                description = "验证标准邮箱格式",
                pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("user@example.com", true),
                    RegexExample("invalid.email", false)
                )
            ),
            RegexItem(
                name = "手机号验证",
                description = "验证中国大陆手机号",
                pattern = "^1[3-9]\\d{9}$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("13812345678", true),
                    RegexExample("12345678901", false)
                )
            ),
            RegexItem(
                name = "邮政编码验证",
                description = "验证中国邮政编码，6位数字",
                pattern = "^\\d{6}$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("100000", true),
                    RegexExample("012345", true),
                    RegexExample("12345", false)
                )
            ),
            RegexItem(
                name = "强密码验证",
                description = "密码必须包含大小写字母、数字和特殊字符，长度8-16位",
                pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("Abc123!@", true),
                    RegexExample("abc123", false)
                )
            ),
            RegexItem(
                name = "URL验证",
                description = "验证URL地址的合法性，支持http、https协议，可选端口、路径、参数、锚点，支持localhost和IP地址",
                pattern = "^https?://(([a-zA-Z0-9_-])+(\\.)?)*(:[0-9]+)?(/[a-zA-Z0-9_-]*)*/?\\??([a-zA-Z0-9_-]*=[a-zA-Z0-9_-]*&?)*#?([a-zA-Z0-9_-]*)?$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("https://www.example.com:8080/path?key=value#anchor", true),
                    RegexExample("http://localhost:3000", true),
                    RegexExample("not-a-url", false)
                )
            ),
            RegexItem(
                name = "身份证验证",
                description = "验证中国大陆居民身份证号码，支持18位",
                pattern = "^[1-9]\\d{5}(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}[0-9Xx]$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("110101199001011234", true),
                    RegexExample("11010119900101123X", true),
                    RegexExample("123456789012345678", false)
                )
            ),
            RegexItem(
                name = "IPv4地址验证",
                description = "验证IPv4地址",
                pattern = "^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("192.168.1.1", true),
                    RegexExample("256.1.1.1", false)
                )
            ),
            RegexItem(
                name = "IPv6地址验证",
                description = "验证IPv6地址",
                pattern = "^(([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|::)$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("2001:0db8:85a3:0000:0000:8a2e:0370:7334", true),
                    RegexExample("2001:db8::8a2e:370:7334", true),
                    RegexExample("192.168.1.1", false)
                )
            ),
            RegexItem(
                name = "数字验证",
                description = "验证是否为数字（整数或小数）",
                pattern = "^-?\\d+(\\.\\d+)?$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("123", true),
                    RegexExample("-45.67", true),
                    RegexExample("abc", false)
                )
            ),
            RegexItem(
                name = "日期验证",
                description = "验证日期格式 YYYY-MM-DD",
                pattern = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("2024-01-15", true),
                    RegexExample("2024-13-01", false)
                )
            ),
            RegexItem(
                name = "n位数字验证",
                description = "验证是否为n位数字（示例：6位）",
                pattern = "^\\d{6}$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("123456", true),
                    RegexExample("12345", false)
                )
            ),
            RegexItem(
                name = "至少n位数字验证",
                description = "验证至少n位数字（示例：至少6位）",
                pattern = "^\\d{6,}$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("123456", true),
                    RegexExample("1234567890", true),
                    RegexExample("12345", false)
                )
            ),
            RegexItem(
                name = "数字位数范围验证",
                description = "验证数字位数在指定范围内（示例：6-12位）",
                pattern = "^\\d{6,12}$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("123456", true),
                    RegexExample("123456789012", true),
                    RegexExample("12345", false)
                )
            ),
            RegexItem(
                name = "小数验证",
                description = "验证小数（可选负号）",
                pattern = "^-?\\d+\\.\\d+$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("3.14", true),
                    RegexExample("-0.5", true),
                    RegexExample("123", false)
                )
            ),
            RegexItem(
                name = "整数验证",
                description = "验证整数（可选负号）",
                pattern = "^-?\\d+$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("123", true),
                    RegexExample("-456", true),
                    RegexExample("3.14", false)
                )
            ),
            RegexItem(
                name = "中文姓名验证",
                description = "验证中文姓名（2-4个汉字）",
                pattern = "^[\\u4e00-\\u9fa5]{2,4}$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("张三", true),
                    RegexExample("欧阳修", true),
                    RegexExample("Zhang San", false)
                )
            ),
            RegexItem(
                name = "英文姓名验证",
                description = "验证英文姓名（支持空格和连字符）",
                pattern = "^[a-zA-Z]+([\\s-][a-zA-Z]+)*$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("John Doe", true),
                    RegexExample("Mary-Jane", true),
                    RegexExample("张三", false)
                )
            ),
            RegexItem(
                name = "MAC地址验证",
                description = "验证MAC地址",
                pattern = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("00:1B:44:11:3A:B7", true),
                    RegexExample("00-1B-44-11-3A-B7", true),
                    RegexExample("00:1B:44:11:3A", false)
                )
            ),
            RegexItem(
                name = "金额验证",
                description = "验证金额（最多2位小数）",
                pattern = "^(0|[1-9]\\d*)(\\.\\d{1,2})?$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("100", true),
                    RegexExample("99.99", true),
                    RegexExample("99.999", false)
                )
            ),
            RegexItem(
                name = "域名验证",
                description = "验证域名格式",
                pattern = "^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,}$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("example.com", true),
                    RegexExample("sub.example.com", true),
                    RegexExample("invalid_domain", false)
                )
            ),
            RegexItem(
                name = "Emoji过滤",
                description = "检测是否包含Emoji表情",
                pattern = "[\\uD83C-\\uDBFF\\uDC00-\\uDFFF]+",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("Hello 😊", true),
                    RegexExample("Hello World", false)
                )
            ),
            RegexItem(
                name = "纯中文验证",
                description = "验证是否为纯中文",
                pattern = "^[\\u4e00-\\u9fa5]+$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("你好世界", true),
                    RegexExample("Hello世界", false)
                )
            ),
            RegexItem(
                name = "纯英文验证",
                description = "验证是否为纯英文字母",
                pattern = "^[a-zA-Z]+$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("Hello", true),
                    RegexExample("Hello123", false)
                )
            ),
            RegexItem(
                name = "不包含特殊字符",
                description = "验证不包含特殊字符（只允许字母、数字、中文、下划线）",
                pattern = "^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$",
                category = RegexCategory.VALIDATION,
                examples = listOf(
                    RegexExample("Hello123_你好", true),
                    RegexExample("Hello@123", false)
                )
            )
        )
        
        RegexCategory.EXTRACTION -> listOf(
            RegexItem(
                name = "手机号提取",
                description = "从文本中提取中国大陆手机号",
                pattern = "1[3-9]\\d{9}",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("联系电话：13812345678", true),
                    RegexExample("No phone here", false)
                )
            ),
            RegexItem(
                name = "邮箱提取",
                description = "从文本中提取邮箱地址",
                pattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("Contact: user@example.com", true),
                    RegexExample("No email here", false)
                )
            ),
            RegexItem(
                name = "URL链接提取",
                description = "从文本中提取URL链接",
                pattern = "https?://[\\w\\-]+(\\.[\\w\\-]+)+[/#?]?[^\\s]*",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("Visit https://example.com/path?key=value", true),
                    RegexExample("No URL here", false)
                )
            ),
            RegexItem(
                name = "域名提取",
                description = "从文本中提取域名",
                pattern = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,}",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("访问 www.example.com 获取更多", true),
                    RegexExample("No domain here", false)
                )
            ),
            RegexItem(
                name = "IPv4地址提取",
                description = "从文本中提取IPv4地址",
                pattern = "((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("服务器IP: 192.168.1.1", true),
                    RegexExample("No IP here", false)
                )
            ),
            RegexItem(
                name = "IPv6地址提取",
                description = "从文本中提取IPv6地址",
                pattern = "(([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|::)",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("IPv6: 2001:db8::8a2e:370:7334", true),
                    RegexExample("No IPv6 here", false)
                )
            ),
            RegexItem(
                name = "MAC地址提取",
                description = "从文本中提取MAC地址",
                pattern = "([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("MAC: 00:1B:44:11:3A:B7", true),
                    RegexExample("No MAC here", false)
                )
            ),
            RegexItem(
                name = "固定电话提取",
                description = "从文本中提取固定电话号码",
                pattern = "0\\d{2,3}-?\\d{7,8}",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("电话：010-12345678", true),
                    RegexExample("No phone here", false)
                )
            ),
            RegexItem(
                name = "邮政编码提取",
                description = "从文本中提取邮政编码",
                pattern = "[1-9]\\d{5}",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("邮编：100000", true),
                    RegexExample("No postal code", false)
                )
            ),
            RegexItem(
                name = "身份证号提取",
                description = "从文本中提取身份证号码",
                pattern = "[1-9]\\d{5}(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}[0-9Xx]",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("身份证：110101199001011234", true),
                    RegexExample("No ID card", false)
                )
            ),
            RegexItem(
                name = "银行卡号提取",
                description = "从文本中提取银行卡号（16-19位）",
                pattern = "[1-9]\\d{15,18}",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("卡号：6222021234567890123", true),
                    RegexExample("No card number", false)
                )
            ),
            RegexItem(
                name = "车牌号提取",
                description = "从文本中提取中国车牌号（蓝牌7位或绿牌8位）",
                pattern = "[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z]([A-HJ-NP-Z0-9]{5}|([0-9]{5}[DF])|([DF][A-HJ-NP-Z0-9][0-9]{4}))",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("蓝牌：京A12345 绿牌：京AD12345", true),
                    RegexExample("No plate number", false)
                )
            ),
            RegexItem(
                name = "@用户提取",
                description = "从文本中提取@用户名（社交媒体）",
                pattern = "@[a-zA-Z0-9_-]+",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("关注 @username 获取更新", true),
                    RegexExample("No mention here", false)
                )
            ),
            RegexItem(
                name = "#话题提取",
                description = "从文本中提取#话题标签",
                pattern = "#[^\\s#]+",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("热门话题 #科技 #AI", true),
                    RegexExample("No hashtag here", false)
                )
            ),
            RegexItem(
                name = "Emoji提取",
                description = "从文本中提取Emoji表情",
                pattern = "[\\uD83C-\\uDBFF\\uDC00-\\uDFFF]+",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("Hello 😊 World 🌍", true),
                    RegexExample("No emoji here", false)
                )
            ),
            RegexItem(
                name = "中文字符提取",
                description = "提取所有中文字符",
                pattern = "[\\u4e00-\\u9fa5]+",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("Hello世界123", true),
                    RegexExample("NoChineseHere", false)
                )
            ),
            RegexItem(
                name = "英文字母提取",
                description = "提取所有英文字母",
                pattern = "[a-zA-Z]+",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("Hello123世界", true),
                    RegexExample("123456", false)
                )
            ),
            RegexItem(
                name = "数字提取",
                description = "提取所有数字",
                pattern = "\\d+",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("Price: 123.45", true),
                    RegexExample("No numbers", false)
                )
            ),
            RegexItem(
                name = "数字字母组合提取",
                description = "提取数字和字母的组合",
                pattern = "[a-zA-Z0-9]+",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("订单号：ABC123XYZ", true),
                    RegexExample("中文内容", false)
                )
            ),
            RegexItem(
                name = "颜色值提取",
                description = "提取十六进制颜色值",
                pattern = "#([0-9a-fA-F]{6}|[0-9a-fA-F]{3})",
                category = RegexCategory.EXTRACTION,
                examples = listOf(
                    RegexExample("#FF5733", true),
                    RegexExample("#FFF", true),
                    RegexExample("FF5733", false)
                )
            )
        )
        
        RegexCategory.FORMATTING -> listOf(
            RegexItem(
                name = "手机号格式化",
                description = "格式化为 XXX-XXXX-XXXX",
                pattern = "(\\d{3})(\\d{4})(\\d{4})",
                category = RegexCategory.FORMATTING,
                examples = listOf(
                    RegexExample("13812345678", true),
                    RegexExample("123", false)
                )
            ),
            RegexItem(
                name = "身份证格式化",
                description = "格式化为 XXXXXX-XXXX-XXXX-XXXX",
                pattern = "(\\d{6})(\\d{4})(\\d{4})(\\d{4})",
                category = RegexCategory.FORMATTING,
                examples = listOf(
                    RegexExample("110101199001011234", true),
                    RegexExample("123456", false)
                )
            ),
            RegexItem(
                name = "银行卡格式化",
                description = "格式化为 XXXX-XXXX-XXXX-XXXX",
                pattern = "(\\d{4})(\\d{4})(\\d{4})(\\d{4})",
                category = RegexCategory.FORMATTING,
                examples = listOf(
                    RegexExample("6222021234567890", true),
                    RegexExample("123456", false)
                )
            ),
            RegexItem(
                name = "日期格式化",
                description = "格式化为 YYYY-MM-DD",
                pattern = "(\\d{4})(\\d{2})(\\d{2})",
                category = RegexCategory.FORMATTING,
                examples = listOf(
                    RegexExample("20240101", true),
                    RegexExample("2024", false)
                )
            ),
            RegexItem(
                name = "时间格式化",
                description = "格式化为 HH:MM:SS",
                pattern = "(\\d{2})(\\d{2})(\\d{2})",
                category = RegexCategory.FORMATTING,
                examples = listOf(
                    RegexExample("123456", true),
                    RegexExample("12", false)
                )
            )
        )
    }
}
