package com.nexius.devtoy.dto.batchpay

class RequestDetailExtendInfoDTO {

    var key: String? = null

    var value: String? = null

    fun toXml(): String {
        var xmlTemplete = "\t\t\t\t\t<!--Zero or more repetitions:-->\n" +
                "\t\t\t\t\t<bean:extendInfoDTO>\n" +
                "\t\t\t\t\t\t<bean:key>${key}</bean:key>\n" +
                "\t\t\t\t\t\t<bean:value>${value}</bean:value>\n" +
                "\t\t\t\t\t</bean:extendInfoDTO>\n"
        return xmlTemplete;
    }
}
