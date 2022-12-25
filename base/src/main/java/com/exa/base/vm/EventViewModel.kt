package com.exa.base.vm


import android.widget.Toast
import com.exa.base.action.CommonAction
import com.exa.base.base.BaseViewModel
import com.exa.base.base.appContext
import com.exa.base.state.CommonState


/**
 *  @author CHyun
 *  @description:app
 *  @date :2021/10/13 11:52
 **/

class EventViewModel : BaseViewModel<CommonState,CommonAction>() {
    override fun initStates(): CommonState= CommonState.Common

    init {

        handleAction {
            when(it){
                is CommonAction.Toast->{
                    Toast.makeText(appContext,it.msg,Toast.LENGTH_LONG).show()
                }
            }
    }

   }

}