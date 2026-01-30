package com.nexius.devtoy.components.popup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.autoSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.structuralEqualityPolicy

/**
 * 弹窗状态管理类
 * 封装弹窗的显示状态和文本内容
 */
@Immutable // 标记为不可变数据类（外部无法直接修改内部状态）
class PopupState(
    // 内部可变状态，使用结构相等性策略
    private val _isShow: MutableState<Boolean> = mutableStateOf(false, structuralEqualityPolicy()),
    private val _text: MutableState<String> = mutableStateOf("", structuralEqualityPolicy())
) {
    // 对外暴露不可变的 State，保证状态安全
    val isShow: Boolean
        get() = _isShow.value

    val text: String
        get() = _text.value

    /**
     * 显示弹窗
     * @param text 弹窗显示的文本内容，默认空字符串
     */
    fun show(text: String = "") {
        _isShow.value = true
        _text.value = text
    }

    /**
     * 隐藏弹窗
     * 可选是否清空文本内容（默认清空）
     */
    fun dismiss(clearText: Boolean = true) {
        _isShow.value = false
        if (clearText) {
            _text.value = ""
        }
    }

    /**
     * 切换弹窗显示状态
     * @param text 如果切换为显示状态时的文本内容
     */
    fun toggle(text: String = this.text) {
        if (_isShow.value) {
            dismiss()
        } else {
            show(text)
        }
    }

    /**
     * 更新弹窗文本（不改变显示状态）
     */
    fun updateText(newText: String) {
        _text.value = newText
    }

    companion object {
        /**
         * Saver 序列化器，用于 rememberSaveable 持久化状态
         * 支持屏幕旋转等配置变更时恢复状态
         */
        val Saver: Saver<PopupState, *> = Saver(
            save = { state ->
                // 保存需要持久化的状态：显示状态和文本
                mapOf(
                    "isShow" to state.isShow,
                    "text" to state.text
                )
            },
            restore = { savedState ->
                // 从保存的状态中恢复
                val isShow = savedState["isShow"] as? Boolean ?: false
                val text = savedState["text"] as? String ?: ""
                PopupState(
                    _isShow = mutableStateOf(isShow, structuralEqualityPolicy()),
                    _text = mutableStateOf(text, structuralEqualityPolicy())
                )
            }
        )

        /**
         * 简化版 Saver，使用 autoSaver 自动序列化（适用于简单场景）
         * 如果不需要自定义序列化逻辑，可以使用这个
         */
        val AutoSaver: Saver<PopupState, *> = autoSaver()
    }
}

/**
 * 创建并记忆弹窗状态
 * 使用 rememberSaveable 确保配置变更（如旋转）时状态不丢失
 * @return 弹窗状态管理实例
 */
@Composable
fun rememberPopupState(): PopupState {
    // 使用自定义 Saver 持久化状态
    return rememberSaveable(saver = PopupState.Saver) {
        PopupState()
    }
}

// 可选：如果需要支持无参数的默认序列化，可以添加这个扩展
@Composable
fun rememberPopupStateWithAutoSaver(): PopupState {
    return rememberSaveable(saver = PopupState.AutoSaver) {
        PopupState()
    }
}