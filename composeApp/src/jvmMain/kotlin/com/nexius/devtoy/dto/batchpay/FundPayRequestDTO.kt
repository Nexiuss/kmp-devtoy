package com.nexius.devtoy.dto.batchpay

class FundPayRequestDTO {

    var batchSeqID: String? = null

    var requestTime: String? = null

    var totalAmount = 0.0

    var totalCount = 0

    var verifyField: String? = null
    protected var requestDetails: List<FundPayRequestDetailDTO>? = null





    fun toXml(): String {
        val requestDetailsXml = StringBuilder("");
        for (requestDetail in requestDetails!!) {
            requestDetailsXml.append(requestDetail.toXml())
        }

        val xmlTemplete = """<bean:requestBody>
            <bean:batchSeqID>${batchSeqID}</bean:batchSeqID>
            <bean:requestTime>${requestTime}</bean:requestTime>
            <bean:totalAmount>${totalAmount}</bean:totalAmount>
            <bean:totalCount>${totalCount}</bean:totalCount>
            <bean:verifyField>${verifyField}</bean:verifyField>
            <!--1 or more repetitions:-->
              ${requestDetailsXml}
         </bean:requestBody>"""
        return xmlTemplete;
    }
}
