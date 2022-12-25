package com.exa.home.action

import com.exa.impl.Action

sealed class HomeAction:Action {
    data class Toast(val msg:String):HomeAction()
}