package com.exa.home.ui

import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.exa.base.base.BaseFragment
import com.exa.base.base.appContext
import com.exa.base.base.eventVm
import com.exa.base.ext.flowWithLifecycle
import com.exa.base.ext.launchAndRepeatWithViewLifecycle
import com.exa.base.util.ToastUtil
import com.exa.home.databinding.HomeFragmentBinding
import com.exa.home.vm.HomeVm
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
        binding.tv.setOnClickListener {
            vm.getToast(true)
        }
    }



    override fun initListener() {
      vm.articleBeanFlow.flowWithLifecycle(this){
          binding.tv.text=it.total.toString()
      }
      vm.toastFlow.flowWithLifecycle(this){
          Toast.makeText(appContext,it.toString(),Toast.LENGTH_LONG).show()
      }


    }
}