package com.exa.base.base

import android.app.Application
import com.exa.base.vm.EventViewModel
import com.tencent.mmkv.MMKV

/**
 * @author wwq
 * @description:
 * @date :2022/1/17
 */


lateinit var appContext:Application
val eventVm: EventViewModel by lazy {
   EventViewModel()
}

class BaseApp :Application(){
    override fun onCreate() {
        super.onCreate()
        appContext=this
        MMKV.initialize(this)
    }
}