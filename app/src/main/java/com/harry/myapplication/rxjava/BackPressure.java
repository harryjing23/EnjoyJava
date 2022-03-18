package com.harry.myapplication.rxjava;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created on 2022/3/18.
 *
 * @author harry
 */
public class BackPressure {

    /**
     * BackPressure即背压、回压。上游的生产速度大于下游的消费速度时，肯定会有缓冲区buffer，当buffer达到上限时，就会产生回压
     * （源自工程管道的概念，此情况下游就会对上游产生逆向压力）
     * 背压的处理方式有两种：1. buffer不设上限，可能会导致OOM 2. 对事件进行丢弃（丢弃的策略有很多）
     * btw：背压一定是出现在异步情况下，因为同步情况下生产和消费是同一线程的、依次执行的。
     */
    // RxJava对背压的处理，用Flowable
    private void tt() {
        Flowable
                .create(new FlowableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull FlowableEmitter<String> emitter) throws Throwable {

                    }
                    // 提供了几种对背压处理的方式。详情看文档
                }, BackpressureStrategy.MISSING)
                // 包含backpressure的方法有很多，作用与设置线程池的缓冲队列类似
                .onBackpressureBuffer(100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {

                    }
                });
    }
}
