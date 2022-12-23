package com.exa.base.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @author wwq
 * @description:
 * @date :2022/12/23
 */

inline fun  Fragment.launchAndRepeatWithViewLifecycle(
    minActiveState:Lifecycle.State=Lifecycle.State.STARTED,
    crossinline block:suspend CoroutineScope.()->Unit
){
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState){
            block()
        }
    }
}
//https://www.modb.pro/db/519585
inline  fun <T> Flow<T>.flowWithLifecycle(
    lifecycleOwner: LifecycleOwner,
    minActiveState:Lifecycle.State=Lifecycle.State.STARTED,
    crossinline block:(T)->Unit
)=lifecycleOwner.lifecycleScope.launch {
    flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState).collectLatest {
            block.invoke(it)
    }
}