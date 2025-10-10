package com.nexius.devtoy.dto.batchpay

class RequestHeadDTO {

    var seqNo: String? = null

    var consumerSeqNo: String? = null

    var consumerID: String? = null

    var providerID: String? = null

    var classCode: String? = null

    var riskCode: String? = null

    var regionCode: String? = null

    var version: String? = null


    fun toXml(): String {
        val xmlTemplete = "<bean:requestHead>\n" +
                "\t\t\t\t<!--Optional:-->\n" +
                "\t\t\t\t<bean1:seqNo>${seqNo}</bean1:seqNo>\n" +
                "\t\t\t\t<!--Optional:-->\n" +
                "\t\t\t\t<bean1:consumerSeqNo>${consumerSeqNo}</bean1:consumerSeqNo>\n" +
                "\t\t\t\t<bean1:consumerID>${consumerID}</bean1:consumerID>\n" +
                "\t\t\t\t<!--Optional:-->\n" +
                "\t\t\t\t<bean1:providerID>${providerID}</bean1:providerID>\n" +
                "\t\t\t\t<!--Optional:-->\n" +
                "\t\t\t\t<bean1:classCode>${classCode}</bean1:classCode>\n" +
                "\t\t\t\t<!--Optional:-->\n" +
                "\t\t\t\t<bean1:riskCode>${riskCode}</bean1:riskCode>\n" +
                "\t\t\t\t<!--Optional:-->\n" +
                "\t\t\t\t<bean1:regionCode>${regionCode}</bean1:regionCode>\n" +
                "\t\t\t\t<!--Optional:-->\n" +
                "\t\t\t\t<bean1:version>${version}</bean1:version>\n" +
                "\t\t\t</bean:requestHead>"
        return xmlTemplete;
    }
}
