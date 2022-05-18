package com.exa.base.bean

data class BaseResponse<T>(var errorCode: Int, val errorMsg: String?, val data: T)