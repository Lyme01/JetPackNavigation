package com.exa.base.util

import android.util.Log
import com.exa.base.BuildConfig

/**
 * 描述：LogUtil
 * 统一管理 log
 * 日期：2021/11/03
 * @author fan
 */
object LogUtil {

    fun e(tag: String, msg: String) {
        if (!BuildConfig.DEBUG) {
            return
        }
        Log.e(tag, msg)
    }

    fun d(tag: String, msg: String) {
        if (!BuildConfig.DEBUG) {
            return
        }
        Log.d(tag, msg)
    }
}