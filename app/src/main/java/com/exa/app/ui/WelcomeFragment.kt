package com.exa.app.ui


import android.view.View
import android.widget.LinearLayout.HORIZONTAL
import androidx.compose.ui.graphics.Color
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.drake.brv.utils.linear
import com.drake.brv.utils.models
import com.drake.brv.utils.setup
import com.exa.app.R
import com.exa.app.adapter.WelcomeAdapter
import com.exa.app.databinding.ItemDotBinding
import com.exa.app.databinding.WelcomeFragmentBinding
import com.exa.app.vm.WelcomeVm
import com.exa.base.base.BaseFragment
import com.exa.base.ext.buildSpannableString
import com.exa.base.ext.nav
import com.exa.base.ext.navigateAction
import com.exa.base.model.DotModel

/**
 * @author wwq
 * @description:
 * @date :2022/3/17
 */

class WelcomeFragment : BaseFragment<WelcomeVm, WelcomeFragmentBinding>(false) {
    private val welcomeAdapter = WelcomeAdapter()

    override fun initData() {
        binding.rvDot.linear(HORIZONTAL, scrollEnabled = false).setup {
            addType<DotModel>(R.layout.item_dot)
            onBind {
                val binding = ItemDotBinding.bind(itemView)
                binding.checkDotId.isChecked = getModel<DotModel>().check
            }
        }.models=dots
        welcomeAdapter.setNewInstance(
            mutableListOf(
                R.mipmap.ic_guide1,
                R.mipmap.ic_guide2,
                R.mipmap.ic_guide3
            )
        )
    }

    override fun initViews(root: View) {
        super.initViews(root)
//        binding.tv.buildSpannableString {
//            addText("我已详细阅读并同意"){
//                setFontSize(16,true)
//                setBackgroundColor(R.color.color_click)
//            }
//            addText("《隐私政策》"){
//                setColor(R.color.color_main)
//                setFontSize(14,true)
//            }
//        }
        binding.vpPager.adapter = welcomeAdapter
        binding.vpPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                dots.forEachIndexed { index, dotModel ->
                    dots[index].check=index==position
                }
                binding.rvDot.models=dots
                if (position == dots.size - 1) {
                    binding.rvDot.isVisible = false
                    binding.btnApplyNow.isVisible = true
                } else {
                    binding.rvDot.isVisible = true
                    binding.btnApplyNow.isVisible = false
                }
            }
        })
    }

    private var dots=mutableListOf(
        DotModel(true),
        DotModel(false),
        DotModel(false)
    )

    override fun initListener() {
        binding.btnApplyNow.setOnClickListener {
            nav().navigateAction(R.id.action_to_global_main)
        }
    }
}