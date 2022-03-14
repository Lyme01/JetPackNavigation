package com.exa.base.util

import android.app.Dialog
import android.content.Context
import android.view.View
import com.exa.base.R

/**
 * 描述: Dialog 和 popupWindow
 * 作者: fan
 * 时间: 2021/11/03
 */

object DialogUtil {

    fun createDialog(context: Context, view: View): Dialog {
        return Dialog(context, R.style.NormalDialog).apply {
            setContentView(view)
        }
    }

}