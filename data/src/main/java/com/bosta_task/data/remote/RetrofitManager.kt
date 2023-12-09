package com.bosta_task.data.remote

import com.bosta_task.data.remote.webService.baseUrl.BaseUrl
import com.bosta_task.domain.apiManager.ApiManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitManager @Inject constructor():ApiManager {
    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BaseUrl.BOSTA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }
    override fun <T> buildWebService(webService: Class<T>): T = retrofit.create(webService)
}