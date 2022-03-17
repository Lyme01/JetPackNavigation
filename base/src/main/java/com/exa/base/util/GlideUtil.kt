package com.exa.base.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.exa.base.R
import com.exa.base.ext.getDimension

/**
 * @author wwq
 * @description:
 * @date :2022/1/17
 */

object GlideUtil {
    fun load(imageView: ImageView, any: Any){
        Glide.with(imageView.context).load(any).into(imageView)
    }

    fun loadCircle(imageView: ImageView, any: Any) {
        Glide.with(imageView.context).load(any).apply(circleOption()).into(imageView)
    }


//    fun loadBorderCircle(imageView: ImageView, any: Any, borderWidth: Float, borderColor: Int) {
//        Glide.with(imageView.context)
//            .load(any)
//            .apply(borderCircleOption(borderWidth, borderColor))
//            .into(imageView)
//    }

    fun loadRound(imageView: ImageView, any: Any, round: Int) {
        Glide.with(imageView.context)
            .load(any)
            .centerCrop()
            .transform(RoundedCorners(getDimension(round).toInt()))
            .into(imageView)
    }


    private fun circleOption(): RequestOptions {
        return RequestOptions()
            .circleCrop()
            .error(R.mipmap.ic_launcher) //错误占位符
            .diskCacheStrategy(DiskCacheStrategy.NONE) //缓存策略
    }

//    private fun borderCircleOption(borderWidth: Float, borderColor: Int): RequestOptions {
//        return circleOption().transform(GlideCircleBorderTransform(borderWidth, borderColor))
//
//    }
}