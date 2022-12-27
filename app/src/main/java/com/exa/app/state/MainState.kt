package com.exa.app.state


import com.exa.impl.UiState

sealed class MainState:UiState{
    data class Title( val title:String=""): MainState()
}