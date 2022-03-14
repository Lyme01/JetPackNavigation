package com.exa.base.http.exc

/**
 * @author wwq
 * @description:错误枚举类
 * @date :2022/2/8
 */

const val APP_ERROR_CODE = "-1001239"


enum class Error(private val code: String, private val err: String) {

    /**
     * 解析错误
     */
    PARSE_ERROR(APP_ERROR_CODE, "Parsing error, please try again later"),//解析错误，请稍后再试

    /**
     * 网络错误
     */
    NETWORK_ERROR(APP_ERROR_CODE, "Network anomaly"),//网络连接超时，请稍后重试

    /**
     * 证书出错
     */
    SSL_ERROR(APP_ERROR_CODE, "Certificate error, please try again later"),//证书出错，请稍后再试

    /**
     * 连接超时
     */
    TIMEOUT_ERROR(APP_ERROR_CODE, "The network connection timed out."),//网络连接超时，请稍后重试

    UNKNOW_ERROR("-1", "unknown error"),//未知

    TOKEN_INVALID("1000", "Login information invalid"),//token无效

    CANCEL_EXCEPTION("1000086","");//协程取消错误 不做处理

    fun getValue(): String {
        return err
    }

    fun getKey(): String {
        return code
    }

}