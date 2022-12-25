package com.exa.base.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exa.base.bean.BaseResponse
import com.exa.base.http.exc.AppException
import com.exa.base.http.exc.ExceptionHandle
import com.exa.impl.Action
import com.exa.impl.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<State:UiState,Effect:Action>:ViewModel() {

    protected val _state:MutableStateFlow<State> = MutableStateFlow(initStates())
    val uiState:StateFlow<State>
           get() = _state
    private val userIntent = MutableSharedFlow<Effect>()
    abstract fun initStates():State
    fun dispatch(viewAction: Effect) {
        viewModelScope.launch {
            userIntent.emit(viewAction)
        }
    }

    fun handleAction(action: (effect:Effect)->Unit){
        viewModelScope.launch {
            userIntent.collectLatest {
                action.invoke(it)
            }
        }
    }



    fun <T> doRequest(
        block:suspend  () -> BaseResponse<T>,
        success:(T) ->Unit,
        onError:(AppException) -> Unit={},
    ) =  viewModelScope.launch {
        flow {
            val response = block.invoke()
            if (response.errorCode != 0) {
                throw  AppException(response.errorCode, response.errorMsg)
            }
            emit(response)
        }.flowOn(Dispatchers.IO).onStart {

        }.catch { exc,->
            onError.invoke(ExceptionHandle.handleException(exc))
        }.onCompletion {

        }.collect{
            it.data?.let { it1 -> success.invoke(it1) }
        }
    }
}