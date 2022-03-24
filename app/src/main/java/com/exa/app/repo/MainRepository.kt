package com.exa.app.repo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.exa.app.R
import com.exa.base.base.BaseFragment
import com.exa.base.base.BaseRepository
import com.exa.home.ui.HomeFragment
import com.exa.mine.ui.MineFragment
import com.exa.square.ui.SquareFragment

/**
 * @author wwq
 * @description:
 * @date :2022/3/15
 */

class MainRepository:BaseRepository() {
    val fragments = listOf<BaseFragment<*, *>>(
      HomeFragment(),
      MineFragment(),
      SquareFragment()
    )


    fun switch(
        manager:FragmentManager,
        current:Fragment?,
        target:BaseFragment<*,*>
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
    fun getIdToPosition(id: Int): Int = when (id) {
        R.id.main_rb1 -> 0
        R.id.main_rb2 -> 1
        R.id.main_rb3 -> 2

        else -> 0
    }

    fun getPositionToId(position: Int): Int = when (position) {
        0 -> R.id.main_rb1
        1 -> R.id.main_rb2
        2 -> R.id.main_rb3
        else -> R.id.main_rb1
    }
}