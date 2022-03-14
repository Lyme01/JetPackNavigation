package com.exa.base.base

import com.exa.base.util.SingleLiveEvent

/**
 * @author wwq
 * @description:
 * @date :2022/1/17
 */

class UiChange {
    val showDialog by lazy { SingleLiveEvent<String>() }
    val dismissDialog by lazy { SingleLiveEvent<Void>() }
    val toast by lazy { SingleLiveEvent<String>() }
}