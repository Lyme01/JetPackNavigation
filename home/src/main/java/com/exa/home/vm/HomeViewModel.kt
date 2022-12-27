package com.exa.home.vm

import android.widget.Toast
import com.exa.base.base.*
import com.exa.base.ext.nav
import com.exa.base.ext.navigateAction
import com.exa.home.R
import com.exa.home.action.HomeAction
import com.exa.home.http.api
import com.exa.home.state.HomeState

/**
 * @author wwq
 * @description:
 * @date :2022/3/17
 */

class HomeViewModel : BaseViewModel<HomeState,HomeAction>() {

  init {
      handleAction {
          when(it){
              is HomeAction.GetArticle->{
                  getArticle()
              }
              is HomeAction.GoPage -> {
                  nav(it.fragment).navigateAction(R.id.action_to_global_main)
              }
          }
      }
  }


    private fun getArticle() {
        doRequest({
            api.getArticle()
        }, success = {
            _state.value=HomeState.Success(it)
        }, {
            println("yyyy")
        })
    }







    override fun initStates():HomeState{
        return HomeState.Init
    }
}


