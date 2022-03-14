package com.exa.base.util

import android.content.Context
import com.exa.base.base.appContext

/**
 * 描述：测量工具类
 * 日期：2021/11/03
 * @author fan
 */

object MeasureUtil {
    private const val WIDTH = 0
    private const val HEIGHT = 1

    fun getScreenWidth(): Int = measureScreen(WIDTH)

    fun getScreenHeight(): Int = measureScreen(HEIGHT)

    private fun measureScreen(type: Int): Int {
        val displayMetrics = appContext.resources.displayMetrics
        //获取屏幕宽高，单位是像素
        return when (type) {
            WIDTH -> displayMetrics?.widthPixels ?: 0
            HEIGHT -> displayMetrics?.heightPixels ?: 0
            else -> 0
        }
    }

    /**
     * dp2px
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * sp2px
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }
}