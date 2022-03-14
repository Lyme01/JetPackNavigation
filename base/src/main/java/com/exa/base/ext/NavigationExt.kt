package com.exa.base.ext

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.exa.base.R


/**
 * 描述 : 导航
 */
fun Fragment.nav(): NavController {
    return findNavController()
}

fun nav(view: View): NavController {
    return view.findNavController()
}

var lastNavTime = 0L

/**
 * 防止短时间内多次快速跳转 Fragment出现的bug
 * @param resId 跳转的action Id
 * @param bundle 传递的参数
 * @param checkLogin 跳转前是否需要检查登录状态
 * @param cleanStack 跳转前清理所有的栈
 * @param cleanStackId 跳转前清理指定的栈
 * @param interval 多少毫秒内不可重复点击 默认0.5秒
 */
fun NavController.navigateAction(
    resId: Int,
    bundle: Bundle? = null,
    checkLogin: Boolean = false,
    cleanStack: Boolean = false,
    cleanStackId: Int = -1,
    interval: Long = 500
) {
    val currentTime = System.currentTimeMillis()
    if (currentTime >= lastNavTime + interval) {
        lastNavTime = currentTime
        val optionBuilder = NavOptions.Builder()
            .setEnterAnim(R.anim.anim_enter)
            .setExitAnim(R.anim.anim_exit)
            .setPopEnterAnim(R.anim.anim_pop_enter)
            .setPopExitAnim(R.anim.anim_pop_exit)
            .setLaunchSingleTop(true)

        when {
//            checkLogin && MMKVUtil.token.isEmpty() -> {
//                navigate(R.id.global_action_to_login, bundle, optionBuilder.build())
//                return
//            }
//            cleanStack -> {
//                optionBuilder.setPopUpTo(R.id.nav_main, true)
//            }
            cleanStackId != -1 -> {
                optionBuilder.setPopUpTo(cleanStackId, true)
            }
        }
        runCatching { navigate(resId, bundle, optionBuilder.build()) }
    }
}

/**
 * 回退到哪个 Fragment
 * @param fragmentId FragmentId
 */
fun NavController.popBack(fragmentId: Int) {
    runCatching { popBackStack(fragmentId, false) }
}

