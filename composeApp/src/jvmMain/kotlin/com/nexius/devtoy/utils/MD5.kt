package com.nexius.devtoy.utils

import java.security.MessageDigest
import java.util.*

object MD5 {
    /**
     * 获得MD5加密密码的方法
     */
    fun getMD5ofStr(origString: String?): String? {
        println("md5前原始串：$origString")
        var origMD5: String? = null
        try {
            val md5 = MessageDigest.getInstance("MD5")
            val result = md5.digest(origString!!.encodeToByteArray())
            origMD5 = byteArray2HexStr(result)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return origMD5
    }

    /**
     * 处理字节数组得到MD5密码的方法
     */
    private fun byteArray2HexStr(bs: ByteArray): String {
        val sb = StringBuffer()
        for (b in bs) {
            sb.append(byte2HexStr(b))
        }
        return sb.toString()
    }

    /**
     * 字节标准移位转十六进制方法
     */
    private fun byte2HexStr(b: Byte): String {
        var hexStr: String? = null
        var n = b.toInt()
        if (n < 0) {
            //若需要自定义加密,请修改这个移位算法即可
            n = b.toInt() and 0x7F + 128
        }
        hexStr = Integer.toHexString(n / 16) + Integer.toHexString(n % 16)
        return hexStr.uppercase(Locale.getDefault())
    }

    /**
     * 提供一个MD5多次加密方法
     */
    fun getMD5ofStr(origString: String?, times: Int): String? {
        var md5 = getMD5ofStr(origString)
        for (i in 0 until times - 1) {
            md5 = getMD5ofStr(md5)
        }
        return getMD5ofStr(md5)
    }

    /**
     * 密码验证方法
     */
    fun verifyPassword(inputStr: String?, MD5Code: String): Boolean {
        return getMD5ofStr(inputStr) == MD5Code
    }

    /**
     * 重载一个多次加密时的密码验证方法
     */
    fun verifyPassword(inputStr: String?, MD5Code: String, times: Int): Boolean {
        return getMD5ofStr(inputStr, times) == MD5Code
    }

    /**
     * 提供一个测试的主函数
     */
    @JvmStatic
    fun main(args: Array<String>) {
        println("1112.0" + getMD5ofStr("1112.0"))
        //  System.out.println("123456789:" + getMD5ofStr("123456789"));
        //  System.out.println("sarin:" + getMD5ofStr("sarin"));
        //  System.out.println("123:" + getMD5ofStr("123", 4));
    }
}
