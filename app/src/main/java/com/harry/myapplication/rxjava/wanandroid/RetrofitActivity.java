package com.harry.myapplication.rxjava.wanandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.harry.myapplication.R;
import com.harry.myapplication.rxjava.DownloadActivity;
import com.jakewharton.rxbinding4.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Unit;

public class RetrofitActivity extends AppCompatActivity {
    private static final String TAG = "RetrofitActivity";

    private WanAndroidApi mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);


        mApi = HttpUtil.getRetrofit().create(WanAndroidApi.class);
    }


    /**
     * OkHttp处理请求，RxJava处理响应。用Retrofit框架来封装、管理。
     */

    public void getProject() {

        mApi.getProject()
                // Retrofit不会自己切换到子线程，所以需要把请求指定为子线程
                .subscribeOn(Schedulers.io())// Schedulers.computation()也是子线程，用于处理大量计算
                .observeOn(AndroidSchedulers.mainThread())
                // Consumer是Observer的简化版
                .subscribe(new Consumer<ProjectBean>() {
                    @Override
                    public void accept(ProjectBean projectBean) throws Throwable {
                        Log.d(TAG, "accept: " + projectBean);
                    }
                });
    }

    public void getItem() {
        mApi.getProjectItem(1, 294)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // 用lambda代替Consumer
                .subscribe(itemBean -> {
                    Log.d(TAG, "getItem: " + itemBean);
                });
    }


    // 防抖。要引入rxbinding包
    @SuppressLint("CheckResult")// 去掉代码报黄
    public void antiShake(View view) {
        RxView.clicks(view)// 点击的防抖
                .throttleFirst(2000, TimeUnit.MILLISECONDS)// 2s内只响应一次
                .subscribe(
                        // 这里的泛型，就是上面传的防抖对象
                        new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {
                                // ### 网络请求的嵌套的反例
                                mApi.getProject()// 请求主数据
                                        .compose(DownloadActivity.rxud())
                                        .subscribe(new Consumer<ProjectBean>() {
                                            @Override
                                            public void accept(ProjectBean projectBean) throws Throwable {
                                                for (ProjectBean.Data data : projectBean.getData()) {
                                                    // 请求item数据。嵌套，同外层一样的逻辑
                                                    mApi.getProjectItem(1, data.getId())
                                                            .compose(DownloadActivity.rxud())
                                                            .subscribe(new Consumer<ItemBean>() {
                                                                @Override
                                                                public void accept(ItemBean itemBean) throws Throwable {
                                                                    Log.d(TAG, "accept: " + itemBean);
                                                                }
                                                            });
                                                }
                                            }
                                        });
                            }
                        });
    }

    // 用flatMap解决网络请求的嵌套乱的问题
    // doOnNext的使用
    @SuppressLint("CheckResult")
    public void flatMapNetwork(View view) {

        RxView.clicks(view)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                // 给下面所有事件分配子线程
                .observeOn(Schedulers.io())
                .flatMap(
                        // ObservableSource<T>的泛型默认是?，可以修改成要返回的类型
                        new Function<Unit, ObservableSource<ProjectBean>>() {
                            @Override
                            public ObservableSource<ProjectBean> apply(Unit unit) throws Throwable {
                                return mApi.getProject();// 请求主数据。getProject返回类型是Observable，用flatMap刚刚好
                            }
                        })
                .observeOn(AndroidSchedulers.mainThread())
                // doOnNext类似于subscribe，但是doOnNext会继续返回Observable，便于继续链式调用，subscribe返回Disposable则链式结束
                // doOnNext把上面的Observable中的数据扒出来，再把数据装进Observable向下传
                .doOnNext(new Consumer<ProjectBean>() {
                    @Override
                    public void accept(ProjectBean projectBean) throws Throwable {
                        // do something eg.在主线程更新UI
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<ProjectBean, ObservableSource<ProjectBean.Data>>() {
                    @Override
                    public ObservableSource<ProjectBean.Data> apply(ProjectBean projectBean) throws Throwable {
                        // ObservableSource是Observable实现的接口（父类）
                        // Observable.fromIterable的参数一个迭代器，转变成向下面事件发射多次。而data正好是个List
                        return Observable.fromIterable(projectBean.getData());
                    }
                })
                .flatMap(new Function<ProjectBean.Data, ObservableSource<ItemBean>>() {
                    @Override
                    public ObservableSource<ItemBean> apply(ProjectBean.Data data) throws Throwable {
                        return mApi.getProjectItem(1, data.getId());
                    }
                })
                // 给下面所有事件分配主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ItemBean>() {
                    @Override
                    public void accept(ItemBean itemBean) throws Throwable {
                        Log.d(TAG, "accept: " + itemBean);
                    }
                });

    }
}