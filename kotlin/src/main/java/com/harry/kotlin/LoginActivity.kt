package com.harry.kotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.harry.kotlin.api.WanAndroidAPI
import com.harry.kotlin.net.APIClient
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        // 类::class.java 才相当于Java的.class
        val instanceRetrofit: WanAndroidAPI =
            APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)


        // 在build.gradle中apply plugin: 'kotlin-android-extensions' 才会有自动findviewbyid
        bt_login.setOnClickListener()
    }

    // lambda表达式
    private val onClickListener = View.OnClickListener { view ->
        // Kotlin中自动调用Java的get/set。用了=，就调用set
        when (view.id) {

        }
    }
}