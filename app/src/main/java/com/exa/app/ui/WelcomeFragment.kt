package com.exa.app.ui

import com.exa.app.databinding.WelcomeFragmentBinding
import com.exa.app.vm.WelcomeVm
import com.exa.base.base.BaseFragment

/**
 * @author wwq
 * @description:
 * @date :2022/3/17
 */

class WelcomeFragment:BaseFragment<WelcomeVm,WelcomeFragmentBinding>(true) {
    override fun initTitle(): String {
        return "Welcome"
    }
}