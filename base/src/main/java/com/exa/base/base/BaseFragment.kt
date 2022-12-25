package com.exa.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.exa.base.R
import com.exa.base.bean.TitleBean
import com.exa.base.util.StatusBarUtil
import com.exa.base.view.TextImageView
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB : ViewBinding>(
    private val navigationBar: Boolean = false,
    val backPress: Boolean = false
) : Fragment() {
    protected lateinit var binding: VB
    private var mHasLoadedData = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        StatusBarUtil.setStatusBar(activity,isImmersive,isDark)
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        val aClass = type[0] as Class<*>
        return if (navigationBar){
            val method = aClass.getMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java
            )
            val view = inflater.inflate(R.layout.item_action_bar, container, false)
            binding = method.invoke(null, layoutInflater, view, false) as VB
            view
        }else{
            val method = aClass.getMethod("inflate", LayoutInflater::class.java)
            binding = method.invoke(null, layoutInflater) as VB
            binding.root
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (navigationBar){
            initNavigationBar(view)
        }
        registerUIStateCallback()
        super.onViewCreated(view, savedInstanceState)
    }


    private fun initNavigationBar(view:View){
        view.findViewById<LinearLayout>(R.id.action_bar_root).addView(binding.root)
        view.findViewById<TextView>(R.id.action_bar_text_title).text = setTitle().title
        if (setTitle().hasLeft){
            view.findViewById<ImageView>(R.id.action_bar_img_left).apply {
                setTitle().leftImage?.let { setImageResource(it) }
                setOnClickListener { setTitle().leftClick.invoke() }
            }
        }
       view.findViewById<TextImageView>(R.id.action_bar_text_right).apply {
           setTitle().rightImage?.let { setImage(it) }
           setOnClickListener {setTitle().rightClick}
           text=setTitle().rightText
       }

    }


    override fun onResume() {
        super.onResume()
        if (!mHasLoadedData) {
            loadPageData()
            mHasLoadedData = true
        }
        resumeData()
    }

    open fun resumeData() {

    }
    open fun setTitle(titleBean: TitleBean= TitleBean()):TitleBean{
        return titleBean
    }
    open fun modifyActionBar(left: ImageView, right: TextImageView) {}
    open fun initTitle() = ""
    /**
     * 是否为沉浸式状态栏
     * */
    open var isImmersive: Boolean = false
    open var isDark:Boolean=true
    abstract fun registerUIStateCallback()
    abstract fun loadPageData()
}