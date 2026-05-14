package com.nexius.devtoy

import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.DataArray
import androidx.compose.material.icons.filled.DriveFolderUpload
import androidx.compose.material.icons.filled.FilePresent
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.FormatPaint
import androidx.compose.material.icons.filled.GppGood
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Html
import androidx.compose.material.icons.filled.Http
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.NetworkPing
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.TextFormat
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Transform
import androidx.compose.material.icons.filled.Verified
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
                            Icon(Icons.Default.Menu, contentDescription = "图标", modifier = Modifier.size(24.dp))
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
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "后退")
                    }
                    IconButton(onClick = { navigator.navigate("home") }) {
                        Icon(Icons.Default.Home, contentDescription = "首页")
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
        icon = Icons.Default.Home ,
    ),
    MenuItem(
        id = "endecode",
        path = "root/endecode",
        name = "编解码器",
        icon = Icons.Default.Transform,
        expandedIcon = Icons.Default.FolderOpen,
        children = listOf(
            MenuItem(
                id = "base64",
                path = "root/endecode/base64",
                name = "base64文本",
                icon = Icons.Default.Code),
            MenuItem(
                id = "html",
                path = "root/endecode/html",
                name = "HTML",
                icon = Icons.Default.Html,
                expandedIcon = Icons.Default.FolderOpen
            ),
            MenuItem(
                id = "url",
                path =  "root/endecode/url",
                name = "URL",
                icon = Icons.Default.Link,
                expandedIcon = Icons.Default.FolderOpen
            ),
            MenuItem(
                id = "qrCode",
                path =  "root/endecode/qrCode",
                name = "二维码",
                icon = Icons.Default.QrCode,
                expandedIcon = Icons.Default.FolderOpen
            ),
            MenuItem(
                id = "jwt",
                path =  "root/endecode/jwt",
                name = "JWT",
                icon = Icons.Default.Verified,
                expandedIcon = Icons.Default.FolderOpen
            )
        )
    ),
    MenuItem(
        id = "format",
        name =  "格式化工具",
        path =  "root/format",
        icon = Icons.Default.FormatPaint,
        expandedIcon = Icons.Default.FolderOpen,
        children = listOf(
            MenuItem(
                id = "json",
                path = "root/format/json",
                name = "JSON",
                icon = Icons.Default.DataArray),
            MenuItem(
                id = "sql",
                path = "root/format/sql",
                name = "SQL",
                icon = Icons.Default.Storage,
                expandedIcon = Icons.Default.FolderOpen
            ),
            MenuItem(
                id = "xml",
                path =  "root/format/xml",
                name = "XML",
                icon = Icons.Default.Html,
                expandedIcon = Icons.Default.FolderOpen
            )
        )
    ),
    MenuItem(
        id = "generator",
        name =  "生成器",
        path =  "root/generator",
        icon = Icons.Default.AddBox,
        expandedIcon = Icons.Default.FolderOpen,
        children = listOf(
            MenuItem(
                id = "uuid",
                path = "root/generator/uuid",
                name = "UUID",
                icon = Icons.Default.GppGood)
        )
    ),
    MenuItem(
        id = "text",
        name =  "文本处理",
        path =  "root/text",
        icon = Icons.Default.TextFormat,
        expandedIcon = Icons.Default.FolderOpen,
        children = listOf(
            MenuItem(
                id = "markdown",
                path = "root/text/markdown",
                name = "Markdown",
                icon = Icons.Filled.Article),
            MenuItem(
                id = "regexCheck",
                path = "root/text/regexCheck",
                name = "正则速查",
                icon = Icons.Filled.Search)
        )
    ),MenuItem(
        id = "net",
        name =  "网络",
        path =  "root/net",
        icon = Icons.Default.NetworkPing,
        expandedIcon = Icons.Default.FolderOpen,
        children = listOf(
            MenuItem(
                id = "httpClient",
                path = "root/net/httpClient",
                name = "Http客户端",
                icon = Icons.Default.Http)
        )
    ),
    MenuItem(
        id = "ftp",
        path = "root/ftp",
        name = "FTP",
        icon = Icons.Default.DriveFolderUpload,
    ),
    MenuItem(
        id = "fileRename",
        path = "root/fileRename",
        name = "文件重命名",
        icon = Icons.Filled.FilePresent,
    ),
    MenuItem(
        id = "loanCalculator",
        path = "root/LoanCalculator",
        name = "贷款计算",
        icon = Icons.Default.Calculate,
    ),
    MenuItem(
        id = "timestampConverter",
        path = "root/timestampConverter",
        name = "时间戳转换",
        icon = Icons.Default.Timer,
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
            QrCode()
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



