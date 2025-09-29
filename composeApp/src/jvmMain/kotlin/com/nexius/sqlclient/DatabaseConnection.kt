package com.nexius.sqlclient

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// 数据库类型枚举
enum class DatabaseType {
    SQLITE, MYSQL, POSTGRESQL, H2
}

// 数据库连接配置
data class DatabaseConnection(
    val name: String,
    val type: DatabaseType,
    val host: String? = null,
    val port: Int? = null,
    val databaseName: String,
    val username: String? = null,
    val password: String? = null,
    val filePath: String? = null, // 用于文件型数据库
    val isConnected: Boolean = false
)

// 数据库连接管理器
class DatabaseConnectionManager {
    private val _connections = MutableStateFlow<List<DatabaseConnection>>(emptyList())
    val connections: StateFlow<List<DatabaseConnection>> = _connections.asStateFlow()
    
    private val _activeConnection = MutableStateFlow<DatabaseConnection?>(null)
    val activeConnection: StateFlow<DatabaseConnection?> = _activeConnection.asStateFlow()
    
    // 添加新连接
    fun addConnection(connection: DatabaseConnection) {
        _connections.value = _connections.value + connection
    }
    
    // 更新连接状态
    fun updateConnectionStatus(connection: DatabaseConnection, isConnected: Boolean) {
        val updated = connection.copy(isConnected = isConnected)
        _connections.value = _connections.value.map { 
            if (it.name == connection.name) updated else it 
        }
    }
    
    // 设置活动连接
    fun setActiveConnection(connection: DatabaseConnection) {
        _activeConnection.value = connection
    }
    
    // 关闭连接
    fun closeConnection(connection: DatabaseConnection) {
        updateConnectionStatus(connection, false)
        if (_activeConnection.value?.name == connection.name) {
            _activeConnection.value = null
        }
    }
}
