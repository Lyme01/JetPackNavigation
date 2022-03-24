package com.exa.app.ui

import android.widget.RadioButton
import com.exa.app.databinding.FragmentMainBinding
import com.exa.app.vm.MainFragmentVm
import com.exa.base.base.BaseFragment
import com.exa.base.base.eventVm

/**
 * @author wwq
 * @description:
 * @date :2022/3/14
 */

class MainFragment:BaseFragment<MainFragmentVm,FragmentMainBinding>(true) {
    override fun initData() {
        binding.mainRb1.isChecked=true
    }

    override fun initListener() {
        binding.mainRg.setOnCheckedChangeListener { _, i ->
       vm.switchFragment(childFragmentManager,i)
        }
        eventVm.mainTab.observe(this){
            binding.mainRg.findViewById<RadioButton>(vm.getButtonId(it)).isChecked=true
        }
    }

    override fun onBackPressed() {
        exitApp()
    }
}