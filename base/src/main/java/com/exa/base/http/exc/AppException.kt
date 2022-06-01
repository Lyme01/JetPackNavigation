package com.exa.base.http.exc

/**
 * @author wwq
 * @description:
 * @date :2022/2/8
 */

class AppException: Exception {
    var errorMsg: String? //错误消息
    var errCode: Int //错误码
    var throwable: Throwable? = null

    constructor(
        errCode: Int,
        errorMsg: String?,
        throwable: Throwable? = null
    ) : super(errorMsg) {
        this.errorMsg = errorMsg ?: "The request failed. Please try again later"//请求失败，请稍后再试
        this.errCode = errCode
        this.throwable = throwable
    }

    constructor(error: Error, e: Throwable?) {
        errCode = error.getKey()
        errorMsg = error.getValue()
        throwable = e
    }

    override fun toString(): String {
        return "AppException(errorMsg='$errorMsg', errCode=$errCode,throwable=$throwable)"
    }


}