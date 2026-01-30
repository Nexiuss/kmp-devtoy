package com.nexius.devtoy.utils

import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.Cipher
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

object DES {
    private const val Algorithm = "DESede" //定义 加密算法,可用 DES,DESede,Blowfish

    /**
     *
     * @param keybyte 为加密密钥，长度为24字节
     * @param src 为被加密的数据缓冲区（源）
     * @return
     */
    fun encryptMode(keybyte: ByteArray?, src: ByteArray?): ByteArray? {
        try {
            //生成密钥
            val deskey: SecretKey = SecretKeySpec(keybyte, Algorithm)

            //加密
            val c1 = Cipher.getInstance(Algorithm)
            c1.init(Cipher.ENCRYPT_MODE, deskey)
            //   new BASE64Encoder().encode(endata);   
            return c1.doFinal(src)
        } catch (e1: NoSuchAlgorithmException) {
            e1.printStackTrace()
        } catch (e2: NoSuchPaddingException) {
            e2.printStackTrace()
        }
        return null
    }

    /**
     *
     * @param keybyte 加密密钥，长度为24字节
     * @param src 加密后的缓冲区
     * @return
     */
    fun decryptMode(keybyte: ByteArray?, src: ByteArray?): ByteArray? {
        try {
            //生成密钥
            val deskey: SecretKey = SecretKeySpec(keybyte, Algorithm)
            //解密
            val c1 = Cipher.getInstance(Algorithm)
            c1.init(Cipher.DECRYPT_MODE, deskey)
            return c1.doFinal(src)
        } catch (e1: NoSuchAlgorithmException) {
            e1.printStackTrace()
        } catch (e2: NoSuchPaddingException) {
            e2.printStackTrace()
        }
        return null
    }

    //转换成十六进制字符串
    fun byte2hex(b: ByteArray): String {
        var hs = ""
        var stmp = ""
        for (n in b.indices) {
            stmp = Integer.toHexString(b[n].toInt() and 0XFF)
            hs = if (stmp.length == 1) hs + "0" + stmp else hs + stmp
            if (n < b.size - 1) hs = "$hs:"
        }
        return hs.uppercase(Locale.getDefault())
    }

    @JvmStatic
    fun main(args: Array<String>) {
        //添加新安全算法,如果用JCE就要把它添加进去
        //    Security.addProvider(new com.sun.crypto.provider.SunJCE());
        val keyBytes = byteArrayOf(
            0x11,
            0x22,
            0x4F,
            0x58,
            0x88.toByte(),
            0x10,
            0x40,
            0x38,
            0x28,
            0x25,
            0x79,
            0x51,
            0xCB.toByte(),
            0xDD.toByte(),
            0x55,
            0x66,
            0x77,
            0x29,
            0x74,
            0x98.toByte(),
            0x30,
            0x40,
            0x36,
            0xE2.toByte()
        ) //24字节的密钥
        val szSrc = "100.0"
        println("加密前的字符串:$szSrc")
        // new String(DES.encryptMode("111111111111111111111111".getBytes(), "100.0".getBytes()))
        val encoded = encryptMode("111111111111111111111111".encodeToByteArray(), szSrc.encodeToByteArray())
        println("加密后的字符串:" + String(encoded!!))
        val srcBytes = decryptMode("111111111111111111111111".encodeToByteArray(), encoded)
        println("解密后的字符串:" + String(srcBytes!!))
    }
}
