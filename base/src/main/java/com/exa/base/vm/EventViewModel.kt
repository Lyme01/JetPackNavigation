package com.exa.base.vm


import com.exa.base.base.BaseRepository
import com.exa.base.base.BaseVm
import com.exa.base.http.exc.AppException
import com.exa.base.util.SingleLiveEvent


/**
 *  @author CHyun
 *  @description:app
 *  @date :2021/10/13 11:52
 **/
class EventViewModel : BaseVm<BaseRepository>() {
    val mainTab = SingleLiveEvent<Int>()

    val fail = SingleLiveEvent<AppException>()

}