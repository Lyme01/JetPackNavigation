package com.exa.base.state

import com.exa.impl.UiState

sealed class CommonState:UiState {
    object Common:CommonState()
}