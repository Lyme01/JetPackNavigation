package com.exa.app.ui

import com.exa.app.databinding.ActivityMainBinding
import com.exa.app.vm.MainActivityVm
import com.exa.base.base.BaseActivity
import com.exa.base.base.eventVm


/**
 * @author wwq
 * @description:
 * @date :2022/3/14
 */

class MainActivity:BaseActivity<MainActivityVm, ActivityMainBinding>() {
    override fun initViews() {

    }

    override fun initListener() {
        super.initListener()
        //协程的错误，可能是网络请求，可能是其他
        eventVm.fail.observe(this){

        }
    }
}