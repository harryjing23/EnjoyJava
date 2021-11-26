package com.harry.kotlin.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

/**
 * Created on 2021/11/26.
 * @author harry
 */

// Activity的Base
// 限定泛型也是用Kotlin的语法，但是多个限定要在后面用where追加
// 下面等同于<P extends LoginPresenter & Serializable>
//abstract class BaseActivity<P> : AppCompatActivity() where P : LoginPresenter, P : Serializable {
abstract class BaseActivity<P : IBasePresenter> : AppCompatActivity() {

    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        // 可以写很多抽象步骤，不关心细节实现，交给子类

        presenter = createP()

        setContentView(getLayoutId())
    }

    abstract fun getLayoutId(): Int

    abstract fun createP(): P

    // 在必要的位置调用抽象函数，使子类必须实现
    override fun onDestroy() {
        super.onDestroy()
        recycle()
    }

    abstract fun recycle()

    // 还可以有公共方法暴露给子类
    fun hideActionBar() {
        // 调用Java的东西，都要用?接收，因为可能为空
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
    }
}