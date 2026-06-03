package com.nexius.devtoy.utils

object Ascii85File {

    private const val PREFIX = "ascii85:"
    private const val DATA_MARKER = ";data:"

    /**
     * 编码：文件二进制 + 类型 + 文件名 → 可复制ASCII85文本
     * @param data 文件原始字节
     * @param mimeType 文件MIME类型，如 image/png, application/pdf, application/octet-stream
     * @param fileName 文件名（可选）
     */
    fun encode(
        data: ByteArray,
        mimeType: String = "application/octet-stream",
        fileName: String? = null
    ): String {
        val ascii85 = Ascii85.encode(data)
        return buildString {
            append(PREFIX)
            append(mimeType)
            if (!fileName.isNullOrBlank()) {
                append(";name=")
                append(fileName.replace(";", "_")) // 安全过滤
            }
            append(DATA_MARKER)
            append(ascii85)
        }
    }

    /**
     * 解码：带格式的ASCII85文本 → 文件信息 + 原始二进制
     */
    fun decode(encodedText: String): DecodedFile {
        require(encodedText.startsWith(PREFIX)) { "不是有效的Ascii85文件格式" }

        val withoutPrefix = encodedText.removePrefix(PREFIX)
        val dataSplit = withoutPrefix.split(DATA_MARKER)
        require(dataSplit.size == 2) { "数据格式损坏" }

        val metaPart = dataSplit[0]
        val ascii85Data = dataSplit[1]
        val metaParts = metaPart.split(";")

        var mimeType = metaParts[0]
        var fileName: String? = null

        for (part in metaParts.drop(1)) {
            if (part.startsWith("name=")) {
                fileName = part.removePrefix("name=")
            }
        }

        val rawBytes = Ascii85.decode(ascii85Data)
        return DecodedFile(
            data = rawBytes,
            mimeType = mimeType,
            fileName = fileName
        )
    }

    /**
     * 解码结果：包含原始文件所有信息
     */
    data class DecodedFile(
        val data: ByteArray,
        val mimeType: String,
        val fileName: String?
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            other as DecodedFile
            return data.contentEquals(other.data)
                    && mimeType == other.mimeType
                    && fileName == other.fileName
        }

        override fun hashCode(): Int {
            var result = data.contentHashCode()
            result = 31 * result + mimeType.hashCode()
            result = 31 * result + (fileName?.hashCode() ?: 0)
            return result
        }
    }

    // ================================
    // 内部标准Ascii85编解码（无依赖）
    // ================================
    private object Ascii85 {
        fun encode(data: ByteArray): String {
            if (data.isEmpty()) return ""
            val sb = StringBuilder()
            var index = 0
            val len = data.size

            while (index < len) {
                var chunk: Long = 0
                var chunkLen = 0

                for (i in 0 until 4) {
                    chunk = chunk shl 8
                    if (index + i < len) {
                        chunk = chunk or (data[index + i].toLong() and 0xFF)
                        chunkLen++
                    }
                }
                index += 4

                val chars = CharArray(5)
                for (i in 4 downTo 0) {
                    chars[i] = '!' + (chunk % 85).toInt()
                    chunk /= 85
                }
                sb.appendRange(chars, 0, chunkLen + 1)
            }
            return sb.toString()
        }

        fun decode(encoded: String): ByteArray {
            val clean = encoded.filter { !it.isWhitespace() }
            if (clean.isEmpty()) return byteArrayOf()
            val output = mutableListOf<Byte>()
            var index = 0
            val len = clean.length

            while (index < len) {
                var value: Long = 0
                var chunkLen = 0

                for (i in 0 until 5) {
                    value *= 85
                    if (index + i < len) {
                        val c = clean[index + i]
                        value += (c.code - '!'.code)
                        chunkLen++
                    }
                }
                index += 5

                val bytes = byteArrayOf(
                    ((value shr 24) and 0xFF).toByte(),
                    ((value shr 16) and 0xFF).toByte(),
                    ((value shr 8) and 0xFF).toByte(),
                    (value and 0xFF).toByte()
                )

                val take = when (chunkLen) {
                    1 -> 1
                    2 -> 2
                    3 -> 3
                    else -> 4
                }
                output.addAll(bytes.take(take))
            }
            return output.toByteArray()
        }
    }
}