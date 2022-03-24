package com.exa.home.vm

import androidx.lifecycle.MutableLiveData
import com.exa.base.base.BaseRepository
import com.exa.base.base.BaseVm
import com.exa.home.bean.ArticleBean
import com.exa.home.http.api

/**
 * @author wwq
 * @description:
 * @date :2022/3/17
 */

class HomeVm:BaseVm<BaseRepository>() {
    val articleBean=MutableLiveData<ArticleBean>()
    fun getArticle()=launch {
       articleBean.value= doRequest { api.getArticle() }
    }
}