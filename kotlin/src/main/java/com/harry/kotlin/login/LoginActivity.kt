package com.harry.kotlin.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.harry.kotlin.MainActivity
import com.harry.kotlin.R
import com.harry.kotlin.base.BaseActivity
import com.harry.kotlin.entity.LoginRegisterResponse
import com.harry.kotlin.login.interf.LoginPresenter
import com.harry.kotlin.login.interf.LoginView
import com.harry.kotlin.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*


// View层
class LoginActivity : BaseActivity<LoginPresenter>(), LoginView {
    private val TAG = "LoginActivity"

//    lateinit var p: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 类::class.java 才相当于Java的.class
//        val instanceRetrofit: WanAndroidAPI =
//            APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)


        // 在build.gradle中apply plugin: 'kotlin-android-extensions' 才会有自动findviewbyid
        bt_login.setOnClickListener(onClickListener)
        bt_register.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        })

//        p = LoginPresenterImpl(this)
    }

    // lambda表达式
    private val onClickListener = View.OnClickListener { view ->
        // Kotlin中自动调用Java的get/set。用了=，就调用set
        when (view.id) {
            R.id.bt_login -> {
                val username = et_username.text.toString()
                val password = et_pwd.text.toString()
                Log.d(TAG, "username: $username, password: $password")

                // 拿到P层来干活，而不是自己干
                presenter.loginAction(this@LoginActivity, username, password)

            }
        }
    }

    override fun loginSuccess(loginRegisterBean: LoginRegisterResponse?) {
        // Kotlin没有LoginActivity.this，用类自带的标签。this@LoginActivity
        Toast.makeText(this@LoginActivity, "登录成功", Toast.LENGTH_SHORT).show()

        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
    }

    override fun loginFailure(errorMsg: String?) {
        Toast.makeText(this@LoginActivity, "登录失败", Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun createP(): LoginPresenter = LoginPresenterImpl(this)

    override fun recycle() {
        presenter.unAttachView()
    }
}