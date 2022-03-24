package com.exa.impl

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.*
import android.view.View

/**
 * @author wwq
 * @description:
 * @date :2022/3/24
 */

class SpannableStringBuilderImplDsl:SpannableStringBuilderDsl {
    private val builder=SpannableStringBuilder()
    //记录上次添加文字后最后的索引值
    var lastIndex:Int=0
    var isClickable=false
    override fun addText(text: String, method: (SpanBuilderDsl.() -> Unit)?) {
        val start=lastIndex
        builder.append(text)
        lastIndex+=text.length
        val spanBuilder=SpanBuilderImplDsl()
        method?.let {spanBuilder.it()}
        spanBuilder.apply {
            onClickSpan?.let {
                builder.setSpan(it,start,lastIndex,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                isClickable=true
            }
            if (!useUnderLine){
                val noUnderLineSpan=NoUnderlineSpan()
                builder.setSpan(noUnderLineSpan,start,lastIndex,Spannable.SPAN_MARK_MARK)
            }
            foregroundColorSpan?.let {
                builder.setSpan(it,start,lastIndex,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            fontSizeSpan?.let {
                builder.setSpan(it,start,lastIndex,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            backgroundColorSpan?.let {
                builder.setSpan(it,start,lastIndex,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
    }
    fun build():SpannableStringBuilder{
        return builder
    }
}

class SpanBuilderImplDsl:SpanBuilderDsl{
    var foregroundColorSpan:ForegroundColorSpan?=null
    var onClickSpan:ClickableSpan?=null
    var useUnderLine=true
    var fontSizeSpan:AbsoluteSizeSpan?=null
    var backgroundColorSpan:BackgroundColorSpan?=null
    override fun setColor(color: Int) {
        foregroundColorSpan= ForegroundColorSpan(color)
    }

    override fun setBackgroundColor(color: Int) {
        backgroundColorSpan= BackgroundColorSpan(color)
    }

    override fun onClick(useUnderLine: Boolean, onClick: (View) -> Unit) {
        onClickSpan=object:ClickableSpan(){
            override fun onClick(view: View) {
                onClick(view)
            }
        }
        this.useUnderLine=useUnderLine
    }

    override fun setFontSize(size: Int, dp: Boolean) {
       fontSizeSpan= AbsoluteSizeSpan(size,dp)
    }

}

class NoUnderlineSpan:UnderlineSpan(){
    override fun updateDrawState(ds: TextPaint) {
        ds.color=ds.linkColor
        ds.isUnderlineText=false
    }
}