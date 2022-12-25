package com.exa.base.action

import com.exa.impl.Action

sealed class CommonAction:Action {
    data class Toast(val msg:String):CommonAction()
}