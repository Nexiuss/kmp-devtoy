package com.nexius.devtoy

import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import com.gabrieldrn.carbon.tab.TabItem
import com.gabrieldrn.carbon.tab.TabList
import com.nexius.devtoy.components.*
import com.nexius.devtoy.components.MenuItem
import com.nexius.devtoy.components.ftp.ui.FtpScreen
import compose.icons.FeatherIcons
import compose.icons.feathericons.Menu
import compose.icons.feathericons.Settings
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    // 主布局（垂直排列）
    layout()
}


fun loadIcon(path: String): ImageBitmap {
    // path 例如 "icons/folder.png"
    return useResource(path, ::loadImageBitmap)
}


@Composable
fun IconImage(path: String, modifier: Modifier = Modifier) {
    val imageBitmap = loadIcon(path)
    Image(
        bitmap = imageBitmap,
        contentDescription = null,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun layout() {
    val home = getById("home")
    var menuVisiable by remember { mutableStateOf(true) }
    var selectedItem: MenuItem by remember { mutableStateOf(home) }

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
                            Icon(FeatherIcons.Menu, contentDescription = "图标", modifier = Modifier.size(24.dp))
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
                                }
                            )
                        }

                    }

                },
                actions = {
                    IconButton(onClick = { /* 用户信息 */ }) {
                        Icon(FeatherIcons.Settings, contentDescription = "用户")
                    }
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
                    getContent(selectedItem)
                }
            }
        }
    }
}

fun toTab(menuItem: MenuItem): TabItem {
    return TabItem(menuItem.name)
}

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
            //SqlFormat()
        }
        "ftp" -> {
            FtpScreen()
        }
    }
}



