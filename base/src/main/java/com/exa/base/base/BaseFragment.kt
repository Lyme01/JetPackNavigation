package com.exa.base.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.exa.base.R
import com.exa.base.util.StatusBarUtil
import com.exa.base.view.TextImageView
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


/**
 * @author wwq
 * @description:backPress 是否需要 Fragment 来控制物理返回键,若需要重写 onBackPress()
 * @date :2022/1/17
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<VM : BaseVm, VB : ViewBinding>(
    private val navigationBar: Boolean = false,
   val backPress: Boolean = false
) :AbstractFragment<VM>(backPress = backPress) {
     protected lateinit var binding: VB
     var lastView: View?=null
     lateinit var mActivity: FragmentActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity=context as FragmentActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (lastView != null) {
            return lastView
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun createView(
        type: Array<Type>,
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (navigationBar){
            val vbMethod = vm.getInflateMethod((type[1] as Class<VB>), 3)
            val view = inflater.inflate(R.layout.item_action_bar, container, false)
            binding = vbMethod.invoke(null, layoutInflater, view, false) as VB
            view
        }else{
            val vbMethod = vm.getInflateMethod((type[1] as Class<VB>))
            binding = vbMethod.invoke(null, layoutInflater) as VB
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (lastView==null){
            if (navigationBar){
                initNavigationBar(view)
            }
            initViews(view)
            StatusBarUtil.setStatusBar(activity,isImmersive(),isDark())
        }
        lastView=view
        super.onViewCreated(view, savedInstanceState)
    }

    open fun initViews(root:View){}

    private fun initNavigationBar(view:View){
        view.findViewById<LinearLayout>(R.id.action_bar_root).addView(binding.root)
        view.findViewById<TextView>(R.id.action_bar_text_title).text = initTitle()
        val left = view.findViewById<ImageView>(R.id.action_bar_img_left).apply {
            setOnClickListener { findNavController().popBackStack() }
        }
        val right = view.findViewById<TextImageView>(R.id.action_bar_text_right)
        modifyActionBar(left, right)
    }

    /**
     * 操作标题栏
     * @param left 左边 ImageView
     * @param right 右边 TextImageView
     */
    open fun modifyActionBar(left: ImageView, right: TextImageView) {}

}