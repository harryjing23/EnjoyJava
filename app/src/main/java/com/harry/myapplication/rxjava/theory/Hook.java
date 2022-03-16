package com.harry.myapplication.rxjava.theory;

import android.util.Log;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/**
 * Created on 2022/3/16.
 *
 * @author harry
 */
public class Hook {
    private static final String TAG = "Test";

    private void test() {

        // RxJava使用举例
        Observable
                // 每个操作符，都返回Observable的子类（eg. map就返回ObservableMap）
                .create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {

                    }
                })
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object o) throws Throwable {
                        return null;
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Throwable {

                    }
                });

    }


    // 每个操作符都用RxJavaPlugins.onAssembly()包装一层，hook作全局的监听
    // 下面是map()的源码
//    public final <@NonNull R> Observable<R> map(@NonNull Function<? super T, ? extends R> mapper) {
//        Objects.requireNonNull(mapper, "mapper is null");
//        return RxJavaPlugins.onAssembly(new ObservableMap<>(this, mapper));
//    }

    // 下面是RxJavaPlugins.onAssembly()源码
    // Function就是hook，调用每个操作符都用RxJavaPlugins.setOnObservableAssembly()即可添加Function
//    public static <@NonNull T> Observable<T> onAssembly(@NonNull Observable<T> source) {
//        Function<? super Observable, ? extends Observable> f = onObservableAssembly;
//        if (f != null) {
//            return apply(f, source);
//        }
//        return source;
//    }

    private void addHook() {
        RxJavaPlugins.setOnObservableAssembly(new Function<Observable, Observable>() {
            @Override
            public Observable apply(Observable observable) throws Throwable {

                // 全局监听业务。hook就是把代码钩出来，先调用我的，再调用原本的。eg. 加log
                Log.d(TAG, observable + "调用了一次RxJava操作符");


                // 这里不能返回null。由上面操作符的源码可知，这个返回值是操作符最后的返回值
//                return null;
                return observable;
            }
        });
    }
}
