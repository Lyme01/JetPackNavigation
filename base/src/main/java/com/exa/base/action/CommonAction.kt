package com.exa.base.action

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.exa.impl.Action

sealed class CommonAction : Action {
    data class Toast(val msg: String) : CommonAction() {

    }

    data class GoPage(
        val fragment: Fragment,
        val resId: Int,
        val bundle: Bundle? = null,
        val checkLogin: Boolean = false,
        val cleanStack: Boolean = false,
        val cleanStackId: Int = -1,
        val interval: Long = 500
    ) : CommonAction()

    data class PopBack(val fragment: Fragment,val fragmentId:Int):CommonAction()
}