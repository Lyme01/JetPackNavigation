package com.exa.base.http.util

import android.view.View
import androidx.lifecycle.viewModelScope
import com.exa.base.base.BaseVm
import com.exa.base.bean.BaseResponse
import com.exa.base.http.exc.AppException
import com.exa.base.http.exc.ExceptionHandle
import kotlinx.coroutines.*

/**
 * @author wwq
 * @description:
 * @date :2022/5/7
 */

fun <T> BaseVm.request(
    block: suspend () -> BaseResponse<T>,
    success: (T) -> Unit,
    error: (AppException) -> Unit = {},
    isShowDialog: Boolean = false,
    view: View? = null,
    returnApiResponse: (res: BaseResponse<T>) -> Unit = {},
): Job {
    //如果需要弹窗 通知Activity/fragment弹窗
    return viewModelScope.launch() {
        runCatching {
            dialogState(isShowDialog, true)
            view?.isClickable = false
            //请求体
            block()
        }.onSuccess {
            //网络请求成功 关闭弹窗
            returnApiResponse.invoke(it)
            dialogState(isShowDialog, false)
            view?.isClickable = true
            runCatching {
                //校验请求结果码是否正确，不正确会抛出异常走下面的onFailure
                executeResponse(it, { t->
                  success(t)

                }, { e ->
                    error(e)
                    showToast( e.message.toString())
                })
            }.onFailure { e ->
                //打印错误消息
                val ex = ExceptionHandle.handleException(e)
                error(ex)
                showToast( ex.message.toString())
            }
        }.onFailure { e ->
            view?.isClickable = true
            //网络请求异常 关闭弹窗
            //打印错误消息
            //失败回调
            dialogState(isShowDialog, false)
            val ex = ExceptionHandle.handleException(e)
            error(ex)
            println("6666$e")
            showToast( ex.message.toString())
        }
    }
}


private fun BaseVm.dialogState(showDialog: Boolean, state: Boolean) {
    if (showDialog) {
        isDialogShow.value = state
    }
}

private fun BaseVm.showToast( toastStr: String) {

            toastStr?.let {
                toast.value = toastStr
            }


}

/**
 * 请求结果过滤，判断请求服务器请求结果是否成功，不成功则会抛出异常
 */
suspend fun <T> executeResponse(
    response: BaseResponse<T>,
    success: suspend CoroutineScope.(T) -> Unit,
    failed: (excpetion: AppException) -> Unit
) {
    coroutineScope {
        when (response.errorCode) {
            0 -> {
                response.data?.let { success(it) }
            }
            else -> {
                failed(
                    AppException(
                        response.errorCode,
                        response.errorMsg,
                    )
                )
            }
        }
    }
}

/**
 *  调用协程
 * @param block 操作耗时操作任务
 * @param success 成功回调
 * @param error 失败回调 可不给
 */
fun <T> BaseVm.launch(
    block: () -> T,
    success: (T) -> Unit,
    error: (Throwable) -> Unit = {}
) {
    viewModelScope.launch {
        kotlin.runCatching {
            withContext(Dispatchers.IO) {
                block()
            }
        }.onSuccess {
            success(it)
        }.onFailure {
            error(it)
        }
    }
}