package com.exa.base.base

import android.app.Dialog
import android.graphics.Point
import android.view.LayoutInflater
import android.view.ViewGroup
import com.exa.base.bean.BaseResponse
import com.exa.base.http.exc.AppException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.reflect.Method

/**
 * @author wwq
 * @description:
 * @date :2022/1/17
 */

open class BaseRepository {
//    companion object {
//        private const val SERVER_CODE = "0000"
//    }
//
//    /**
//     * 普通请求
//     * @param request 请求函数
//     * @param suc 请求成功需要 message参数时用
//     * @param fail 请求失败（非错误）
//     * @param requestUi 请求过程，UI变化（加载框的显示隐藏，错误页面提示等）控制类
//     * @param isShowErrorToast 显示请求错误的toast
//     * @return 请求结果
//     */
//    suspend fun <T> doRequest(
//        request: suspend CoroutineScope.() -> BaseResponse<T>,
//        suc: ((String) -> Unit?)? = null,
//        fail: (String, String?) -> Unit,
//        requestUi: UiChange,
//        isShowErrorToast: Boolean
//    ): T? {
//        var response: BaseResponse<T>?
//        var result: T?
//        withContext(Dispatchers.IO) {
//            //如果请求报错，会被 BaseViewModel 的 launch 捕获，则不能进行下一步处理结果
//            response = request()
//            //断言不为空，为空则和上面一样
//            result = response!!.let {
//                if (it.code == SERVER_CODE) {
//                    suc?.run { (it.message ?: "") }
//                    it.data
//                } else {
//                    if (isShowErrorToast) requestUi.toast.postValue(it.message)
//                    fail(it.code, it.message)
//                    throw AppException(it.code, it.message)
//                }
//            }
//        }
//        return result
//    }
//
//    fun getInflateMethod(clz: Class<*>, paramCount: Int): Method {
//        return if (paramCount == 3) {
//            clz.getMethod(
//                "inflate",
//                LayoutInflater::class.java,
//                ViewGroup::class.java,
//                Boolean::class.java
//            )
//        } else {
//            clz.getMethod("inflate", LayoutInflater::class.java)
//        }
//    }
//
//    fun changeDialogSize(dialog: Dialog) {
//        dialog.show()
//        dialog.window?.let {
//            val point = Point()
//            it.windowManager.defaultDisplay.getSize(point)
//            val a = it.attributes
//            a.width = point.x / 10 * 3
//            it.attributes = a
//        }
//    }
}