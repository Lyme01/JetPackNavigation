package com.exa.home.ui


import androidx.fragment.app.viewModels
import com.exa.base.action.CommonAction
import com.exa.base.base.BaseFragment
import com.exa.base.base.eventVm
import com.exa.base.ext.flowWithLifecycle
import com.exa.home.R
import com.exa.home.action.HomeAction
import com.exa.home.databinding.HomeFragmentBinding
import com.exa.home.state.HomeState
import com.exa.home.vm.HomeViewModel

/**
 * @author wwq
 * @description:
 * @date :2022/3/17
 */

class HomeFragment: BaseFragment<HomeFragmentBinding>(navigationBar = true,false,true) {
    private val vm by viewModels<HomeViewModel>()

    override var title: String="首页"

    override fun loadPageData() {
        vm.dispatch(HomeAction.GetArticle(0))
         binding.tv.setOnClickListener {
         eventVm.dispatch(CommonAction.GoPage(this, R.id.action_to_global_main))
        }
      }

    override fun registerUIStateCallback() {
        vm.uiState.flowWithLifecycle(this){
            when(it){
                is HomeState.Success->{
                    binding.tv.text=it.articleBean.curPage.toString()
                }
                is HomeState.Init -> {

                }
            }
        }
    }


}


