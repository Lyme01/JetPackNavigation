package com.exa.base.ext

import android.graphics.Color
import android.widget.ImageView
import androidx.annotation.DimenRes
import com.exa.base.R
import com.exa.base.util.GlideUtil

/**
 * @author wwq
 * @description:
 * @date :2022/1/17
 */

fun ImageView.load(any:Any)=GlideUtil.load(this,any)

/**
 * 加载圆形图片
 */
fun ImageView.loadCircle(any: Any) = GlideUtil.loadCircle(this, any)

/**
 * 加载带边框圆形图片
 */
fun ImageView.loadBorderCircle(any: Any, borderWidth: Float = getDimension(R.dimen.dp_2), borderColor: Int = Color.WHITE) =
    GlideUtil.loadBorderCircle(this, any, borderWidth, borderColor)

/**
 * 加载圆角图片
 * @param dimenRes 圆角的大小
 */
fun ImageView.loadRound(any: Any, @DimenRes dimenRes: Int = R.dimen.dp_5) =
    GlideUtil.loadRound(this, any, dimenRes)
