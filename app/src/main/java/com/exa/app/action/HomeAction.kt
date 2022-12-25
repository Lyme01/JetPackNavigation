package com.exa.app.action

import androidx.fragment.app.FragmentManager
import com.exa.impl.Action

sealed class MainAction:Action {
    data class Switch(val fragmentManager: FragmentManager, val position:Int): MainAction()
}