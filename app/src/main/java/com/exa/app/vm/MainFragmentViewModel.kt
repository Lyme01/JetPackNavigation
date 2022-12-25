package com.exa.app.vm


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.exa.app.R
import com.exa.app.action.MainAction
import com.exa.app.state.MainState
import com.exa.base.base.BaseFragment
import com.exa.base.base.BaseViewModel
import com.exa.home.ui.HomeFragment
import com.exa.mine.ui.MineFragment
import com.exa.square.ui.SquareFragment


/**
 * @author wwq
 * @description:
 * @date :2022/3/14
 */

class MainFragmentViewModel:BaseViewModel<MainState,MainAction>() {

    private var currentFragment: Fragment?=null

    private val fragments = listOf<BaseFragment<*>>(
        HomeFragment(),
        MineFragment(),
        SquareFragment()
    )

    init {
        handleAction {
            when(it){
                is MainAction.Switch->switchFragment(it.fragmentManager,it.position)
            }
        }
    }

    private fun switch(
        manager:FragmentManager,
        current:Fragment?,
        target: BaseFragment<*>
    ){
        manager.beginTransaction().let {
            if (current !=null){
                it.hide(current)
            }
            if (!target.isAdded){
                it.add(R.id.main_layout,target)
            }else{
                it.show(target)
            }
            it.commitNow()
        }
    }


    /**
     * 切换 Fragment
     */
    private fun switchFragment(fragmentManager: FragmentManager,position:Int){
     val target=fragments[position]
     switch(fragmentManager,currentFragment,target)
     currentFragment=target
  }

    override fun initStates(): MainState =MainState.Title()

}