package com.exa.mine.ui

import com.exa.base.base.BaseFragment
import com.exa.mine.databinding.MineFragmentBinding
import com.exa.mine.vm.MineVm

/**
 * @author wwq
 * @description:
 * @date :2022/3/17
 */

class MineFragment:BaseFragment<MineVm,MineFragmentBinding>(true) {
    override fun initTitle(): String {
        return "Mine"
    }
}