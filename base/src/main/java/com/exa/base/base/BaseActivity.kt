package com.exa.base.base

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.exa.base.http.state.NetStateManager
import com.exa.base.http.state.NetworkStateReceive
import java.lang.reflect.ParameterizedType

/**
 * @author wwq
 * @description:
 * @date :2022/1/17
 */

abstract class BaseActivity<VM:BaseVm<*>,VB:ViewBinding>:FragmentActivity() {
    protected lateinit var vm:VM
    protected lateinit var binding: VB
    private lateinit var mNetWorkReceiver:BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation=ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val type=(javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        vm=ViewModelProvider(this).get(type[0] as Class<VM>)
        vm.owner=this

        val vbMethod=vm.getInflateMethod((type[1] as Class<VB>))
        binding=vbMethod.invoke(null,layoutInflater) as VB
        setContentView(binding.root)
        initViews()
        initData()
        initListener()

        NetStateManager.instance.netStateCallback.observe(this,{
            if (!it.isSuccess){

            }
        })

    }

    abstract fun initViews()

    open fun initData() {}

    open fun initListener() {}

    override fun onResume() {
        super.onResume()
        mNetWorkReceiver=NetworkStateReceive()
        registerReceiver(mNetWorkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(mNetWorkReceiver)
    }
}