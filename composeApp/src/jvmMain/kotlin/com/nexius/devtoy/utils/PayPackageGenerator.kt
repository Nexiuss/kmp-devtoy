package com.nexius.devtoy.utils

import cn.hutool.core.util.XmlUtil
import com.nexius.devtoy.dto.batchpay.FundPayRequest
import com.nexius.devtoy.dto.batchpay.FundPayRequestDetailDTO
import org.dom4j.Document
import org.dom4j.io.OutputFormat
import org.dom4j.io.XMLWriter
import java.io.IOException
import java.io.StringWriter
import java.math.BigDecimal
import java.nio.charset.StandardCharsets
import java.text.DecimalFormat
import java.util.*

/**
 * 付款报文生成器
 *
 * @author xuy1083
 * @date 2023/05/26 17:29
 */
object PayPackageGenerator {
    private val format = DecimalFormat("0.00")

    /**
     * 获取整体校验码 赋值 requestBody/verifyField节点
     * @param totalCount 总笔数
     * @param totalamount 总金额保留2位小数 如 100.00
     * @return
     */
    fun getTotalVerifyField(totalCount: Int, totalamount: BigDecimal?): String? {
        return MD5.getMD5ofStr(totalCount.toString() + "" + format.format(totalamount))
    }

    /**
     * 生成明细校验 赋值 requestBody/requestDetails/verifyField 节点
     * @param recAccount
     * @param amount
     * @return
     */
    fun getDetailVerifyField(recAccount: String, amount: BigDecimal?): String? {
        return MD5.getMD5ofStr(recAccount + format.format(amount))
    }

    /**
     * 用于将 RecAccount、Amount加密并base64编码
     * @param src
     * @return
     */
    fun encryptAndBase64Encode(src: String): String {
        val key = "201106101929929101601102"
        val encoded = DES.encryptMode(key.encodeToByteArray(), src.toByteArray(StandardCharsets.UTF_8))
        return Base64.getEncoder().encodeToString(encoded)
    }

    fun decryptAndBase64Dncode(src: String): String{
        val key = "201106101929929101601102"
        var decode = Base64.getDecoder().decode(src)
        var decryptMode = DES.decryptMode(key.encodeToByteArray(), decode)

        return decryptMode?.let { String(decryptMode) }.orEmpty()
    }

    fun toXml(request: FundPayRequest): String {
        return request.toXml()
    }

    fun calcuteTotalAmount(requestDetails: List<FundPayRequestDetailDTO?>): BigDecimal {
        var decimal = BigDecimal(0)
        for (requestDetail in requestDetails) {
            val amount = validationDes(Base64.getDecoder().decode(requestDetail!!.amount))
            decimal = decimal.add(BigDecimal(amount))
        }
        return decimal
    }

    fun validationDes(exp: ByteArray?): String? {
        if (exp == null) return "0"
        val key = "201106101929929101601102"
        val encoded = DES.decryptMode(key.encodeToByteArray(), exp) ?: return null
        return String(encoded)
    }

    fun encrypt(exp: ByteArray?): String? {
        val key = "201106101929929101601102"
        val encoded = DES.encryptMode(key.encodeToByteArray(), exp) ?: return null
        return String(encoded)
    }

    fun formatXml(document: Document?): String {
        val format = OutputFormat()
        format.setIndent(false)
        format.isNewlines = true
        format.indent = " "
        return try {
            val out = StringWriter()
            val writer = XMLWriter(out, format)
            writer.write(document)
            writer.flush()
            writer.close()
            out.toString()
        } catch (e: IOException) {
            ""
        }
    }

    fun prettyXml(document: Document): String?{
        var asXML = document.asXML()
        return return asXML.let {
            var document = XmlUtil.parseXml(asXML)
            return XmlUtil.toStr(document)
        }
        return null

    }
}
