package com.exa.base.base


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.RemoteMediator
import com.exa.base.bean.BaseResponse
import com.exa.base.http.exc.AppException
import com.exa.base.http.exc.ExceptionHandle

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.reflect.Method

/**
 * @author wwq
 * @description:
 * @date :2022/1/17
 */

abstract class BaseVm:ViewModel() {


    lateinit var owner: LifecycleOwner

    fun getInflateMethod(clz: Class<*>, paramCount: Int = 1): Method {
        return  getInflateMethodHa(clz, paramCount)
    }

    private fun getInflateMethodHa(clz: Class<*>, paramCount: Int): Method {
        return if (paramCount == 3) {
            clz.getMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java
            )
        } else {
            clz.getMethod("inflate", LayoutInflater::class.java)
        }
    }

    fun isInitOwner(): Boolean {
        return ::owner.isInitialized
    }

    var isDialogShow = MutableLiveData<Boolean>()
    var isFinish = MutableLiveData<Boolean>()

    var toast = MutableLiveData<String>()


    fun <T> doRequest(
      block:suspend  () -> BaseResponse<T>,
      success:(T) ->Unit,
      onError:(AppException) -> Unit={},
    ) =  viewModelScope.launch {
        flow {
            val response = block.invoke()
            if (response.errorCode != 0) {
                throw  AppException(response.errorCode, response.errorMsg)
            }
            emit(response)
        }.flowOn(Dispatchers.IO).onStart {

        }.catch { exc,->
            onError.invoke(ExceptionHandle.handleException(exc))
        }.onCompletion {

        }.collect{
            it.data?.let { it1 -> success.invoke(it1) }
        }
    }

//    /**
//     * 网络请求UI管理，根据请求是否显示/隐藏 Dialog，错误页面等
//     */
//     val requestUi:UiChange by lazy { UiChange() }
//
//    private var mDialog: Dialog? = null
//
//    /**
//     * 全局的协程异常
//     * 捕获手动没有捕获到的异常
//     * 如在协程中使用 async
//     */
//    private val globalException =
//        CoroutineExceptionHandler { coroutineContext, throwable ->
//            LogUtil.e("TAG", "上下文：${coroutineContext},异常：${throwable.message}")
//        }



//    /**
//     * 显示等待进度条的协程
//     * @param isShowDialog 显示等待框
//     * @param error 错误的后续
//     * @param block 异步操作主体
//     */
//    fun launch(
//        isShowDialog: Boolean = false,
//        error: ((msg: String) -> Unit)? = null,
//        block: suspend CoroutineScope.() -> Unit
//    ): Job {
//        return viewModelScope.launch(globalException) {
//            runCatching {
//                if (isShowDialog) requestUi.showDialog.call()
//                block()
//            }.onFailure {
//                error?.run { it.message ?: "" }
//                //捕获 code != 0000 外的其他错误
//                val ex = ExceptionHandle.handleException(it)
//                eventVm.fail.value = ex
//            }
//            if (isShowDialog) requestUi.dismissDialog.call()
//        }
//    }


    /**
     *  调用协程
     * @param block 操作耗时操作任务
     * @param success 成功回调
     * @param error 失败回调 可不给
     */
//    fun <T> launch(
//        block: () -> T,
//        success: (T) -> Unit,
//        error: (Throwable) -> Unit = {}
//    ) {
//        viewModelScope.launch(globalException) {
//            kotlin.runCatching {
//                withContext(Dispatchers.IO) {
//                    block()
//                }
//            }.onSuccess {
//                success(it)
//            }.onFailure {
//                error(it)
//            }
//        }
//    }
//
//    /**
//     * 加载框显示
//     */
//    fun showLoadingDialog(context: Context?) {
//        context ?: return
//        val view = View.inflate(context, R.layout.dialog_loading, null)
//        if (mDialog == null) {
//            mDialog = DialogUtil.createDialog(context, view)
//        }
//        mDialog?.let { mRepository.changeDialogSize(it) }
//    }
//
//    fun hideDialog() {
//        mDialog?.run { if (isShowing) dismiss() }
//    }
//
//    fun isInitOwner(): Boolean {
//        return ::owner.isInitialized
//    }
//
//    fun getInflateMethod(clz: Class<*>, paramCount: Int = 1): Method {
//        return mRepository.getInflateMethod(clz, paramCount)
//    }
//
//    fun initKeyBackListener(back: () -> Boolean): View.OnKeyListener {
//        return View.OnKeyListener { _, code, event ->
//            if (code == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
//                back()
//            } else {
//                false
//            }
//        }
//    }
}