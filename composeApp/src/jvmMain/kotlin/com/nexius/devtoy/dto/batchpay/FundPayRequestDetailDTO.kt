package com.nexius.devtoy.dto.batchpay

import java.lang.StringBuilder

class FundPayRequestDetailDTO {

    var accOrg: String? = null

    var amount: String? = null

    var applyDeptCode: String? = null

    var bizRefCode: String? = null

    var budgetItemCode: String? = null

    var bzType: String? = null

    var cardFlag = 0

    var categoryCode: String? = null

    var channelid: String? = null

    var credentials: String? = null

    var currency: String? = null

    lateinit var cvvCode: ByteArray

    var dealType = 0

    var des: String? = null

    var detailSeqID: String? = null

    var dsCode: String? = null

    var email: String? = null

    lateinit var idCard: ByteArray

    var insCode: String? = null

    var memo: String? = null

    var moneyWay = 0

    var payAccount: String? = null

    var payAccountName: String? = null

    var payBank: String? = null

    var payBankArea: String? = null

    var payBankLocation: String? = null

    var payCurrency: String? = null
    var payDate: String? = null

    var payEntityCode: String? = null

    var payMoney = 0.0

    var payName: String? = null

    var payObjectType: String? = null

    var phoneNum: String? = null

    var recAccount: String? = null

    var recAccountName: String? = null

    var recBank: String? = null

    var recBankArea: String? = null

    var recBankLocation: String? = null

    var recName: String? = null

    var recObjectType: String? = null

    lateinit var reportnum: ByteArray

    var subCategoryCode: String? = null

    var urgent = 0

    var useDes: String? = null

    var validDate: String? = null

    var vendorCode: String? = null

    var foreignCurrency: String? = null

    var verifyField: String? = null

    var recCountryCode: String? = null

    var recAgentBankAccount: String? = null

    var recNameAddress: String? = null

    var recBankAddress: String? = null

    var costMode: String? = null

    var payMethod: String? = null

    var transCode: String? = null

    var isBondedGoods: String? = null

    var nature: String? = null

    var contractNo: String? = null

    var invoiceNo: String? = null

    var unitCode: String? = null

    var remittanceInfor: String? = null

    var recAgentBankName: String? = null

    var recAgentBankAddress: String? = null
    protected var extendInfoDTO: List<RequestDetailExtendInfoDTO>? = null



    private val xmlTemplete = "<bean:requestDetails>\n" +
            "\t\t\t\t\t<bean:accOrg>${accOrg}</bean:accOrg>\n" +
            "\t\t\t\t\t<bean:amount>${amount}</bean:amount>\n" +
            "\t\t\t\t\t<bean:applyDeptCode>${applyDeptCode}</bean:applyDeptCode>\n" +
            "\t\t\t\t\t<bean:bizRefCode>${bizRefCode}</bean:bizRefCode>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:budgetItemCode/>\n" +
            "\t\t\t\t\t<bean:bzType>${bzType}</bean:bzType>\n" +
            "\t\t\t\t\t<bean:cardFlag>${cardFlag}</bean:cardFlag>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:categoryCode>${categoryCode}</bean:categoryCode>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:channelid>${channelid}</bean:channelid>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:credentials/>\n" +
            "\t\t\t\t\t<bean:currency>${currency}</bean:currency>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:cvvCode/>\n" +
            "\t\t\t\t\t<bean:dealType>${dealType}</bean:dealType>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:des/>\n" +
            "\t\t\t\t\t<bean:detailSeqID>${detailSeqID}</bean:detailSeqID>\n" +
            "\t\t\t\t\t<bean:dsCode>${dsCode}</bean:dsCode>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:email/>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:idCard/>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:insCode>${insCode}</bean:insCode>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:memo>${memo}</bean:memo>\n" +
            "\t\t\t\t\t<bean:moneyWay>${moneyWay}</bean:moneyWay>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:payAccount>${payAccount}</bean:payAccount>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:payAccountName>${payAccountName}</bean:payAccountName>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:payBank>${payBank}</bean:payBank>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:payBankArea>${payBankArea}</bean:payBankArea>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:payBankLocation>${payBankLocation}</bean:payBankLocation>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:payCurrency>${payCurrency}</bean:payCurrency>\n" +
            "\t\t\t\t\t<bean:payDate>${payDate}</bean:payDate>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:payEntityCode>${payEntityCode}</bean:payEntityCode>\n" +
            "\t\t\t\t\t<bean:payMoney>${payMoney}</bean:payMoney>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:payName>${payName}</bean:payName>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:payObjectType>${payObjectType}</bean:payObjectType>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:phoneNum/>\n" +
            "\t\t\t\t\t<bean:recAccount>${recAccount}</bean:recAccount>\n" +
            "\t\t\t\t\t<bean:recAccountName>${recAccountName}</bean:recAccountName>\n" +
            "\t\t\t\t\t<bean:recBank>${recBank}</bean:recBank>\n" +
            "\t\t\t\t\t<bean:recBankArea>${recBankArea}</bean:recBankArea>\n" +
            "\t\t\t\t\t<bean:recBankLocation>${recBankLocation}</bean:recBankLocation>\n" +
            "\t\t\t\t\t<bean:recName>${recName}</bean:recName>\n" +
            "\t\t\t\t\t<bean:recObjectType>${recObjectType}</bean:recObjectType>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:reportnum>${reportnum}</bean:reportnum>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:subCategoryCode>${subCategoryCode}</bean:subCategoryCode>\n" +
            "\t\t\t\t\t<bean:urgent>${urgent}</bean:urgent>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:useDes>${useDes}</bean:useDes>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:validDate></bean:validDate>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:vendorCode>${vendorCode}</bean:vendorCode>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:foreignCurrency>N</bean:foreignCurrency>\n" +
            "\t\t\t\t\t<bean:verifyField>${verifyField}</bean:verifyField>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:recCountryCode>${recCountryCode}</bean:recCountryCode>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:recAgentBankAccount>${recAgentBankAccount}</bean:recAgentBankAccount>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:recNameAddress>${recNameAddress}</bean:recNameAddress>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:recBankAddress>${recBankAddress}</bean:recBankAddress>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:costMode>${costMode}</bean:costMode>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:payMethod>${payMethod}</bean:payMethod>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:transCode>${transCode}</bean:transCode>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:isBondedGoods/>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:nature/>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:contractNo/>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:invoiceNo/>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:unitCode/>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:remittanceInfor/>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:recAgentBankName/>\n" +
            "\t\t\t\t\t<!--Optional:-->\n" +
            "\t\t\t\t\t<bean:recAgentBankAddress/>\n" +
            "${toXml(extendInfoDTO!!)}" +
            "\t\t\t\t</bean:requestDetails>"

    fun toXml(dtos:List<RequestDetailExtendInfoDTO>): String{
        var xml = StringBuilder()
        for (dto in dtos) {
            xml.append(dto.toXml())
        }
        return xml.toString()
    }
    fun toXml(): String {
        return xmlTemplete
    }

}
