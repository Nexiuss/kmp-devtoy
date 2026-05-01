package com.nexius.devtoy.components.markitdown

import com.markitdown.config.ConversionOptions
import com.markitdown.converter.*
import com.markitdown.core.ConverterRegistry
import com.markitdown.core.MarkItDownEngine

object MarkitdownEngineFactory {
    fun createEngine(): MarkItDownEngine {
        val registry = ConverterRegistry()

        registry.registerConverter(PdfConverter())
        registry.registerConverter(DocxConverter())
        registry.registerConverter(DocConverter())
        registry.registerConverter(PptxConverter())
        registry.registerConverter(PptConverter())
        registry.registerConverter(XlsxConverter())
        registry.registerConverter(XlsConverter())
        registry.registerConverter(HtmlConverter())
        registry.registerConverter(TextConverter())
        registry.registerConverter(ImageConverter())

        val zipConverter = ZipConverter()
        zipConverter.setDelegate { stream, mime, opt ->
            val c = registry.getConverter(mime).orElse(null)
            c?.convert(stream, mime, opt)
        }
        registry.registerConverter(zipConverter)

        return MarkItDownEngine(registry)
    }

    fun createOptions(settings: ConvertSettings): ConversionOptions {
        return ConversionOptions.builder()
            .includeImages(settings.includeImages)
            .includeTables(settings.includeTables)
            .includeMetadata(settings.includeMetadata)
            .useOcr(settings.useOcr)
            .imageOutputDir(settings.imageOutputDir)
            .apply {
                if (settings.pdfPassword.isNotBlank()) {
                    customOption("pdfPassword", settings.pdfPassword)
                }
            }
            .build()
    }
}