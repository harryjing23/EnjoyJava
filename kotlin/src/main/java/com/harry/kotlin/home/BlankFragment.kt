package com.harry.kotlin.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.harry.kotlin.R
import com.harry.kotlin.request.NetworkResultData
import com.harry.kotlin.request.RequestApi
import okhttp3.Response


class BlankFragment : Fragment() {
    private val TAG = "BlankFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // ScrollView内嵌套的ListView，ListView会显示不全，RecyclerView则不会。解决方案见CustomListView


        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requestHomeData()
    }

    private fun requestHomeData() {
        RequestApi.instanceRequestAction("url", object : NetworkResultData() {
            override fun requestError(info: String) {
                showResultSuccess()
            }

            override fun requestSuccess(result: Response) {
                try {
                    // body为响应体，可能为null
                    val resultData: String = result.body?.string().toString()
                    // 将JSON字符串解析成Bean
                    val homeDataResponse: HomeDataResponse =
                        Gson().fromJson(resultData, HomeDataResponse::class.java)

                    shoResultError(homeDataResponse)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e(TAG, "requestSuccess: ${e.message}")
                }

            }

        })
    }

    private fun shoResultError(homeDataResponse: HomeDataResponse) {
        // 展示UI
    }

    private fun showResultSuccess() {
        // 展示UI
    }
}

// 类似于给泛型增加扩展函数。在四大组件中可以直接调用toast函数
fun Context.toast(info: String) {
    Toast.makeText(this, info, Toast.LENGTH_SHORT).show()
}