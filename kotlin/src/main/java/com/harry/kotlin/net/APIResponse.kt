package com.harry.kotlin.net

import android.content.Context
import com.harry.kotlin.entity.LoginRegisterResponseWrapper
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Created on 2021/11/15.
 * @author harry
 */

// 拦截。自定义observer操作符。目的：将包装Bean拆成两份，成功给数据Bean，失败给错误信息
abstract class APIResponse<T>(val context: Context)// 主构造传Context以便显示UI
    : Observer<LoginRegisterResponseWrapper<T>> {

    private var isShow: Boolean = true

    // 次构造的参数不需要加val/var
    // 类型后面加"=值"，为变量设置默认值
    constructor(context: Context, isShow: Boolean = false) : this(context) {
        this.isShow = isShow
    }

    abstract fun success(data: T?)

    abstract fun failure(errorMsg: String?)

    // ------------------------------------------------rxjava

    // 事件开始
    override fun onSubscribe(d: Disposable) {
        // todo 弹出loading框
    }

    // 上游流下了数据
    override fun onNext(t: LoginRegisterResponseWrapper<T>) {
        if (t.data == null) {
            failure("没有数据。msg: ${t.errorMsg}")
        } else {
            success(t.data)
        }
    }

    // 上游流下了错误
    override fun onError(e: Throwable) {
        failure("发生错误。msg: ${e.message}")
    }

    // 事件结束
    override fun onComplete() {
        // todo 取消loading框
    }
}