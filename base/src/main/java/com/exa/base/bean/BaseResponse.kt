package com.exa.base.bean

data class BaseResponse<T>(var code: String, val message: String?, val data: T?, val success: Boolean)