import org.jetbrains.compose.desktop.application.dsl.TargetFormat
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    //alias(libs.plugins.sqldelight)
}


kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            api(compose.ui)
            api(compose.material)
            api(compose.material3)
            api(compose.materialIconsExtended)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.composeIcons.fontAwesome)
            implementation(libs.composeIcons.feather)
            implementation(libs.composeUI.carbon)
            implementation(libs.zxing.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.jetbrains.markdown)
            implementation(libs.coil.compose)
            implementation(libs.richeditor.compose)

            // Coil
            implementation(libs.coil.compose)
            implementation(libs.coil.svg)
            implementation(libs.coil.network.ktor)

            // Ktor
            implementation(libs.ktor.client.core)

            // Navigation
            implementation(libs.navigation.compose)


            // SQLite JDBC驱动
            implementation(libs.sqlite.jdbc)
            // SQLDelight核心库
            //implementation(sqldelight.runtime)
            //implementation(sqldelight.sqliteDriver)
            //implementation(sqldelight.coroutinesExtensions)

            // 协程
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

            // 数据结构
            implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")
            implementation("commons-net:commons-net:3.9.0") // JVM FTP Client
            implementation("com.jcraft:jsch:0.1.55") // SFTP用
            implementation("com.mohamedrejeb.calf:calf-file-picker:0.9.0")
        }
        commonMain{
            resources.srcDirs("src/jvmMain/resources")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
            implementation(libs.ktor.client.cio)
        }

    }
}


compose.desktop {
    application {
        mainClass = "com.nexius.devtoy.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.nexius.devtoy"
            packageVersion = "1.0.1"
        }
    }
}
