package com.exa.base.ext

import android.text.Editable
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.widget.TextView
import com.exa.impl.SpannableStringBuilderDsl
import com.exa.impl.SpannableStringBuilderImplDsl
import com.exa.impl.TextWatcherDslImpl

/**
 * @author wwq
 * @description:
 * @date :2022/3/24  https://blog.csdn.net/u011133887/article/details/123004000
 */

fun TextView.addTextChangedListenerDsl(init:TextWatcherDslImpl.()->Unit){
    val listener=TextWatcherDslImpl()
    listener.init()
    this.addTextChangedListener(listener)
}

inline fun TextView.addTextChangeListenerClosure(
    crossinline afterTextChanged: (Editable?)->Unit={},
    crossinline beforeTextChanged: (CharSequence?,Int,Int,Int)->Unit={charsequence,start,count,after->},
    crossinline onTextChanged: (CharSequence?,Int,Int,Int)->Unit={charsequence,start,count,after->}
){
    val listener=object :TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
           beforeTextChanged.invoke(s,start,count,after)
        }

        override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            onTextChanged.invoke(s,start,count,after)
        }

        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s)
        }

    }
    this.addTextChangedListener(listener)
}

fun TextView.buildSpannableString(init: SpannableStringBuilderDsl.()->Unit){
    //具体实现类
    val spanStringBuilderImpl=SpannableStringBuilderImplDsl()
    spanStringBuilderImpl.init()
    movementMethod=LinkMovementMethod.getInstance()
    //通过实现类返回SpannableStringBuilder
    text=spanStringBuilderImpl.build()
}