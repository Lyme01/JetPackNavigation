package com.exa.home.state


import com.exa.home.bean.ArticleBean
import com.exa.impl.UiState

sealed class HomeState: UiState {
    object Init:HomeState()

    data class Success(val articleBean: ArticleBean):HomeState()
}