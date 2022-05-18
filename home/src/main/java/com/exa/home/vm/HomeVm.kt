package com.exa.home.vm

import androidx.lifecycle.MutableLiveData
import com.exa.base.base.BaseRepository
import com.exa.base.base.BaseVm
import com.exa.base.http.util.request
import com.exa.home.bean.ArticleBean
import com.exa.home.http.api

/**
 * @author wwq
 * @description:
 * @date :2022/3/17
 */

class HomeVm:BaseVm() {
    fun getArticle(
        success: (data: ArticleBean) -> Unit = {},
        error: () -> Unit = {}
    ){
        request({ api.getArticle() },
       { success.invoke(it)
        },{
           println("yyyy")
        })
    }
}