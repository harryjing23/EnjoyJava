package com.harry.kotlin.login

import android.content.Context
import com.harry.kotlin.entity.LoginRegisterResponse
import com.harry.kotlin.login.interf.LoginModel
import com.harry.kotlin.login.interf.LoginPresenter
import com.harry.kotlin.login.interf.LoginView

/**
 * Created on 2021/11/26.
 * @author harry
 */

// P层需要拿到M和V
// 若主构造设置为private，括号前加上private constructor
class LoginPresenterImpl(var loginView: LoginView?) : LoginPresenter,
    LoginPresenter.OnLoginListener {

    private val loginModel: LoginModel = LoginModelImpl()

    override fun loginAction(context: Context, username: String, password: String) {


        // 一大堆校验等前置工作
        // 请求
        loginModel.login(context, username, password, this)

    }

    override fun unAttachView() {
        loginView = null
        loginModel.cancelRequest()
    }

    // 接收Model回调
    override fun loginSuccess(loginRegisterBean: LoginRegisterResponse?) {
        // 一大堆校验等前置工作

        loginView?.loginSuccess(loginRegisterBean)
    }

    override fun loginFailure(errorMsg: String?) {
        // 一大堆校验等前置工作

        loginView?.loginFailure(errorMsg)
    }


}