package com.harry.myapplication.rxjava.thread;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.harry.myapplication.R;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ThreadActivity extends AppCompatActivity {
    private static final String TAG = "ThreadActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
    }

    private void test() {
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {

                    }
                })
                // 第二步。会触发里面的线程池
                .subscribeOn(
                        // 第一步。源码new了IoScheduler，经过多层包装，最终走到对线程池的管控
                        Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    // --------------------Schedulers.io()的hook相关----------------------

    // 下面是Schedulers.io()源码。非常熟悉的形式，也是全局hook
//    public static Scheduler io() {
//        return RxJavaPlugins.onIoScheduler(IO);
//    }

    // 下面是RxJavaPlugins.onIoScheduler()源码。由此可见，onIoHandler不为null时就有hook
//    public static Scheduler onIoScheduler(@NonNull Scheduler defaultScheduler) {
//        Function<? super Scheduler, ? extends Scheduler> f = onIoHandler;
//        if (f == null) {
//            return defaultScheduler;
//        }
//        return apply(f, defaultScheduler);
//    }

    // 下面是RxJavaPlugins.setIoSchedulerHandler()源码。这里对onIoHandler进行了赋值
//    public static void setIoSchedulerHandler(@Nullable Function<? super Scheduler, ? extends Scheduler> handler) {
//        if (lockdown) {
//            throw new IllegalStateException("Plugins can't be changed anymore");
//        }
//        onIoHandler = handler;
//    }

    // 设置IoScheduler的hook
    private void hookScheduler() {
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Throwable {
                // 记录了每次调用Schedulers.io()的日志
                Log.d(TAG, "apply: " + scheduler);
                return scheduler;
            }
        });
    }

    // --------------------Schedulers的IO--------------------------
    // 由Schedulers.io()源码可知，IO就是实际的Scheduler
    // 下面是Schedulers的静态代码块的源码。对Schedulers的各种策略进行初始化，RxJavaPlugins的init方法是hook用，实际起作用的是里面的new
    // 通过IOTask.get()->IoScheduler->IoScheduler.start()->CachedWorkerPool就创建了线程池Executors.newScheduledThreadPool
//    static {
//        SINGLE = RxJavaPlugins.initSingleScheduler(new Schedulers.SingleTask());
//
//        COMPUTATION = RxJavaPlugins.initComputationScheduler(new Schedulers.ComputationTask());
//
//        IO = RxJavaPlugins.initIoScheduler(new Schedulers.IOTask());
//
//        TRAMPOLINE = TrampolineScheduler.instance();
//
//        NEW_THREAD = RxJavaPlugins.initNewThreadScheduler(new Schedulers.NewThreadTask());
//    }

}