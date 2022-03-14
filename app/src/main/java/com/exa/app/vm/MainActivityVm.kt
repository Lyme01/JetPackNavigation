package com.exa.app.vm

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.exa.base.base.BaseRepository
import com.exa.base.base.BaseVm
import com.exa.base.http.exc.AppException

/**
 * @author wwq
 * @description:
 * @date :2022/3/14
 */

class MainActivityVm:BaseVm<BaseRepository>() {
    val toastMsg=MutableLiveData<String>()

    fun exceptionHandle(v: View?, it:AppException){
        v ?: return
        when(it.errCode){

        }
    }
}