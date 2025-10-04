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
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.composeIcons.fontAwesome)
            implementation(libs.composeIcons.feather)
            implementation(libs.composeUI.carbon)
            implementation(libs.zxing.core)
            implementation(libs.kotlinx.serialization.json)




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
        }

    }
}


compose.desktop {
    application {
        mainClass = "com.nexius.devtoy.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.nexius.devtoy"
            packageVersion = "1.0.0"
        }
    }
}
