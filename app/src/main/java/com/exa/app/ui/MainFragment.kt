package com.exa.app.ui

import androidx.fragment.app.viewModels
import com.exa.app.R
import com.exa.app.action.MainAction
import com.exa.app.databinding.FragmentMainBinding
import com.exa.app.vm.MainFragmentViewModel
import com.exa.base.base.BaseFragment

/**
 * @author wwq
 * @description:
 * @date :2022/3/14
 */

class MainFragment: BaseFragment<FragmentMainBinding>() {
    private val  vm by viewModels<MainFragmentViewModel>()

    override fun loadPageData() {
        vm.dispatch(MainAction.Switch(childFragmentManager,0))
        selectBar()
    }

    private fun selectBar(){
        binding.mainRg.setOnItemSelectedListener{ item->
            when(item.itemId){
                R.id.home_item->{
                    vm.dispatch(MainAction.Switch(childFragmentManager,0))
                }
                R.id.type_item->{
                    vm.dispatch(MainAction.Switch(childFragmentManager,1))
                }

                R.id.setting_item->{
                    vm.dispatch(MainAction.Switch(childFragmentManager,2))
                }

            }
            return@setOnItemSelectedListener true
        }
    }



    override fun registerUIStateCallback() {

    }

}