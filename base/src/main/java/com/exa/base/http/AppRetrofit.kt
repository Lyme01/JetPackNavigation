package com.exa.base.http


import com.exa.base.BuildConfig
import com.exa.base.base.appContext
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * 描述：AppRetrofit
 * 日期：2021/11/03
 * @author fan
 */
object AppRetrofit {

    private val instance: Retrofit by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        getRetrofit()
    }

    fun <T> createApi(service: Class<T>): T {
        return instance.create(service)
    }

    private fun getRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okhttp = getOkhttpClick()

        return Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .client(okhttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getOkhttpClick(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val timeOut = 10L
        val cacheSize = 10 * 1024 * 1024L
        return OkHttpClient()
            .newBuilder()
            .cache(Cache(File(appContext.cacheDir, "network_cache"), cacheSize))
            .cookieJar(getCookieJar())
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private fun getCookieJar(): PersistentCookieJar = PersistentCookieJar(
        SetCookieCache(),
        SharedPrefsCookiePersistor(appContext)
    )

}