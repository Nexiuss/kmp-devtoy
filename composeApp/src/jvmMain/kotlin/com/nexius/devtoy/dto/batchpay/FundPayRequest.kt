package com.nexius.devtoy.dto.batchpay

import java.lang.StringBuilder

class FundPayRequest {

    var requestHead: RequestHeadDTO? = null

    var requestBody: FundPayRequestDTO? = null
    fun toXml(): String {
        val StringBuilder =
            StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:bean=\"http://service.ccic.com/fund/fundPay/bean\" xmlns:bean1=\"http://service.ccic.com/common/bean\">\n")
                .append("   <soapenv:Header/>\n")
                .append("   <soapenv:Body>\n")
                .append("      <bean:fundPayRequest>")
                .append(requestHead!!.toXml()).append("\n")
                .append(requestBody!!.toXml()).append("\n")
                .append("      </bean:fundPayRequest>\n")
                .append("   </soapenv:Body>\n")
                .append("</soapenv:Envelope>")
        return StringBuilder.toString().replace("\\{(.*?)\\}".toRegex(), "")
    }
}
