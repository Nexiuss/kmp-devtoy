package com.nexius.devtoy.utils

import java.io.ByteArrayInputStream
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.charset.Charset
import java.security.*
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*

class Crypto {
    private fun getChars(bytes: ByteArray, encoding: String): CharArray {

        val cs = Charset.forName(encoding)

        val bb = ByteBuffer.allocate(bytes.size)
        bb.put(bytes)
        bb.flip()

        val cb = cs.decode(bb)
        return cb.array()
    }

    companion object {
        const val KEY_ALGORITHM = "RSA"
        const val SHA1_ALGORITHM = "SHA-1"
        const val SIGNATURE_ALGORITHM = "MD5withRSA"
        var decoder = Base64.getDecoder()
        var encoder = Base64.getEncoder()
        fun buildSortWaitSign(mapPars: Map<String?, String>, ExcludeBlankValue: Boolean): String {
            val sbPars = StringBuilder()
            val lsPars: MutableList<String> = ArrayList()
            val i: Iterator<*> = mapPars.keys.iterator()
            while (i.hasNext()) {
                val strKey = i.next().toString()
                lsPars.add(strKey)
            }
            val tmpPars = lsPars.toTypedArray<String>()
            Arrays.sort(tmpPars as Array<Any>)
            var integer = Integer.valueOf(0)
            while (integer < tmpPars.size) {
                val strKey = tmpPars[integer]
                var strValue = mapPars[strKey].toString()
                if (strValue == null || strValue.length == 0) {
                    if (ExcludeBlankValue) {
                        strValue = ""
                    } else {
                        integer = Integer.valueOf(integer + 1)
                        continue
                    }
                }
                if (integer == tmpPars.size - 1) {
                    sbPars.append(String.format("%s=%s", *arrayOf<Any>(strKey, strValue)))
                } else {
                    sbPars.append(String.format("%s=%s&", *arrayOf<Any>(strKey, strValue)))
                }
                integer = Integer.valueOf(integer + 1)
                continue
                integer = Integer.valueOf(integer + 1)
            }
            return sbPars.toString()
        }

        fun buildObjectSortWaitSign(mapPars: Map<String?, Any>, ExcludeBlankValue: Boolean): String {
            val sbPars = StringBuilder()
            val lsPars: MutableList<String> = ArrayList()
            val i: Iterator<*> = mapPars.keys.iterator()
            while (i.hasNext()) {
                val strKey = i.next().toString()
                lsPars.add(strKey)
            }
            val tmpPars = lsPars.toTypedArray<String>()
            Arrays.sort(tmpPars as Array<Any>)
            var integer = Integer.valueOf(0)
            while (integer < tmpPars.size) {
                val strKey = tmpPars[integer]
                var strValue = mapPars[strKey].toString()
                if (strValue == null || strValue.length == 0) {
                    if (ExcludeBlankValue) {
                        strValue = ""
                    } else {
                        integer = Integer.valueOf(integer + 1)
                        continue
                    }
                }
                if (sbPars.toString().length > 0) {
                    sbPars.append("&")
                }
                sbPars.append(String.format("%s=%s", *arrayOf<Any>(strKey, strValue)))
                integer = Integer.valueOf(integer + 1)
                continue
                integer = Integer.valueOf(integer + 1)
            }
            return sbPars.toString()
        }

        fun signObject(maps: Map<String?, Any>, key: String): String? {
            var decrypt = buildObjectSortWaitSign(maps, false)
            decrypt = "$decrypt&key=$key"
            val sbErr = StringBuilder()
            return try {
                val messageDigest = sign("SHA-1", decrypt, sbErr)
                bytesToHexString(messageDigest)
            } catch (e: Exception) {
                ""
            }
        }

        fun sign(algorithm: String?, waitSign: String, sbErr: StringBuilder): ByteArray? {
            try {

                val digest = MessageDigest.getInstance("SHA-1")
                digest.update(waitSign.toByteArray(charset("UTF-8")))

                return digest.digest()

            } catch (e: NoSuchAlgorithmException) {

                sbErr.append(String.format("鍙戠敓寮傚父锛�s", *arrayOf<Any>(e)))
            } catch (ex: Exception) {

                sbErr.append(String.format("寮傚父锛�s", *arrayOf<Any>(ex)))
            }
            return null
        }

        fun verifyObject(maps: Map<String?, Any>, key: String, serverSign: String?): Boolean {

            val strSign = signObject(maps, key)
            return if (strSign.equals(serverSign, ignoreCase = true)) {

                true
            } else false

        }

        @Throws(Exception::class)
        fun encrypt(data: ByteArray?, privateKey: String?): String {

            val dec = decoder.decode(privateKey)

            val pkcs8KeySpec = PKCS8EncodedKeySpec(dec)


            val keyFactory = KeyFactory.getInstance("RSA")


            val priKey = keyFactory.generatePrivate(pkcs8KeySpec)


            val signature = Signature.getInstance("MD5withRSA")
            signature.initSign(priKey)
            signature.update(data)

            return encoder.encodeToString(signature.sign())
        }

        fun hasAnyChar(p_str: String?): Boolean {

            return if (p_str != null && p_str.length > 0) {

                true
            } else false

        }

        fun checkPEM(paramArrayOfByte: ByteArray): ByteArray? {

            val str1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/+= \r\n-"
            for (i in paramArrayOfByte.indices) {

                if (str1.indexOf(Char(paramArrayOfByte[i].toUShort())) == -1) return null

            }
            val localStringBuffer = StringBuffer(
                paramArrayOfByte.size
            )

            val str2 = String(paramArrayOfByte)
            for (j in 0 until str2.length) {

                if (str2[j] != ' ' && str2[j] != '\r' && str2[j] != '\n') localStringBuffer.append(
                    str2[j]
                )

            }
            return localStringBuffer.toString().encodeToByteArray()
        }

        fun hexToByte(b: ByteArray?): ByteArray {

            var b = b
            require(b!!.size % 2 == 0) {

                "输入长度不是偶数"
            }

            val b2 = ByteArray(b.size / 2)

            var n = 0
            while (n < b.size) {


                val item = String(b, n, 2)

                b2[n / 2] = item.toInt(16).toByte()
                n += 2
            }
            b = null as ByteArray?
            return b2
        }

        fun bytesToHexString(p_bytes: ByteArray?): String? {

            if (p_bytes == null || p_bytes.size <= 0) {

                return null
            }

            val sb = StringBuilder()
            for (i in p_bytes.indices) {

                val each = p_bytes[i].toInt() and 0xFF

                val eachHex = Integer.toHexString(each)
                if (eachHex.length < 2) {

                    sb.append(0)
                }
                sb.append(eachHex)
            }
            return sb.toString()
        }

        fun signData(
            p_toSign: ByteArray?,
            p_ksType: String?,
            p_algorithm: String?,
            p_ksPath: String?,
            p_kPass: String,
            p_sbLog: StringBuilder
        ): ByteArray? {
            return try {

                val ks = KeyStore.getInstance(p_ksType)

                var fis: FileInputStream? = FileInputStream(p_ksPath)

                var keyPassword: CharArray? = null
                if (hasAnyChar(p_kPass)) {

                    keyPassword = p_kPass.toCharArray()
                }
                ks.load(fis, keyPassword)
                fis!!.close()
                fis = null


                val enumAlias = ks.aliases()

                var keyAlias: String? = null
                if (enumAlias.hasMoreElements()) {

                    keyAlias = enumAlias.nextElement()
                }

                val priKey = ks.getKey(keyAlias, keyPassword) as PrivateKey


                val signProvider = Signature.getInstance(p_algorithm)
                signProvider.initSign(priKey)
                signProvider.update(p_toSign)
                signProvider.sign()
            } catch (ex: Exception) {

                p_sbLog.append(ex.message)

                null
            }
        }

        fun getPublicKey(p_ksType: String?, p_ksPath: String?, p_kPass: String, p_sbLog: StringBuilder): String {
            return try {

                val ks = KeyStore.getInstance(p_ksType)

                var fis: FileInputStream? = FileInputStream(p_ksPath)

                var keyPassword: CharArray? = null
                if (hasAnyChar(p_kPass)) {

                    keyPassword = p_kPass.toCharArray()
                }
                ks.load(fis, keyPassword)
                fis!!.close()
                fis = null


                val enumAlias = ks.aliases()

                var keyAlias: String? = null
                if (enumAlias.hasMoreElements()) {

                    keyAlias = enumAlias.nextElement()
                }

                val cer = ks.getCertificate(keyAlias)

                val pk = cer.publicKey
                encoder.encodeToString(pk.encoded)

            } catch (ex: Exception) {

                p_sbLog.append(ex.message)

                ""
            }
        }

        fun getBytePublicKey(
            p_ksType: String?, p_ksPath: String?, p_kPass: String, p_sbLog: StringBuilder
        ): ByteArray? {
            return try {

                val ks = KeyStore.getInstance(p_ksType)

                var fis: FileInputStream? = FileInputStream(p_ksPath)

                var keyPassword: CharArray? = null
                if (hasAnyChar(p_kPass)) {

                    keyPassword = p_kPass.toCharArray()
                }
                ks.load(fis, keyPassword)
                fis!!.close()
                fis = null


                val enumAlias = ks.aliases()

                var keyAlias: String? = null
                if (enumAlias.hasMoreElements()) {

                    keyAlias = enumAlias.nextElement()
                }

                val cer = ks.getCertificate(keyAlias)
                cer.encoded

            } catch (ex: Exception) {

                p_sbLog.append(ex.message)

                null
            }
        }

        fun verifyMD5(waitSign: String, checkValue: String, encoding: String?, sbErr: StringBuilder): Boolean {

            val tmpSign = md5ToHexString(waitSign, encoding, sbErr)
            return if (checkValue.equals(tmpSign, ignoreCase = true)) {

                true
            } else false

        }

        fun md5ToHexString(waitSign: String, encoding: String?, sbErr: StringBuilder): String? {

            return bytesToHexString(md5(waitSign, encoding, sbErr))
        }

        fun md5(waitSign: String, encoding: String?, sbErr: StringBuilder): ByteArray? {
            try {

                val digest = MessageDigest.getInstance("MD5")
                digest.update(waitSign.toByteArray(charset(encoding!!)))

                return digest.digest()

            } catch (e: NoSuchAlgorithmException) {
                sbErr.append(String.format("发生异常：%s", *arrayOf<Any>(e)))
            } catch (ex: Exception) {
                sbErr.append(String.format("异常：%s", *arrayOf<Any>(ex)))
            }
            return null
        }

        fun getPublicKey(path: String?, sbErr: StringBuilder): PublicKey? {

            var cf: CertificateFactory? = null

            var `in`: FileInputStream? = null
            return try {

                cf = CertificateFactory.getInstance("X.509")
                `in` = FileInputStream(path)

                val verifyCert = cf.generateCertificate(`in`) as X509Certificate

                verifyCert.publicKey

            } catch (ex: Exception) {

                sbErr.append(
                    String.format(
                        "获取公钥异常：%s，信息：%s", *arrayOf<Any?>(ex.message, ex.stackTrace)
                    )
                )

                null
            }
        }

        @Throws(SecurityException::class)
        fun getCertFromHexString(hexCert: String): X509Certificate? {
            val cert = hexToByte(hexCert.encodeToByteArray())
            var cierto: X509Certificate? = null
            ByteArrayInputStream(cert).use { bIn->{
                val fact = CertificateFactory.getInstance("X.509")
                cierto = fact.generateCertificate(bIn) as X509Certificate
            } }

            return cierto
        }

        fun sign(
            signtype: String?,
            strParameter: String,
            encoding: String?,
            keytype: String?,
            algorithm: String?,
            priKey: String?,
            priKeypass: String,
            sbErr: StringBuilder
        ): ByteArray? {

            var tmpSign: ByteArray? = null
            try {

                if (S3SigntypeEnum.MD5.value.equals(signtype)) {

                    tmpSign = md5(strParameter, encoding, sbErr)

                } else if (S3SigntypeEnum.RSA.value.equals(signtype)) {

                    tmpSign = signData(
                        strParameter.toByteArray(charset(encoding!!)), keytype, algorithm, priKey, priKeypass, sbErr
                    )
                }

            } catch (ex: Exception) {

                sbErr.append(ex.message)
            }
            return tmpSign
        }

        fun verify(
            returnInfoByte: ByteArray?,
            serverSignByte: ByteArray?,
            serverCert: String?,
            algorithm: String?,
            encoding: String?,
            sbErr: StringBuilder
        ): Boolean {
            try {

                val publicKey = getPublicKey(serverCert, sbErr)

                val signet = Signature.getInstance(algorithm)
                signet.initVerify(publicKey)
                signet.update(returnInfoByte)
                return signet.verify(serverSignByte)

            } catch (e: NoSuchAlgorithmException) {
                sbErr.append(e.message)
            } catch (e: InvalidKeyException) {
                sbErr.append(e.message)
            } catch (e: SignatureException) {

                sbErr.append(e.message)
            }
            return false
        }
    }
}
