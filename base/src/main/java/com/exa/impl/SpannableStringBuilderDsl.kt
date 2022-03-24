package com.exa.impl

import android.view.View

/**
 * @author wwq
 * @description:
 * @date :2022/3/24
 */

interface SpannableStringBuilderDsl {
    fun addText(text:String,method:(SpanBuilderDsl.()->Unit)?=null)
}

interface SpanBuilderDsl{
    fun setColor(color:Int)
    fun setBackgroundColor(color:Int)
    fun onClick(useUnderLine:Boolean=true,onClick:(View)->Unit)
    fun setFontSize(size:Int,dp:Boolean)
}
