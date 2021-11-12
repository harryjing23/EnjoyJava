package com.harry.kotlin.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created on 2021/11/12.
 * @author harry
 */
class APIClient {

    object Holder {
        val INSTANCE = APIClient()
    }

    companion object {
        val instance = Holder.INSTANCE
    }


    // 创建API
    // Java中是方法的泛型定义在返回类型前面。Kotlin定义在fun后面
    fun <T> instanceRetrofit(apiInterface: Class<T>): T {

        val mOkHttpClient = OkHttpClient().newBuilder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()


        val retrofit: Retrofit = Retrofit.Builder()
            // 请求
            .client(mOkHttpClient)

            // 响应
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

            .build()

        return retrofit.create(apiInterface)
    }
}