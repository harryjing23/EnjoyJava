package com.harry.myapplication.rxjava.theory;

import android.graphics.Bitmap;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;

/**
 * RxJava所有操作符都是：
 * 1. 从最外层代码来讲，是装饰模型，自上到下逐层封装Observable，再subscribe。（ObservableCreate->ObservableMap->subscribe）
 * 2. 订阅时，自下向上逐层封包裹Observer。（自定义Observer->MapObserver->发射器，封装为了把自己map的附加功能加进Observer）
 * 订阅subscribe到最上层事件Observable中，会有发射器，由发射器来调用下一层Observer的onNext、onComplete等方法，一直调用到最下层
 * 3. 发射事件时，再自上向下逐层拆包裹调用。（发射器->MapObserver->自定义Observer，拆包为了在onNext()中先执行map的附加功能再执行下一层Observer）
 * eg. 下面代码：订阅时 自定义Observer->MapObserver->CreateEmitter。发射时则倒序调用
 * 1.2.3.的整体流程为从上到下再到上再到下，加上洋葱模型（封/拆包裹）的数据结构，实现了卡片式的链式编程，可以随意增减卡片（事件）
 * <p>
 * Created on 2022/3/17.
 *
 * @author harry
 */

// map操作符的流程
public class _3_Map {

    private void test() {
        Observable
                // 返回的是ObservableCreate，还携带着参数ObservableOnSubscribe
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                        // 用发射器emitter来调用Observer中的方法。create操作符中发射器实际是CreateEmitter

                        // 会调用下一层包裹的onNext，也就是MapObserver.onNext()
                        emitter.onNext("bitmap");

                        // onComplete同理
                        emitter.onComplete();
                    }
                })
                // 就会走ObservableCreate.map。返回的是ObservableMap
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String s) throws Throwable {
                        return null;
                    }
                })
                // 就会走ObservableMap.subscribeActual
                .subscribe(
                        // 自定义Observer（终点）
                        new Observer<Bitmap>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull Bitmap bitmap) {

                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
    }


    // 下面是ObservableMap.subscribeActual()源码
//    public void subscribeActual(Observer<? super U> t) {
    // 将自定义Observer和Function包裹了一层，成了MapObserver。source指的都是上一层事件的Observable
    // source.subscribe实际上就调到ObservableCreate.subscribe(MapObserver)。其实ObservableCreate.create也将自定义Observer包裹了一层，成了发射器
//        source.subscribe(new ObservableMap.MapObserver<T, U>(t, function));
//    }
    // 也就是说，每一个RxJava操作符，都会将上一层的包裹再包裹一层，最里面是自定义Observer


    // 下面是MapObserver.onNext()源码
    // 除去健壮性代码，会调用mapper.apply，也就是map()的参数Function.apply()
//    public void onNext(T t) {
//        if (done) {
//            return;
//        }
//
//        if (sourceMode != NONE) {
//            downstream.onNext(null);
//            return;
//        }
//
//        U v;
//
//        try {
    // 实际走这里。Function.apply()将数据类型t转成了v
//            v = Objects.requireNonNull(mapper.apply(t), "The mapper function returned a null value.");
//        } catch (Throwable ex) {
//            fail(ex);
//            return;
//        }
    //最后走这里。调用下一层包裹的onNext，也就是自定义Observer（终点）的onNext
//        downstream.onNext(v);
//    }
}
