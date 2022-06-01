package com.exa.base.bean

data class BaseResponse<T>(
    val errorCode: Int,
    val errorMsg: String,
    var data: T?
)