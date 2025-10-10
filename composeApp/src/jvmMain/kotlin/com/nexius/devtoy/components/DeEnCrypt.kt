package com.nexius.devtoy.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cn.hutool.core.map.MapUtil
import com.nexius.devtoy.utils.PayPackageGenerator
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronsLeft
import compose.icons.feathericons.ChevronsRight
import org.dom4j.Document
import org.dom4j.DocumentHelper
import org.dom4j.XPath
import java.math.BigDecimal

@Composable
fun ccicPayDecrypt() {
    var ccicPkgL by remember { mutableStateOf("\n" +
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:bean=\"http://service.ccic.com/fund/fundPay/bean\" xmlns:bean1=\"http://service.ccic.com/common/bean\">\n" +
            "     <soapenv:Header></soapenv:Header>\n" +
            "     <soapenv:Body>\n" +
            "         <bean:fundPayRequest>\n" +
            "             <bean:requestHead>\n" +
            "                 <!--Optional:流水号-->\n" +
            "                 <bean1:seqNo></bean1:seqNo>\n" +
            "                 <!--Optional:消费方流水号-->\n" +
            "                 <bean1:consumerSeqNo></bean1:consumerSeqNo>\n" +
            "                 <!--必填:服务消费方ID-->\n" +
            "                 <bean1:consumerID>payment</bean1:consumerID>\n" +
            "                 <!--Optional:服务提供方ID-->\n" +
            "                 <bean1:providerID>ZJ2022</bean1:providerID>\n" +
            "                 <!--Optional:险类-->\n" +
            "                 <bean1:classCode></bean1:classCode>\n" +
            "                 <!--Optional:险种-->\n" +
            "                 <bean1:riskCode></bean1:riskCode>\n" +
            "                 <!--Optional:区域-->\n" +
            "                 <bean1:regionCode></bean1:regionCode>\n" +
            "                 <!--Optional:-->\n" +
            "                 <bean1:version></bean1:version>\n" +
            "             </bean:requestHead>\n" +
            "             <bean:requestBody>\n" +
            "                 <bean:batchSeqID>V052023050500001</bean:batchSeqID>\n" +
            "                 <bean:requestTime>20230505163406</bean:requestTime>\n" +
            "                 <bean:totalAmount>1.00</bean:totalAmount>\n" +
            "                 <bean:totalCount>1</bean:totalCount>\n" +
            "                 <bean:verifyField>AB0D74B0C55E37B44F1D5393AF1E307F</bean:verifyField>\n" +
            "                 <!--1 or more repetitions:-->\n" +
            "                 <bean:requestDetails>\n" +
            "                     <bean:accOrg>31015100</bean:accOrg>\n" +
            "                     <bean:amount>1.00</bean:amount>\n" +
            "                     <bean:applyDeptCode>31015104</bean:applyDeptCode>\n" +
            "                     <bean:bizRefCode>Q202300000030369</bean:bizRefCode>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:budgetItemCode></bean:budgetItemCode>\n" +
            "                     <bean:bzType>102</bean:bzType>\n" +
            "                     <bean:cardFlag>2</bean:cardFlag>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:categoryCode>20230426162507</bean:categoryCode>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:channelid>0101</bean:channelid>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:credentials></bean:credentials>\n" +
            "                     <bean:currency>CNY</bean:currency>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:cvvCode></bean:cvvCode>\n" +
            "                     <bean:dealType>0</bean:dealType>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:des></bean:des>\n" +
            "                     <bean:detailSeqID>V052023050500001000</bean:detailSeqID>\n" +
            "                     <bean:dsCode>1.2</bean:dsCode>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:email></bean:email>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:idCard></bean:idCard>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:insCode></bean:insCode>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:memo>赔款NDB34557697862CWUH20233101510400000120</bean:memo>\n" +
            "                     <bean:moneyWay>1</bean:moneyWay>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:payAccount>3301201231213213</bean:payAccount>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:payAccountName></bean:payAccountName>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:payBank></bean:payBank>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:payBankArea></bean:payBankArea>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:payBankLocation></bean:payBankLocation>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:payCurrency></bean:payCurrency>\n" +
            "                     <bean:payDate>2023-05-30</bean:payDate>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:payEntityCode></bean:payEntityCode>\n" +
            "                     <bean:payMoney>0.0</bean:payMoney>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:payName>万欣和（上海）企业服务有限公司</bean:payName>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:payObjectType></bean:payObjectType>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:phoneNum></bean:phoneNum>\n" +
            "                     <bean:recAccount>3301201231213213</bean:recAccount>\n" +
            "                     <bean:recAccountName>万欣和（上海）企业服务有限公司</bean:recAccountName>\n" +
            "                     <bean:recBank>中国工商银行股份有限公司</bean:recBank>\n" +
            "                     <bean:recBankArea>3100</bean:recBankArea>\n" +
            "                     <bean:recBankLocation>中国工商银行上海市分行营业部</bean:recBankLocation>\n" +
            "                     <bean:recName></bean:recName>\n" +
            "                     <bean:recObjectType>company</bean:recObjectType>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:reportnum></bean:reportnum>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:subCategoryCode></bean:subCategoryCode>\n" +
            "                     <bean:urgent>0</bean:urgent>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:useDes></bean:useDes>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:validDate></bean:validDate>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:vendorCode>0000000000</bean:vendorCode>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:foreignCurrency>N</bean:foreignCurrency>\n" +
            "                     <bean:verifyField>4DF986B78D9A35CE01473C913269EE26</bean:verifyField>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:recCountryCode></bean:recCountryCode>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:recAgentBankAccount></bean:recAgentBankAccount>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:recNameAddress></bean:recNameAddress>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:recBankAddress></bean:recBankAddress>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:costMode>1</bean:costMode>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:payMethod>4</bean:payMethod>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:transCode></bean:transCode>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:isBondedGoods></bean:isBondedGoods>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:nature></bean:nature>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:contractNo></bean:contractNo>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:invoiceNo></bean:invoiceNo>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:unitCode></bean:unitCode>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:remittanceInfor></bean:remittanceInfor>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:recAgentBankName></bean:recAgentBankName>\n" +
            "                     <!--Optional:-->\n" +
            "                     <bean:recAgentBankAddress></bean:recAgentBankAddress>\n" +
            "                     <!--Zero or more repetitions:-->\n" +
            "                     <bean:extendInfoDTO>\n" +
            "                         <bean:key>attribute4</bean:key>\n" +
            "                         <bean:value>WUH</bean:value>\n" +
            "                     </bean:extendInfoDTO>\n" +
            "                 </bean:requestDetails>\n" +
            "             </bean:requestBody>\n" +
            "         </bean:fundPayRequest>\n" +
            "     </soapenv:Body>\n" +
            " </soapenv:Envelope>\n") }
    var ccicPkgR by remember { mutableStateOf("")}
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 输入框区域
        androidx.compose.material3.OutlinedTextField(
            value = ccicPkgL,
            onValueChange = { ccicPkgL = it },
            modifier = Modifier.weight(1f),
            label = { Text("大地MD5明文") },
            singleLine = false  // 允许多行显示
        )

        Column {
            // 格式化按钮
            IconButton(
                onClick = {
                    // 尝试解析当前JSON字符串后重新格式化
                    ccicPkgR = ccicPkgL.ccicPayEncrypt()
                },
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Icon(
                    FeatherIcons.ChevronsRight,
                    contentDescription = "加密"
                )
            }
            IconButton(
                onClick = {
                    // 尝试解析当前JSON字符串后重新格式化
                    ccicPkgL = ccicPkgR.ccicPayDecrypt()
                },
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Icon(
                    FeatherIcons.ChevronsLeft,
                    contentDescription = "解密"
                )
            }
        }
        // 右输入框区域
        androidx.compose.material3.OutlinedTextField(
            value = ccicPkgR,
            onValueChange = { ccicPkgR = it },
            modifier = Modifier.weight(1f),
            label = { Text("大地MD5密文") },
            singleLine = false  // 允许多行显示
        )
    }
}

fun String.ccicPayEncrypt(
):String {
    val document: Document = DocumentHelper.parseText(this)
    val namespace: Map<String, String> =
        MapUtil.builder<String, String>().put("soapenv", "http://schemas.xmlsoap.org/soap/envelope/")
            .put("bean", "http://service.ccic.com/fund/fundPay/bean")
            .put("bean1", "http://service.ccic.com/common/bean").build()
    val requestBodyxPath: XPath = DocumentHelper.createXPath("//bean:requestBody")
    val detailxPath: XPath = DocumentHelper.createXPath("//bean:requestBody/bean:requestDetails")
    detailxPath.setNamespaceURIs(namespace)
    val requestBodyNode = requestBodyxPath.selectSingleNode(document)
    val nodes = detailxPath.selectNodes(document)
    var totalDecimal = BigDecimal(0)
    for (node in nodes) {
        val amountNode = node.selectSingleNode("bean:amount")
        val amountStr = amountNode.text
        val recAccountNode = node.selectSingleNode("bean:recAccount")
        val recAccountStr = recAccountNode.text
        val amountDecial = BigDecimal(amountStr)
        val detailVerifyField: String = PayPackageGenerator.getDetailVerifyField(recAccountStr, amountDecial)!!
        node.selectSingleNode("bean:verifyField").text = detailVerifyField
        amountNode.text = PayPackageGenerator.encryptAndBase64Encode(amountStr)
        recAccountNode.text = PayPackageGenerator.encryptAndBase64Encode(recAccountStr)
        totalDecimal = totalDecimal.add(amountDecial)
    }
    val totalCountNode = requestBodyNode.selectSingleNode("bean:totalCount")
    val totalAmountNode = requestBodyNode.selectSingleNode("bean:totalAmount")
    val totalVerifyFieldNode = requestBodyNode.selectSingleNode("bean:verifyField")
    val totalVerifyField: String = PayPackageGenerator.getTotalVerifyField(nodes.size, totalDecimal)!!
    totalVerifyFieldNode.text = totalVerifyField
    totalCountNode.text = nodes.size.toString() + ""
    totalAmountNode.text = totalDecimal.toString()
    return PayPackageGenerator.prettyXml(document) ?: ""
}

fun String.ccicPayDecrypt(
):String {
    val document: Document = DocumentHelper.parseText(this)
    val namespace: Map<String, String> =
        MapUtil.builder<String, String>().put("soapenv", "http://schemas.xmlsoap.org/soap/envelope/")
            .put("bean", "http://service.ccic.com/fund/fundPay/bean")
            .put("bean1", "http://service.ccic.com/common/bean").build()
    val detailxPath: XPath = DocumentHelper.createXPath("//bean:requestBody/bean:requestDetails")
    detailxPath.setNamespaceURIs(namespace)
    val nodes = detailxPath.selectNodes(document)
    for (node in nodes) {
        val amountNode = node.selectSingleNode("bean:amount")
        val amountStr = amountNode.text
        val recAccountNode = node.selectSingleNode("bean:recAccount")
        val recAccountStr = recAccountNode.text

        amountNode.text = PayPackageGenerator.decryptAndBase64Dncode(amountStr)
        recAccountNode.text = PayPackageGenerator.decryptAndBase64Dncode(recAccountStr)
    }
    return PayPackageGenerator.prettyXml(document) ?: ""
}