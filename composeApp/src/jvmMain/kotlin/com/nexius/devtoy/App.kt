package com.nexius.devtoy

import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState

import com.nexius.devtoy.components.Icons.AddBox
import com.nexius.devtoy.components.Icons.Article
import com.nexius.devtoy.components.Icons.Calculate
import com.nexius.devtoy.components.Icons.Code
import com.nexius.devtoy.components.Icons.DataArray
import com.nexius.devtoy.components.Icons.DriveFolderUpload
import com.nexius.devtoy.components.Icons.FilePresent
import com.nexius.devtoy.components.Icons.FolderOpen
import com.nexius.devtoy.components.Icons.FormatPaint
import com.nexius.devtoy.components.Icons.GppGood
import com.nexius.devtoy.components.Icons.Home
import com.nexius.devtoy.components.Icons.Html
import com.nexius.devtoy.components.Icons.Http
import com.nexius.devtoy.components.Icons.Link
import com.nexius.devtoy.components.Icons.Menu
import com.nexius.devtoy.components.Icons.NetworkPing
import com.nexius.devtoy.components.Icons.QrCode
import com.nexius.devtoy.components.Icons.Search
import com.nexius.devtoy.components.Icons.Storage
import com.nexius.devtoy.components.Icons.TextFormat
import com.nexius.devtoy.components.Icons.Timer
import com.nexius.devtoy.components.Icons.Transform
import com.nexius.devtoy.components.Icons.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.gabrieldrn.carbon.tab.TabItem
import com.gabrieldrn.carbon.tab.TabList
import com.nexius.devtoy.components.*
import com.nexius.devtoy.components.Icons.ArrowBack
import com.nexius.devtoy.components.Icons.CodeXml
import com.nexius.devtoy.components.Icons.Markdown
import com.nexius.devtoy.components.filerename.ui.FileRenameView
import com.nexius.devtoy.components.ftp.ui.FtpScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    // 主布局（垂直排列）
    layout()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun layout() {
    val home = getById("home")
    var menuVisiable by remember { mutableStateOf(true) }
    var selectedItem: MenuItem by remember { mutableStateOf(home) }
    val navigator = rememberNavController()

    val tabs = remember { mutableStateListOf(toTab(home)) }
    var selectedTab by remember { mutableStateOf(toTab(home)) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        IconButton(onClick = {
                            menuVisiable = !menuVisiable
                        }) {
                            Icon(Menu, contentDescription = "图标", modifier = Modifier.size(24.dp))
                        }

                        Spacer(modifier = Modifier.weight(1f))
                        ContextMenuArea(items = {
                            listOf(
                                ContextMenuItem("关闭未选中"){
                                    tabs.removeIf { it != selectedTab }
                                },
                                ContextMenuItem("关闭选中") {
                                    tabs.remove(selectedTab)
                                },
                                ContextMenuItem("关闭所有") {
                                    tabs.clear()
                                }
                            )
                        }) {
                            TabList(
                                modifier = Modifier.fillMaxWidth(), tabs = tabs, selectedTab = selectedTab, onTabSelected = {
                                    selectedTab = it
                                    selectedItem = getByName(it.label)
                                    navigator.navigate(selectedItem.id)
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
                        Icon(Home, contentDescription = "首页")
                    }
                    MinimalDropdownMenu()
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        // 首先定义一个滚动状态
        val scrollState = rememberScrollState()

        // 主布局：左侧菜单 + 右侧内容
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if(menuVisiable){
                // 左侧菜单树
                Surface(
                    modifier = Modifier
                        .width(240.dp)
                        .fillMaxHeight(),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shadowElevation = 2.dp
                ) {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        item {
                            FontAwesomeMenuTree(menuItems = menuItems, onClick = {
                                println(
                                    "点击了: ${it.name}"
                                )
                                selectedItem = it
                                if(it.children.isEmpty()){
                                    selectedTab = toTab(it)
                                    if(!tabs.contains(toTab(it))){
                                        tabs.add(toTab(it))
                                    }
                                    navigator.navigate(it.id)
                                }
                            })
                        }
                    }
                }
            }
            // 右侧内容区域
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Navigation(navigator)
                }
            }
        }
    }
}

fun toTab(menuItem: MenuItem): TabItem {
    return TabItem(menuItem.name)
}


// 示例菜单数据 - 使用Font Awesome图标
val menuItems = listOf(
    MenuItem(
        id = "home",
        path = "root/home",
        name = "首页",
        icon = Home ,
    ),
    MenuItem(
        id = "endecode",
        path = "root/endecode",
        name = "编解码器",
        icon = Transform,
        expandedIcon = FolderOpen,
        children = listOf(
            MenuItem(
                id = "base64",
                path = "root/endecode/base64",
                name = "base64文本",
                icon = Code),
            MenuItem(
                id = "html",
                path = "root/endecode/html",
                name = "HTML",
                icon = Html,
                expandedIcon = FolderOpen
            ),
            MenuItem(
                id = "url",
                path =  "root/endecode/url",
                name = "URL",
                icon = Link,
                expandedIcon = FolderOpen
            ),
            MenuItem(
                id = "qrCode",
                path =  "root/endecode/qrCode",
                name = "二维码",
                icon = QrCode,
                expandedIcon = FolderOpen
            ),
            MenuItem(
                id = "jwt",
                path =  "root/endecode/jwt",
                name = "JWT",
                icon = Verified,
                expandedIcon = FolderOpen
            )
        )
    ),
    MenuItem(
        id = "format",
        name =  "格式化工具",
        path =  "root/format",
        icon = FormatPaint,
        expandedIcon = FolderOpen,
        children = listOf(
            MenuItem(
                id = "json",
                path = "root/format/json",
                name = "JSON",
                icon = DataArray),
            MenuItem(
                id = "sql",
                path = "root/format/sql",
                name = "SQL",
                icon = Storage,
                expandedIcon = FolderOpen
            ),
            MenuItem(
                id = "xml",
                path =  "root/format/xml",
                name = "XML",
                icon = CodeXml,
                expandedIcon = FolderOpen
            )
        )
    ),
    MenuItem(
        id = "generator",
        name =  "生成器",
        path =  "root/generator",
        icon = AddBox,
        expandedIcon = FolderOpen,
        children = listOf(
            MenuItem(
                id = "uuid",
                path = "root/generator/uuid",
                name = "UUID",
                icon = GppGood)
        )
    ),
    MenuItem(
        id = "text",
        name =  "文本处理",
        path =  "root/text",
        icon = TextFormat,
        expandedIcon = FolderOpen,
        children = listOf(
            MenuItem(
                id = "markdown",
                path = "root/text/markdown",
                name = "Markdown",
                icon = Markdown),
            MenuItem(
                id = "regexCheck",
                path = "root/text/regexCheck",
                name = "正则速查",
                icon = Search)
        )
    ),MenuItem(
        id = "net",
        name =  "网络",
        path =  "root/net",
        icon = NetworkPing,
        expandedIcon = FolderOpen,
        children = listOf(
            MenuItem(
                id = "httpClient",
                path = "root/net/httpClient",
                name = "Http客户端",
                icon = Http)
        )
    ),
    MenuItem(
        id = "ftp",
        path = "root/ftp",
        name = "FTP",
        icon = DriveFolderUpload,
    ),
    MenuItem(
        id = "fileRename",
        path = "root/fileRename",
        name = "文件重命名",
        icon = FilePresent,
    ),
    MenuItem(
        id = "loanCalculator",
        path = "root/LoanCalculator",
        name = "贷款计算",
        icon = Calculate,
    ),
    MenuItem(
        id = "timestampConverter",
        path = "root/timestampConverter",
        name = "时间戳转换",
        icon = Timer,
    ),
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
    }
}



