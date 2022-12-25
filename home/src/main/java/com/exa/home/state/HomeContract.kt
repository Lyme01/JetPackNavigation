package com.exa.home.state


import com.exa.base.bean.TitleBean
import com.exa.home.bean.ArticleBean
import com.exa.impl.UiState

sealed class HomeState:UiState{
    object Init:HomeState()
    data class Title(val titleBean: TitleBean=TitleBean("ssss")):HomeState()
    data class Success(val articleBean: ArticleBean):HomeState()
}