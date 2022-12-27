package com.exa.home.action

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.exa.impl.Action

sealed class HomeAction:Action {
    data class GetArticle(val page:Int):HomeAction()
    data class GoPage(val fragment: Fragment, val resId: Int,
                      val bundle: Bundle? = null,):HomeAction()
}