package com.exa.base.base

import android.app.Dialog
import android.content.Context
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exa.base.R
import com.exa.base.bean.BaseResponse
import com.exa.base.http.exc.ExceptionHandle
import com.exa.base.util.DialogUtil
import com.exa.base.util.LogUtil
import kotlinx.coroutines.*
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType

/**
 * @author wwq
 * @description:
 * @date :2022/1/17
 */

abstract class BaseVm<BR:BaseRepository>:ViewModel() {
    protected var mRepository:BR=((javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments[0] as Class<BR>).newInstance()

     lateinit var owner: LifecycleOwner

    /**
     * 网络请求UI管理，根据请求是否显示/隐藏 Dialog，错误页面等
     */
     val requestUi:UiChange by lazy { UiChange() }

    private var mDialog: Dialog? = null

    /**
     * 全局的协程异常
     * 捕获手动没有捕获到的异常
     * 如在协程中使用 async
     */
    private val globalException =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            LogUtil.e("TAG", "上下文：${coroutineContext},异常：${throwable.message}")
        }

    /**
     * 普通请求
     * @param fail 请求失败，非异常，code！= success 的情况
     * @param request 请求函数
     * @param isShowToast 显示请求失败成功的 toast
     */
    suspend fun <T> doRequest(
        suc: ((String) -> Unit?)? = null,
        fail: (String, String?) -> Unit = { _, _ -> },
        isShowToast: Boolean = true,
        request: suspend CoroutineScope.() -> BaseResponse<T>
    ): T? {
        return mRepository.doRequest(
            request,
            suc,
            fail,
            requestUi,
            isShowToast
        )
    }

    /**
     * 显示等待进度条的协程
     * @param isShowDialog 显示等待框
     * @param error 错误的后续
     * @param block 异步操作主体
     */
    fun launch(
        isShowDialog: Boolean = false,
        error: ((msg: String) -> Unit)? = null,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(globalException) {
            runCatching {
                if (isShowDialog) requestUi.showDialog.call()
                block()
            }.onFailure {
                error?.run { it.message ?: "" }
                //捕获 code != 0000 外的其他错误
                val ex = ExceptionHandle.handleException(it)
                eventVm.fail.value = ex
            }
            if (isShowDialog) requestUi.dismissDialog.call()
        }
    }


    /**
     *  调用协程
     * @param block 操作耗时操作任务
     * @param success 成功回调
     * @param error 失败回调 可不给
     */
    fun <T> launch(
        block: () -> T,
        success: (T) -> Unit,
        error: (Throwable) -> Unit = {}
    ) {
        viewModelScope.launch(globalException) {
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

    /**
     * 加载框显示
     */
    fun showLoadingDialog(context: Context?) {
        context ?: return
        val view = View.inflate(context, R.layout.dialog_loading, null)
        if (mDialog == null) {
            mDialog = DialogUtil.createDialog(context, view)
        }
        mDialog?.let { mRepository.changeDialogSize(it) }
    }

    fun hideDialog() {
        mDialog?.run { if (isShowing) dismiss() }
    }

    fun isInitOwner(): Boolean {
        return ::owner.isInitialized
    }

    fun getInflateMethod(clz: Class<*>, paramCount: Int = 1): Method {
        return mRepository.getInflateMethod(clz, paramCount)
    }

    fun initKeyBackListener(back: () -> Boolean): View.OnKeyListener {
        return View.OnKeyListener { _, code, event ->
            if (code == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
                back()
            } else {
                false
            }
        }
    }
}