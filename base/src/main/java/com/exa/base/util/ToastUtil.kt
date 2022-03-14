package com.exa.base.util

import android.content.Context
import android.graphics.PixelFormat
import android.os.Handler
import android.os.Looper
import android.os.Message

import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.TextView
import com.exa.base.R
import com.exa.base.base.appContext
import java.lang.Exception


/**
 * @author wwq
 * @description:
 * @date :2022/3/14
 */

class ToastUtil private constructor(): Handler(Looper.getMainLooper()){
    private val customView by lazy {
        LayoutInflater.from(appContext).inflate(
            R.layout.dialog_toast,null
        )
    }

    private var  isShow=false
    companion object{
        val instance=ToastUtil()
    }
    fun show(context:Context,tips:String?){
        if (tips.isNullOrEmpty()){
            return
        }
        try {
            val params=WindowManager.LayoutParams()
            params.height = WindowManager.LayoutParams.WRAP_CONTENT
            params.width = WindowManager.LayoutParams.WRAP_CONTENT
            params.format = PixelFormat.TRANSLUCENT
            params.windowAnimations = android.R.style.Animation_Toast
            params.flags = (WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            params.packageName = appContext.packageName
            // 重新初始化位置
            params.gravity = Gravity.CENTER
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            customView.findViewById<TextView>(R.id.tv_tips_id).apply {
                text = tips
            }
            if (!isShow) {
                windowManager.addView(customView, params)
            } else {
                removeMessages(hashCode())
                windowManager.updateViewLayout(customView, params)
            }
            isShow = true
            // 自动消失
            sendEmptyMessageDelayed(hashCode(), 2000)
        }catch (e:Exception){

        }
    }


    fun cancel() {
        removeMessages(hashCode())
        try {
            isShow = false
            (customView.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).removeViewImmediate(
                customView
            )
        } catch (e: Exception) {
        }
    }

    override fun handleMessage(msg: Message) {
        cancel()
    }
}