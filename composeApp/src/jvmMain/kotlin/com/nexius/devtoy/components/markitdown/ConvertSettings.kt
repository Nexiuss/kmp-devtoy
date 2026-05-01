package com.nexius.devtoy.components.markitdown

data class ConvertSettings(
    val includeImages: Boolean = true,
    val includeTables: Boolean = true,
    val includeMetadata: Boolean = true,
    val useOcr: Boolean = false,
    val pdfPassword: String = "",
    val imageOutputDir: String = "assets"
)