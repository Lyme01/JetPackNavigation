package com.exa.base.http.exc

/**
 * @author wwq
 * @description:错误枚举类
 * @date :2022/2/8
 */

const val APP_ERROR_CODE = "-1001239"


enum class Error(private val code: Int, private val err: String) {

    /**
     * 解析错误
     */
    PARSE_ERROR(1001, "Parsing error, please try again later"),//解析错误，请稍后再试

    /**
     * 网络错误
     */
    NETWORK_ERROR(1002, "Network anomaly"),//网络连接超时，请稍后重试





    UNKNOW_ERROR(1000, "unknown error");//未知



    fun getValue(): String {
        return err
    }

    fun getKey(): Int {
        return code
    }

}