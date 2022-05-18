package com.exa.app.vm


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.exa.app.repo.MainRepository
import com.exa.base.base.BaseVm


/**
 * @author wwq
 * @description:
 * @date :2022/3/14
 */

class MainFragmentVm:BaseVm() {
  private var currentFragment: Fragment?=null
  private val mRepository=MainRepository()
    /**
     * 切换 Fragment
     */
  fun switchFragment(fragmentManager: FragmentManager,id:Int){
     val position=mRepository.getIdToPosition(id)
     val target=mRepository.fragments[position]
     mRepository.switch(fragmentManager,currentFragment,target)
     currentFragment=target
  }

    fun  getButtonId(position:Int)=mRepository.getPositionToId(position)
}