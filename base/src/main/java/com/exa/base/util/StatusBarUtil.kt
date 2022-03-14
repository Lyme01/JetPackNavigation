package com.exa.base.util

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager


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
            var flag:Int= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            if (isDark){
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    flag=flag or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
            }
            window.decorView.systemUiVisibility=flag
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor= Color.TRANSPARENT
            val statusBarHeight=if (isImmersive){
                0
            }else{
                getStatusBarHeight(activity)
            }
            setStatusBarView(activity, statusBarHeight)
        }
    }

    private fun setStatusBarView(activity: Activity, statusBarHeight: Int) {
        // 设置根布局的参数
        val roootView=(activity.findViewById<View>(android.R.id.content) as ViewGroup)
            .getChildAt(0) as ViewGroup
        roootView.setPadding(0,statusBarHeight,0,0)
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    fun getStatusBarHeight(context: Context): Int {
        val resources = context.resources
        val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }
}