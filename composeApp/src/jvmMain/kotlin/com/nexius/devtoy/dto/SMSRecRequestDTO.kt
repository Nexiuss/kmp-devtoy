package dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class SMSRecRequestDTO : Serializable {
    /**
     * 对接系统
     */
    @JsonProperty("TransSource")
    var transSource: String? = null

    /**
     * 编码
     */
    @JsonProperty("TransCode")
    var transCode: String? = null

    /**
     * 请求日期
     */
    @JsonProperty("TransDate")
    var transDate: String? = null

    /**
     * 请求时间
     */
    @JsonProperty("TransTime")
    var transTime: String? = null

    /**
     * 时间戳
     */
    @JsonProperty("TransSeq")
    var transSeq: String? = null

    /**
     * 版本号
     */
    @JsonProperty("Version")
    var version: String? = null

    /**
     * 加密内容
     */
    @JsonProperty("BizContent")
    var bizContent: String? = null

    /**
     * 签名
     */
    @JsonProperty("Sign")
    var sign: String? = null
    override fun toString(): String {
        return "RequestDTO{" +
                "TransSource='" + transSource + '\'' +
                ", TransCode='" + transCode + '\'' +
                ", TransDate='" + transDate + '\'' +
                ", TransTime='" + transTime + '\'' +
                ", TransSeq='" + transSeq + '\'' +
                ", Version='" + version + '\'' +
                ", BizContent='" + bizContent + '\'' +
                ", Sign='" + sign + '\'' +
                '}'
    }

    companion object {
        private const val serialVersionUID = 2288716642171707137L
    }
}
