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
                is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> {
                    ex = AppException(Error.PARSE_ERROR, e)
                    return ex
                }
                is ConnectException -> {
                    ex = AppException(Error.NETWORK_ERROR, e)
                    return ex
                }
                is javax.net.ssl.SSLException -> {
                    ex = AppException(Error.SSL_ERROR, e)
                    return ex
                }
                is ConnectTimeoutException -> {
                    ex = AppException(Error.TIMEOUT_ERROR, e)
                    return ex
                }
                is java.net.SocketTimeoutException -> {
                    ex = AppException(Error.TIMEOUT_ERROR, e)
                    return ex
                }
                is java.net.UnknownHostException -> {
                    ex = AppException(Error.TIMEOUT_ERROR, e)
                    return ex
                }
                is CancellationException -> {
                    ex = AppException(Error.CANCEL_EXCEPTION, e)
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