package com.harry.kotlin.request

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * Created on 2021/12/14.
 * @author harry
 */

// 对OkHttp的Callback封装一层，目的：1. 切换成主线程 2. 将数据过滤
abstract class NetworkResultData : Callback {
    private val TAG = "NetworkResultData"

    override fun onFailure(call: Call, e: IOException) {
        Log.d(TAG, "onFailure: ${e.message}")

        Handler(Looper.getMainLooper(), object : Handler.Callback {
            override fun handleMessage(msg: Message): Boolean {
                e.message?.let { requestError(it) }
                return false
            }
        }).sendEmptyMessage(0)
    }

    override fun onResponse(call: Call, response: Response) {
        Log.d(TAG, "onResponse: ")

        Handler(Looper.getMainLooper(), Handler.Callback {
            requestSuccess(response)
            // 因为lambda表达式是表达式，所以没有return关键字，最后一条语句就是返回值
            false
        }).sendEmptyMessage(0)
    }


    abstract fun requestError(info: String)
    abstract fun requestSuccess(result: Response)

}