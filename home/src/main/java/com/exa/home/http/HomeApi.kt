package com.exa.home.http

import com.exa.base.bean.BaseResponse
import com.exa.base.http.AppRetrofit
import com.exa.home.bean.ArticleBean
import retrofit2.http.GET

/**
 * @author wwq
 * @description:
 * @date :2022/3/24
 */

val api:HomeApi by lazy{ AppRetrofit.createApi(HomeApi::class.java)}
interface HomeApi {
    @GET("article/list/0/json")
    suspend fun getArticle():BaseResponse<ArticleBean>
}