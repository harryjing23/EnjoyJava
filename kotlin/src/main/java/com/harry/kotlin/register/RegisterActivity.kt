package com.harry.kotlin.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.harry.kotlin.R
import com.harry.kotlin.base.BaseActivity
import com.harry.kotlin.entity.LoginRegisterResponse
import com.harry.kotlin.register.interf.RegisterPresenter
import com.harry.kotlin.register.interf.RegisterView
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity<RegisterPresenter>(), RegisterView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideActionBar()

        bt_register.setOnClickListener(View.OnClickListener {
            presenter.registerWanAndroid(
                this@RegisterActivity,
                et_username.text.toString(),
                et_password.text.toString(),
                et_repassword.text.toString()
            )
        })
    }

    override fun getLayoutId(): Int = R.layout.activity_register

    override fun createP(): RegisterPresenter = RegisterPresenterImpl(this@RegisterActivity)


    override fun recycle() {
        presenter.unAttachView()
    }

    override fun registerSuccess(loginRegisterBean: LoginRegisterResponse?) {
        Toast.makeText(this@RegisterActivity, "注册成功", Toast.LENGTH_SHORT).show()
    }

    override fun registerFailure(errorMsg: String?) {
        Toast.makeText(this@RegisterActivity, "注册失败", Toast.LENGTH_SHORT).show()
    }
}