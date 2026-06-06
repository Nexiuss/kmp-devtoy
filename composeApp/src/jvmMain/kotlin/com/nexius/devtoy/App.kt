package com.nexius.devtoy

import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.isMetaPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.gabrieldrn.carbon.tab.TabItem
import com.gabrieldrn.carbon.tab.TabList
import com.nexius.devtoy.components.*
import com.nexius.devtoy.components.Icons.ArrowBack
import com.nexius.devtoy.components.filerename.ui.FileRenameView
import com.nexius.devtoy.components.ftp.ui.FtpScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.coroutines.ContinuationInterceptor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    layout()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun layout() {
    val home = getById("home")
    // 侧边栏显隐
    var menuVisible by remember { mutableStateOf(true) }
    var selectedItem: MenuItem by remember { mutableStateOf(home) }
    val navigator = rememberNavController()

    // 标签页状态
    val tabs = remember { mutableStateListOf(toTab(home)) }
    var selectedTab by remember { mutableStateOf(toTab(home)) }

    // 初始宽度、最小宽度、最大宽度限制
    var sidebarWidth by remember { mutableStateOf(240.dp) }
    val minSidebarWidth = 180.dp
    val maxSidebarWidth = 400.dp

    Scaffold(
        // ========== 新增：全局快捷键监听修饰符 ==========
        modifier = Modifier.onKeyEvent { event ->
            if (event.type == KeyEventType.KeyUp) return@onKeyEvent false
            // 监听 Ctrl+W 或 Cmd+W
            val isWKey = event.key == Key.W
            val isShortcut = (event.isCtrlPressed || event.isMetaPressed) && isWKey
            if (isShortcut) {
                // 执行关闭当前选中标签
                if (tabs.size > 1) { // 防止关闭最后一个标签
                    tabs.remove(selectedTab)
                    // 关闭后自动切换到最后一个标签
                    if (tabs.isNotEmpty()) {
                        selectedTab = tabs.last()
                        val item = getByName(selectedTab.label)
                        if (item != null) {
                            navigator.navigate(item.id)
                        }
                    }
                }
                true // 消费事件
            } else {
                false
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // 菜单显隐按钮
                        IconButton(onClick = { menuVisible = !menuVisible }) {
                            Icon(
                                Icons.Menu,
                                contentDescription = "切换菜单",
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        // 标签页 + 右键菜单
                        ContextMenuArea(items = {
                            listOf(
                                ContextMenuItem("关闭未选中") { tabs.removeIf { it != selectedTab } },
                                ContextMenuItem("关闭选中") { tabs.remove(selectedTab) },
                                ContextMenuItem("关闭所有") { tabs.clear() }
                            )
                        }) {
                            TabList(
                                modifier = Modifier.fillMaxWidth(),
                                tabs = tabs,
                                selectedTab = selectedTab,
                                onTabSelected = {
                                    selectedTab = it
                                    val item = getByName(it.label)
                                    if (item != null) {
                                        navigator.navigate(item.id)
                                    }
                                }
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { navigator.popBackStack() }) {
                        Icon(ArrowBack, contentDescription = "后退")
                    }
                    IconButton(onClick = { navigator.navigate("home") }) {
                        Icon(Icons.Home, contentDescription = "首页")
                    }
                    MinimalDropdownMenu()
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        val scrollState = rememberScrollState()

        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // 左侧可拖拽侧边栏
            if (menuVisible) {
                // 侧边栏内容
                Surface(
                    modifier = Modifier
                        .width(sidebarWidth)
                        .fillMaxHeight(),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shadowElevation = 2.dp
                ) {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        item {
                            FontAwesomeMenuTree(
                                menuItems = menuItems,
                                onClick = {
                                    selectedItem = it
                                    if (it.children.isEmpty()) {
                                        val tab = toTab(it)
                                        selectedTab = tab
                                        if (!tabs.contains(tab)) {
                                            tabs.add(tab)
                                        }
                                        navigator.navigate(it.id)
                                    }
                                }
                            )
                        }
                    }
                }

                // ====================== 拖拽分割条 ======================
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(10.dp)
                        .background(Color.Transparent)
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDrag = { _, dragAmount ->
                                    val newWidth = sidebarWidth + dragAmount.x.toDp()
                                    sidebarWidth = newWidth.coerceIn(minSidebarWidth, maxSidebarWidth)
                                }
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    // 悬浮拖动图标
                    Icon(
                        imageVector = Icons.DragIndicator,
                        contentDescription = "左右拖动调整宽度",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                    )
                }
                // ======================================================
            }

            // 右侧内容区域
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Navigation(navigator)
            }
        }
    }
}

// 转换为标签项
fun toTab(menuItem: MenuItem): TabItem {
    return TabItem(menuItem.name)
}
fun getById(id: String): MenuItem {
    return getItem(tree = menuItems, filter = { it.id == id })
        ?: throw IllegalArgumentException("No menu item found with id $id")
}
fun getByName(name: String): MenuItem {
    return getItem(tree = menuItems, filter = { it.name == name })
        ?: throw IllegalArgumentException("No menu item found with name $name")
}

// ====================== 菜单数据（无修改） ======================
val menuItems = listOf(
    MenuItem(
        id = "home",
        path = "root/home",
        name = "首页",
        icon = Icons.Home,
    ),
    MenuItem(
        id = "endecode",
        path = "root/endecode",
        name = "编解码器",
        icon = Icons.Transform,
        expandedIcon = Icons.FolderOpen,
        children = listOf(
            MenuItem(id = "base64", path = "root/endecode/base64", name = "base64文本", icon = Icons.Code),
            MenuItem(id = "base85", path = "root/endecode/base85", name = "base85文本文件", icon = Icons.TextFile),
            MenuItem(id = "html", path = "root/endecode/html", name = "HTML", icon = Icons.Html, expandedIcon = Icons.FolderOpen),
            MenuItem(id = "url", path = "root/endecode/url", name = "URL", icon = Icons.Link, expandedIcon = Icons.FolderOpen),
            MenuItem(id = "qrCode", path = "root/endecode/qrCode", name = "二维码", icon = Icons.QrCode, expandedIcon = Icons.FolderOpen),
            MenuItem(id = "jwt", path = "root/endecode/jwt", name = "JWT", icon = Icons.Verified, expandedIcon = Icons.FolderOpen)
        )
    ),
    MenuItem(
        id = "format",
        name = "格式化工具",
        path = "root/format",
        icon = Icons.FormatPaint,
        expandedIcon = Icons.FolderOpen,
        children = listOf(
            MenuItem(id = "json", path = "root/format/json", name = "JSON", icon = Icons.DataArray),
            MenuItem(id = "sql", path = "root/format/sql", name = "SQL", icon = Icons.Storage, expandedIcon = Icons.FolderOpen),
            MenuItem(id = "xml", path = "root/format/xml", name = "XML", icon = Icons.CodeXml, expandedIcon = Icons.FolderOpen)
        )
    ),
    MenuItem(
        id = "generator",
        name = "生成器",
        path = "root/generator",
        icon = Icons.AddBox,
        expandedIcon = Icons.FolderOpen,
        children = listOf(
            MenuItem(id = "uuid", path = "root/generator/uuid", name = "UUID", icon = Icons.GppGood)
        )
    ),
    MenuItem(
        id = "text",
        name = "文本处理",
        path = "root/text",
        icon = Icons.TextFormat,
        expandedIcon = Icons.FolderOpen,
        children = listOf(
            MenuItem(id = "markdown", path = "root/text/markdown", name = "Markdown", icon = Icons.Markdown),
            MenuItem(id = "regexCheck", path = "root/text/regexCheck", name = "正则速查", icon = Icons.Search),
            MenuItem(id = "textCase", path = "root/text/textCase", name = "转换大小写", icon = Icons.SwitchIcon),
        )
    ),
    MenuItem(
        id = "net",
        name = "网络",
        path = "root/net",
        icon = Icons.NetworkPing,
        expandedIcon = Icons.FolderOpen,
        children = listOf(
            MenuItem(id = "httpClient", path = "root/net/httpClient", name = "Http客户端", icon = Icons.Http)
        )
    ),
    MenuItem(id = "ftp", path = "root/ftp", name = "FTP", icon = Icons.DriveFolderUpload),
    MenuItem(id = "fileRename", path = "root/fileRename", name = "文件重命名", icon = Icons.FilePresent),
    MenuItem(id = "loanCalculator", path = "root/LoanCalculator", name = "贷款计算", icon = Icons.Calculate),
    MenuItem(id = "timestampConverter", path = "root/timestampConverter", name = "时间戳转换", icon = Icons.Timer),
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun getContent(menuItem: MenuItem){
    when(menuItem.id){
        "html" -> {
            HtmlDeEncode()
        }
        "url" -> {
            UrlDeEncode()
        }
        "base64" -> {
            Base64DeEncode()
        }
        "qrCode" -> {
            QrcodeToolComponent()
        }
        "uuid" -> {
            UuidGenerator()
        }
        "markdown"->{
            MarkDownPreview()
        }
        "json" -> {
            JsonFormat()
        }
        "xml"-> {
            XmlFormat()
        }
        "sql" -> {
            SqlFormat()
        }
        "ftp" -> {
            FtpScreen()
        }
        "httpClient"->{
            HttpClientGui()
        }
        "fileRename"->{
            FileRenameView()
        }
        "jwt"->{
            JwtDecode()
        }
        "loanCalculator"->{
            LoanCalculatorScreen()
        }
        "regexCheck"->{
            RegexCheck()
        }
        "timestampConverter"->{
            TimestampConverter()
        }
        "base85"->{
            Base85DeEncode()
        }
        "textCase"->{
            TextCaseConvert()
        }
    }
}



