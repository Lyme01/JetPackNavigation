package com.exa.impl

import android.text.Editable
import android.text.TextWatcher

/**
 * @author wwq
 * @description:
 * @date :2022/3/24
 */

class TextWatcherDslImpl :TextWatcher {
    private var  afterTextChanged:((Editable?)->Unit)?=null

    private var onTextChanged:((CharSequence?,Int,Int,Int)->Unit)?=null
    private var beforeTextChanged:((CharSequence?,Int,Int,Int)->Unit)?=null

    fun afterTextChanged(method:(Editable?)->Unit){
        afterTextChanged=method
    }

    fun onTextChanged(method: (CharSequence?, Int, Int, Int) -> Unit){
        onTextChanged=method
    }

    fun beforeTextChanged(method: (CharSequence?, Int, Int, Int) -> Unit){
        beforeTextChanged=method
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        beforeTextChanged?.invoke(p0,p1,p2,p3)
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        onTextChanged?.invoke(p0,p1,p2,p3)
    }

    override fun afterTextChanged(p0: Editable?) {
        afterTextChanged?.invoke(p0)
    }
}