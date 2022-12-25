package com.exa.home.ui


import androidx.fragment.app.viewModels
import com.exa.base.base.BaseFragment
import com.exa.base.ext.flowWithLifecycle
import com.exa.home.databinding.HomeFragmentBinding
import com.exa.home.state.HomeState
import com.exa.home.vm.HomeViewModel

/**
 * @author wwq
 * @description:
 * @date :2022/3/17
 */

class HomeFragment: BaseFragment<HomeFragmentBinding>(navigationBar = true) {
    private val vm by viewModels<HomeViewModel>()


    override fun loadPageData() {
        vm.getArticle()
        binding.tv.setOnClickListener {

        }
      }

    override fun registerUIStateCallback() {
        vm.uiState.flowWithLifecycle(this){
            when(it){
                is HomeState.Success->{
                    binding.tv.text=it.articleBean.curPage.toString()

                }
                is HomeState.Title->{
                    println(it.titleBean.title)
                    setTitle(it.titleBean)
                }
            }
        }
    }


}


