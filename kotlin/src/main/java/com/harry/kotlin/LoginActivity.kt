package com.harry.kotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.harry.kotlin.api.WanAndroidAPI
import com.harry.kotlin.entity.LoginResponse
import com.harry.kotlin.net.APIClient
import com.harry.kotlin.net.APIResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        // 类::class.java 才相当于Java的.class
        val instanceRetrofit: WanAndroidAPI =
            APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)


        // 在build.gradle中apply plugin: 'kotlin-android-extensions' 才会有自动findviewbyid
        bt_login.setOnClickListener(onClickListener)
    }

    // lambda表达式
    private val onClickListener = View.OnClickListener { view ->
        // Kotlin中自动调用Java的get/set。用了=，就调用set
        when (view.id) {
            R.id.bt_login -> {
                val username = et_username.text.toString()
                val password = et_pwd.text.toString()
                Log.d(TAG, "username: $username, password: $password")

                APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
                    // rxjava
                    .loginAction(username, password)
                    .subscribeOn(Schedulers.io())// 上面分配异步线程
                    .observeOn(AndroidSchedulers.mainThread())// 下面分配主线程
//                    .subscribe(object : Consumer<LoginResponseWrapper<LoginResponse>> {
//                        override fun accept(t: LoginResponseWrapper<LoginResponse>?) {
//                            // 拿到包装Bean。更新UI
//                        }
//                    })
                    // object: 标识匿名内部类
                    .subscribe(object : APIResponse<LoginResponse>(this) {
                        override fun success(data: LoginResponse?) {
                            Log.d(TAG, "success: $data")

                            // Kotlin没有LoginActivity.this，用类自带的标签。this@LoginActivity
                            Toast.makeText(this@LoginActivity, "登录成功", Toast.LENGTH_SHORT).show()
                        }

                        override fun failure(errorMsg: String?) {
                            Log.d(TAG, "failure: $errorMsg")

                            Toast.makeText(this@LoginActivity, "登录失败", Toast.LENGTH_SHORT).show()
                        }

                    })


            }
        }
    }
}