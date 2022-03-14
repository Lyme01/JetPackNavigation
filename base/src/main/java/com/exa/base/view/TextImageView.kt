package com.exa.base.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import com.exa.base.R

/**
 * 描述 : 可以显示固定大小图片的 TextView
 * 通过 xml的 src 或者 setImage 设置图片
 * @see R.styleable.AabbTextImageView_src
 * @see setImage
 * 作者 :fan
 * 日期 :2021/11/3
 */
class TextImageView(context: Context, attrs: AttributeSet) :
    androidx.appcompat.widget.AppCompatTextView(context, attrs) {
    private val dp24 = resources.getDimension(R.dimen.dp_24).toInt()

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.TextImageView)
        val image = ta.getDrawable(R.styleable.TextImageView_src)
        setDrawable(image)
        ta.recycle()
    }

    fun setImage(@DrawableRes res: Int, imgSize: Int = dp24) {
        val imgDrawable = ResourcesCompat.getDrawable(resources, res, null)
        setDrawable(imgDrawable, imgSize)
    }

    private fun setDrawable(drawable: Drawable?, imgSize: Int = dp24) {
        drawable?.let {
            it.setBounds(0, 0, imgSize, imgSize)
            setCompoundDrawables(it, null, null, null)
        }
    }
}