package com.exa.home.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.exa.base.base.BaseRepository
import com.exa.base.base.BaseVm
import com.exa.base.bean.BaseResponse
import com.exa.home.bean.ArticleBean
import com.exa.home.http.api
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * @author wwq
 * @description:
 * @date :2022/3/17
 */

class HomeVm : BaseVm() {
//    fun getArticle(
//        success: (data: ArticleBean) -> Unit = {},
//        error: () -> Unit = {}
//    ){
//        request({ api.getArticle() },
//       { success.invoke(it)
//        },{
//           println("yyyy")
//        })
//    }

    val articleBean=MutableLiveData<ArticleBean>()
    val articleBeanFlow= MutableStateFlow(ArticleBean())
    private val toastChannel=Channel<Boolean>()
    val toastFlow=toastChannel.receiveAsFlow()
    val navigationEvent=MutableSharedFlow<Boolean>()


    fun getArticle() {
        doRequest({
            api.getArticle()
        }, success = {
          articleBeanFlow.value=it
        }, {
            println("yyyy")
        })
    }

    fun handleAction(){
        viewModelScope.launch {
            navigationEvent.emit(value = true)
        }
    }

//    fun getArticle(){
//        viewModelScope.launch {
//            launch1 {
//                api.getArticle()
//            }.catch {
//              println("66666")
//            }.collect{
//                it.data?.let { it1 -> articleBeanFlow.emit(it1) }
//            }
//
//        }
//    }

    fun getToast(value:Boolean){
        viewModelScope.launch {
            toastChannel.send(value)
        }
    }
}


