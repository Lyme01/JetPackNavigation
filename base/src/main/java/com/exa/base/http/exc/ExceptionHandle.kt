package com.exa.base.http.exc

import android.net.ParseException
import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.util.concurrent.CancellationException

/**
 * @author wwq
 * @description:根据异常返回相关的错误信息工具类
 * @date :2022/2/8
 */

object ExceptionHandle {
    fun handleException(e: Throwable?): AppException {
        val ex: AppException
        e?.let {
            when (it) {
                is HttpException -> {
                    ex = AppException(Error.NETWORK_ERROR, e)
                    return ex
                }
                is JsonParseException, is JSONException, is ParseException-> {
                    ex = AppException(Error.PARSE_ERROR, e)
                    return ex
                }
                is ConnectException -> {
                    ex = AppException(Error.NETWORK_ERROR, e)
                    return ex
                }

                is AppException -> return it

                else -> AppException(Error.UNKNOW_ERROR, e)
            }
        }
        ex = AppException(Error.UNKNOW_ERROR, e)
        return ex
    }
}