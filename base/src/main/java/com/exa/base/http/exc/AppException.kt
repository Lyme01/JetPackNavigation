package com.exa.base.http.exc

/**
 * @author wwq
 * @description:
 * @date :2022/2/8
 */

class AppException: Exception {
    var errorMsg: String? //错误消息
    var errCode: String //错误码
    var errorLog: String? //错误日志
    var throwable: Throwable? = null

    constructor(
        errCode: String,
        errorMsg: String?,
        errorLog: String? = "",
        throwable: Throwable? = null
    ) : super(errorMsg) {
        this.errorMsg = errorMsg ?: "The request failed. Please try again later"//请求失败，请稍后再试
        this.errCode = errCode
        this.errorLog = errorLog ?: this.errorMsg
        this.throwable = throwable
    }

    constructor(error: Error, e: Throwable?) {
        errCode = error.getKey()
        errorMsg = error.getValue()
        errorLog = e?.message
        throwable = e
    }

    override fun toString(): String {
        return "AppException(errorMsg='$errorMsg', errCode=$errCode, errorLog=$errorLog, throwable=$throwable)"
    }


}