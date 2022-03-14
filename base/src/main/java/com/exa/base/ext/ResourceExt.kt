package com.exa.base.ext

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import com.exa.base.base.appContext

/**
 * @author wwq
 * @description:
 * @date :2022/1/17
 */
fun getColor(@ColorRes colorRes: Int) =
    ResourcesCompat.getColor(appContext.resources, colorRes, null)

fun getDrawable(@DrawableRes drawableRes: Int) =
    ResourcesCompat.getDrawable(appContext.resources, drawableRes, null)

fun getString(resId: Int) = appContext.getString(resId)

fun getString(resId: Int, vararg args: Any) = appContext.getString(resId, args)

fun getDimension(@DimenRes id: Int) = appContext.resources.getDimension(id)