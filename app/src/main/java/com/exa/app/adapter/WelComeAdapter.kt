package com.exa.app.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.exa.app.R


/**
 * @author wwq
 * @description:
 * @date :2022/3/24
 */

class WelcomeAdapter: BaseQuickAdapter<Int, BaseViewHolder>(R.layout.item_welcome) {
    override fun convert(holder: BaseViewHolder, item: Int) {
        holder.setImageResource(R.id.iv_welcome_img,item)
    }
}