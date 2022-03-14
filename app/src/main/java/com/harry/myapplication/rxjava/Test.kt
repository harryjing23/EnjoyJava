package com.harry.myapplication.rxjava

import com.harry.myapplication.rxjava.wanandroid.HttpUtil
import com.harry.myapplication.rxjava.wanandroid.ItemBean
import com.harry.myapplication.rxjava.wanandroid.WanAndroidApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created on 2021/9/26.
 *
 * @author harry
 */
class Test private constructor() {
    private fun tt() {
        val observable: Observable<*>? = null
        observable!!.compose(object : ObservableTransformer<Any?, Any?> {
            override fun apply(upstream: Observable<*>): ObservableSource<*> {
                return upstream.map { null }
            }
        })

        // 2s的防抖
//        RxView.clicks(null)
//                .throttleFirst(2000, TimeUnit.MILLISECONDS)
//                // 让下面的代码运行在io线程
//                .observeOn(Schedulers.io())
//                .flatMap(new Function<Object, ObservableSource<ProjectBean>>() {
//                    @Override
//                    public ObservableSource<ProjectBean> apply(Object o) throws Exception {
//                        // 请求数据
//                        return mApi.getProject();
//                    }
//                })
//                .flatMap(new Function<ProjectBean, ObservableSource<ProjectBean.Data>>() {
//                    @Override
//                    public ObservableSource<ProjectBean.Data> apply(ProjectBean projectBean) throws Throwable {
//                        return Observable.fromIterable(projectBean.getData());
//                    }
//                })
//                .flatMap(new Function<ProjectBean.Data, ObservableSource<ItemBean>>() {
//                    @Override
//                    public ObservableSource<ItemBean> apply(ProjectBean.Data data) throws Exception {
//                        return mApi.getProjectItem(1, data.getId());
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe((Consumer<R>) r -> {
//
//                });
    }

    private val mApi: WanAndroidApi
    private fun wan() {
        val project = mApi.project
        project //                .observeOn(AndroidSchedulers.mainThread())
            .subscribe { projectBean -> println(projectBean.toString()) }
    }

    private fun wanList() {
        mApi.getProjectItem(1, 294) //                .compose(rxud())
            //                .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<ItemBean> {
                override fun onSubscribe(d: Disposable) {
                    println("onSubscribe")
                }

                override fun onNext(itemBean: ItemBean) {
                    println(itemBean)
                }

                override fun onError(e: Throwable) {
                    println(e)
                }

                override fun onComplete() {
                    println("onComplete")
                }
            })
    }

    private fun pp() {
        RxJavaPlugins.setOnObservableAssembly { null }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val test = Test()
            test.wan()
            //        test.wanList();
        }

        fun <UD> rxud(): ObservableTransformer<UD, UD> {
            return ObservableTransformer { upstream ->
                upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

    init {
        mApi = HttpUtil.getRetrofit().create(WanAndroidApi::class.java)
    }
}