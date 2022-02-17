package com.harry.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // 用runBlocking将线程环境切换到协程环境。即有两个协程
    fun click1(view: android.view.View) = runBlocking {// 外协程
        // 默认是主线程，为launch指定参数即指定线程
        launch(Dispatchers.IO) {// 内协程
            Log.e(TAG, "click1: launch ${Thread.currentThread().name}")

            repeat(10) {
                Thread.sleep(1000)
                Log.d(TAG, "click1: 计数${it}")
            }
        }
    }

    // 协程的应用
    fun click2(view: android.view.View) = runBlocking {
        launch {
            // 这里是主线程

            // async可以返回线程的执行结果。默认也是主线程，通过参数指定为IO子线程
            val def = async(Dispatchers.IO) {
                // 这里是子线程
                Thread.sleep(1000)
                "模拟网络请求的结果"
            }

            // 这里是主线程
            // 用了高阶，所以async返回什么类型，await就得到什么类型
            tv_show.text = def.await()
        }


        // 简化版
        launch {
            tv_show.text = async(Dispatchers.IO) {
                Thread.sleep(1000)
                "模拟网络请求的结果"
            }.await()
        }
    }

    // 协程中频繁切换线程。对比RxJava的功能
    fun click3(view: android.view.View) = runBlocking {
        // 因为用的runBlocking，是阻塞式的

        Log.d(TAG, "click3: ${Thread.currentThread().name}")

        // 这里是主线程
        withContext(Dispatchers.IO) {
            // 这里是子线程
            Log.d(TAG, "click3: ${Thread.currentThread().name}")
        }

        // 这里是主线程
        Log.d(TAG, "click3: ${Thread.currentThread().name}")

        withContext(Dispatchers.IO) {
            // 这里是子线程
            Log.d(TAG, "click3: ${Thread.currentThread().name}")
        }

        // 这里是主线程
        Log.d(TAG, "click3: ${Thread.currentThread().name}")
    }


    // 与上面的区别到底是什么？？？
    // 非阻塞的方式，尽量不用阻塞式协程
    fun click4(view: android.view.View) {
        // 只用了一个协程
        GlobalScope.launch(Dispatchers.Main) {
            // 这里是主线程
            Log.d(TAG, "click3: ${Thread.currentThread().name}")

            withContext(Dispatchers.IO) {
                // 这里是子线程
                Log.d(TAG, "click3: ${Thread.currentThread().name}")
            }

            // 这里是主线程
            Log.d(TAG, "click3: ${Thread.currentThread().name}")

            withContext(Dispatchers.IO) {
                // 这里是子线程
                Log.d(TAG, "click3: ${Thread.currentThread().name}")
            }

            // 这里是主线程
            Log.d(TAG, "click3: ${Thread.currentThread().name}")
        }
    }


}

/*
线程：
操作系统来调度
依附于进程，对进程而言，线程是轻量级的
创建一万个线程，会崩溃，内存溢出
 */

/*
协程：
用户代码来控制
对线程而言，协程是轻量级的
创建一万个协程，没问题
 */

/**
 * 协程可参考“Kotlin中文社区”的简书文章
 * 比RxJava切换线程更方便
 *
 * 协程的原理视频？？？
 */

fun test01() {

    // GlobalScope.launch类似于守护线程，若主线程执行完毕，里面的子线程则不再执行
    GlobalScope.launch {
        delay(2000)
        println("1111")// 不会打印
    }

    println("A")
}

suspend fun test02() {
    val job = GlobalScope.launch {
        repeat(100) {
            delay(40)
            println(it)
        }
    }
    println("A")
    Thread.sleep(100)
    // 取消协程。时间不准确，会有一点时间差
    job.cancel()
    // 立刻取消协程。且函数要加上suspend
    job.cancelAndJoin()
}