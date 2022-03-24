package com.exa.home.ui

import com.exa.base.base.BaseFragment
import com.exa.home.databinding.HomeFragmentBinding
import com.exa.home.vm.HomeVm

/**
 * @author wwq
 * @description:
 * @date :2022/3/17
 */

class HomeFragment:BaseFragment<HomeVm,HomeFragmentBinding>(navigationBar = true) {
    override fun initTitle(): String {
        return "首页"
    }

    override fun initData() {
        vm.getArticle()
    }

    override fun initListener() {
        vm.articleBean.observe(this){
            binding.tv.text=it.datas.apkLink
        }
    }
}