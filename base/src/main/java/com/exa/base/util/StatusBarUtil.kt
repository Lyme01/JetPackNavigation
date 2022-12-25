package com.exa.base.util

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentActivity


/**
 * @author wwq
 * @description:
 * @date :2022/3/14
 */

object StatusBarUtil {
    fun setStatusBar(
        activity: Activity?,
        isImmersive:Boolean,
        isDark:Boolean
    ){
        activity?.run {
            WindowCompat.setDecorFitsSystemWindows(window,true)
            WindowCompat.getInsetsController(window, findViewById(android.R.id.content)).apply {
                if (isImmersive){
                    hide(WindowInsetsCompat.Type.statusBars())
                }
                isAppearanceLightStatusBars=isDark
            }
        }
    }

    private fun setStatusBarView(activity: Activity, statusBarHeight: Int) {
        // 设置根布局的参数
        val rootView=(activity.findViewById<View>(android.R.id.content) as ViewGroup)
            .getChildAt(0) as ViewGroup
        rootView.setPadding(0,statusBarHeight,0,0)
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    private val FragmentActivity.windowInsetsCompat: WindowInsetsCompat?
        get() = ViewCompat.getRootWindowInsets(findViewById(android.R.id.content))

    fun getStatusBarsHeight(activity:FragmentActivity): Int {
        activity.run {
            val windowInsetsCompat = windowInsetsCompat ?: return 0
            return windowInsetsCompat.getInsets(WindowInsetsCompat.Type.statusBars()).top
        }
    }

}