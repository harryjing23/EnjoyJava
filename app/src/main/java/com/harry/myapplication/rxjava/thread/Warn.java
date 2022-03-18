package com.harry.myapplication.rxjava.thread;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * Created on 2022/3/18.
 *
 * @author harry
 */
public class Warn {

    private void check() {
        // just就是对create的封装，自动发射，自动调用onNext和onComplete
        Observable.just("test")
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Throwable {

                    }
                });
        // 上面的subscribe报黄，因为没有接收subscribe的返回值Disposable。建议拿到Disposable在onDestroy中作中断控制
    }
}
