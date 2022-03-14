package com.exa.base.http.state

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.exa.base.util.NetworkUtil

/**
 * @author wwq
 * @description:
 * @date :2022/2/10
 */

class NetworkStateReceive:BroadcastReceiver() {
    var isInit = true
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action ==ConnectivityManager.CONNECTIVITY_ACTION){
            if (!isInit){
                if (!NetworkUtil.isNetworkAvailable(context)){
                    //收到没有网络时判断之前的值是不是有网络，如果有网络才提示通知 ，防止重复通知
                    NetStateManager.instance.netStateCallback.value?.let {
                        if (it.isSuccess){
                            //没网
                            NetStateManager.instance.netStateCallback.value=
                                NetState(isSuccess=false)
                        }
                        return
                    }
                    NetStateManager.instance.netStateCallback.value=
                        NetState(isSuccess=false)
                }else{
                    //收到有网络时判断之前的值是不是没有网络，如果没有网络才提示通知 ，防止重复通知
                    NetStateManager.instance.netStateCallback.value?.let {
                        if (!it.isSuccess) {
                            //有网络了
                            NetStateManager.instance.netStateCallback.value =
                                NetState(isSuccess = true)
                        }
                        return
                    }
                    NetStateManager.instance.netStateCallback.value = NetState(isSuccess = true)
                }
            }
            isInit = false
        }
    }
}