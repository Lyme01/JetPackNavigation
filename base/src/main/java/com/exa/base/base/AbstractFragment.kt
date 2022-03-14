package com.exa.base.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.exa.base.util.LogUtil
import com.exa.base.util.ToastUtil
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author wwq
 * @description:
 * @date :2022/1/17
 */
@Suppress("UNCHECKED_CAST")
abstract class AbstractFragment<VM:BaseVm<*>>(private val backPress:Boolean):Fragment() {
    protected lateinit var vm:VM
    protected lateinit var mContext: Context
    protected var isFirstLoad=true

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val type=(javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        vm=ViewModelProvider(this).get(type[0] as Class<VM>)
        if (!vm.isInitOwner()){
            vm.owner=this
        }
        LogUtil.e("TAG", "当前Fragment：${javaClass.simpleName}")
        return createView(type,inflater, container, savedInstanceState)
    }

    abstract fun createView(
        type: Array<Type>,
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lazyInit()
        registerUiChange()
        if (backPress){
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
                onBackPressed()
            }
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden){

        }
    }


    /**
     * 是否为沉浸式状态栏
     */
    open fun isImmersive(): Boolean = false

    open fun isDark():Boolean=true

    fun showToast(msg: String?) =ToastUtil.instance.show(mContext, msg)

    private var exitTime = 0L

    fun exitApp(){
        //是主页
        if (System.currentTimeMillis()-exitTime>1000){
            showToast("click again to exit app.")
            exitTime=System.currentTimeMillis()
        }else{
            ToastUtil.instance.cancel()
            activity?.finish()
        }
    }

    /**
     * 注册界面事件
     * 用于请求成功，错误的处理
     * 如：请求错误会触发 showPage 来显示错误页面
     */
    private fun registerUiChange(){
        vm.requestUi.showDialog.observe(viewLifecycleOwner,{
            vm.showLoadingDialog(activity)
        })
        vm.requestUi.dismissDialog.observe(viewLifecycleOwner, {
            vm.hideDialog()
        })
        vm.requestUi.toast.observe(viewLifecycleOwner, {
            ToastUtil.instance.show(mContext, it)
        })
    }

    /**
     * 是否需要懒加载
     */
    private fun lazyInit(){
        if (lifecycle.currentState==Lifecycle.State.STARTED&&isFirstLoad){
            initListener()
            initData()
            isFirstLoad=false
        }
    }

    open fun initTitle() = ""

    open fun initData() {}

    open fun initListener() {}

    open fun onBackPressed() {}

}