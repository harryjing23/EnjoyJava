package com.harry.kotlin.request

import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created on 2021/12/15.
 * @author harry
 */
object RequestApi : IRequest {


    // 派生和object都可以实现单例，但推荐object。需要传参时再使用派生，因为object不能传参
//    companion object {
//        fun instanceRequestApi(context: Context): IRequest = RequestApi()
//    }

    override fun instanceRequestAction(url: String, resultData: NetworkResultData) {
        commonOkHttpRequestAction(url, resultData)
    }

    // 用可变参数打造一个通用函数
    private fun commonOkHttpRequestAction(
        url: String,
        resultData: NetworkResultData,
        vararg values: String
    ) {

        // 1. 创建OkHttpClient
        val okHttpClient = OkHttpClient()

        // 2. 构建参数body，用表单形式
        val builder: MultipartBody.Builder = MultipartBody.Builder().setType(MultipartBody.FORM)

        // 3. 填入参数
        for (value in values) {
            builder.addFormDataPart("key", value)
        }

        // 4. 构建请求Request
        val request: Request = Request.Builder().url(url).post(builder.build()).build()

        // 5. 发送请求
        okHttpClient.newCall(request).enqueue(resultData)
    }

}