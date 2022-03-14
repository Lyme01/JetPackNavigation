package com.exa.base.http.state

import com.exa.base.util.SingleLiveEvent

/**
 * @author wwq
 * @description:
 * @date :2022/2/10
 */

class NetStateManager {
    val netStateCallback:SingleLiveEvent<NetState> by lazy {
        SingleLiveEvent()
    }

    companion object{
        val instance:NetStateManager by lazy(mode =LazyThreadSafetyMode.NONE){
            NetStateManager()
        }
    }
}