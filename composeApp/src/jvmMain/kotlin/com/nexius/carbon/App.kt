package com.nexius.carbon

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridCells.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow
import com.example.compose.snippets.components.AppBarExamples
import com.example.compose.snippets.components.BadgeExamples
import com.example.compose.snippets.components.CheckboxExamples
import com.example.compose.snippets.components.ChipExamples
import com.example.compose.snippets.components.DatePickerExamples
import com.example.compose.snippets.components.DividerExamples
import com.example.compose.snippets.components.FloatingActionButtonExamples
import com.example.compose.snippets.components.PartialBottomSheet
import com.example.compose.snippets.components.RichTooltipExample
import com.example.compose.snippets.components.ScaffoldExample
import com.example.compose.snippets.components.SegmentedButtonExamples
import com.example.compose.snippets.components.SliderExamples
import com.example.compose.snippets.components.SwipeToDismissBoxExamples
import com.example.compose.snippets.components.SwitchExamples
import com.example.compose.snippets.components.TimePickerExamples
import com.example.compose.snippets.components.TooltipExamples
import com.gabrieldrn.carbon.CarbonDesignSystem
import com.gabrieldrn.carbon.breadcrumb.Breadcrumb
import com.gabrieldrn.carbon.contentswitcher.ContentSwitcher
import com.gabrieldrn.carbon.contentswitcher.IconContentSwitcher
import com.gabrieldrn.carbon.loading.Loading
import com.gabrieldrn.carbon.tab.TabItem
import com.gabrieldrn.carbon.tab.TabList
import com.gabrieldrn.carbon.tag.ReadOnlyTag
import com.gabrieldrn.carbon.tag.TagType
import com.nexius.carbon.components.HighlightedTextField
import com.nexius.carbon.example.SnackbarTest
import kmp_carbon.composeapp.generated.resources.Res
import kmp_carbon.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    /*MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer).safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }*/
}

enum class Component(var value: String) {
    Breadcrumb("面包屑"),
    ContentSwitcher("滑块"),
    IconContentSwitcher("图标滑块"),
    TabList("Tab列表"),
    ReadOnlyTag("只读标签"),
    MenuTree("菜单树"),
    Loading("加载中"),
    LazyVerticalGrid("网格布局"),
    Snackbar("提示框"),
    Dialog("提示框"),
    ContextMenu("上下文菜单"),
    Card("卡片"),
    Example("其他示例"),
        Chip("Chip"),

    //ComponentsScreen("组件列表"),
    DatePickers("日期选择器"),
    Divider("分割线"),
    Badges("角标"),
    BottomSheet("底部弹窗"),
    //Carousel("轮播"),
    Checkbox("复选框"),
    FloatingActionButton("悬浮按钮"),
    Scaffold("脚手架"),
    SegmentedButton("分段按钮"),
    Slider("滑块"),
    Switch("开关"),
    SwipeToDismissBox("滑动删除"),
    TimePickers("时间选择器"),
    Tooltips("提示框"),










}

@Composable
@Preview
fun CarbonApp() {

    // 状态管理集中声明
    var options = listOf("Option 1", "Option 2", "Option 3")
    var selectedOption by remember { mutableStateOf(options[0]) }

    val tabs = remember { listOf(TabItem("t1"), TabItem("t2")) }
    var selectedTab by remember { mutableStateOf(tabs[0]) }

    var p1: Painter = painterResource(Res.drawable.compose_multiplatform)
    var p2: Painter = painterResource(Res.drawable.compose_multiplatform)
    val painters: List<Painter> = remember { listOf(p1, p2) }
    var selectedPainter by remember { mutableStateOf(painters[0]) }

    var loadingVisibility by remember { mutableStateOf(true) }

    var breadcrumbs = listOf(
        Breadcrumb(
            "x1"
        ), Breadcrumb(
            "x2"
        )
    )

    var componentRemb by remember { mutableStateOf(Component.entries[0]) }


    // 主布局（垂直排列）
    CarbonDesignSystem {

        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
                for (component in Component.entries) {
                    item {
                        Button(onClick = {
                            componentRemb = component
                        }) {
                            Text(component.value)
                        }
                    }
                }
            }
            when (componentRemb) {
                Component.Card -> {
                    OutlinedCardExample()
                }

                Component.Breadcrumb -> {
                    Breadcrumb(breadcrumbs = breadcrumbs, onBreadcrumbClick = {
                        println("clicked $it")
                    })
                }

                Component.ContentSwitcher -> {
                    // ContentSwitcher区块
                    ContentSwitcher(
                        modifier = Modifier.fillMaxWidth(),
                        options = options,
                        selectedOption = selectedOption,
                        onOptionSelected = { selectedOption = it }
                    )
                }

                Component.IconContentSwitcher -> {
                    // IconContentSwitcher区块
                    IconContentSwitcher(
                        options = painters,
                        selectedOption = selectedPainter,
                        onOptionSelected = { selectedPainter = it }
                    )
                }

                Component.TabList -> // TabList区块
                    TabList(
                        modifier = Modifier.fillMaxWidth(), tabs = tabs, selectedTab = selectedTab, onTabSelected = {
                            selectedTab = it
                        }
                    )

                Component.ReadOnlyTag -> // ReadOnlyTag区块
                    Row {
                        ReadOnlyTag("Tag1", icon = {
                            p1
                        }, type = TagType.Red)
                        ReadOnlyTag("Tag2", icon = {
                            p2
                        })
                    }

                Component.MenuTree -> {
                    // MainMenu区块
                    MainMenuSample()
                }

                Component.Loading -> {
                    Row {
                        com.gabrieldrn.carbon.button.Button(
                            label = "开关",
                            onClick = { loadingVisibility = !loadingVisibility }
                        )
                        if (loadingVisibility) {
                            Loading(
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }
                    }
                }

                Component.LazyVerticalGrid -> {
                    LazyVerticalGrid(columns = Adaptive(minSize = 128.dp)) {
                        item {
                            Text("1", modifier = Modifier.background(Color.Red))
                        }
                        item {
                            Text("2", modifier = Modifier.background(Color.Blue))
                        }
                        item {
                            Text("3", modifier = Modifier.background(Color.Yellow))
                        }
                        item {
                            Text("4", modifier = Modifier.background(Color.Red))
                        }
                    }
                }

                Component.Snackbar -> {
                    // 提示框区块
                    SnackbarTest()
                }

                Component.Dialog -> {
                    var visible by remember { mutableStateOf(true) }
                    // 提示框区块
                    DialogWindow(onCloseRequest = { visible = false }, title = "标题", visible = visible) {
                        Text("内容")
                    }
                }

                Component.ContextMenu -> {
                    // 上下文菜单区块
                    ContextMenuArea(items = {
                        listOf(
                            ContextMenuItem("User-defined action") {
                                // Custom action
                            },
                            ContextMenuItem("Another user-defined action") {
                                // Another custom action
                            }
                        )
                    }) {
                        // Blue box where context menu will be available
                        Box(modifier = Modifier.background(Color.Blue).height(100.dp).width(100.dp))
                    }
                }

                Component.Example -> AppBarExamples{}
                Component.Chip -> {
                    // 其他示例区块
                    ChipExamples()
                }
                Component.DatePickers -> DatePickerExamples()
                Component.Divider -> DividerExamples()
                Component.Badges -> BadgeExamples()
                Component.BottomSheet -> PartialBottomSheet()
                Component.Checkbox -> CheckboxExamples()
                Component.FloatingActionButton -> FloatingActionButtonExamples()
                Component.Scaffold -> ScaffoldExample()
                Component.SegmentedButton -> SegmentedButtonExamples()
                Component.Slider -> SliderExamples()
                Component.Switch -> SwitchExamples()
                Component.SwipeToDismissBox -> SwipeToDismissBoxExamples()
                Component.TimePickers -> TimePickerExamples()
                Component.Tooltips -> TooltipExamples()
            }



        }
    }
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

@Composable
fun OutlinedCardExample() {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .size(width = 240.dp, height = 100.dp)
    ) {
        Text(
            text = "Outlined",
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
        )
    }
}

