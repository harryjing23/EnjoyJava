package com.harry.myapplication.rxjava.theory;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * RxJava的创建Observable、创建Observer、订阅的流程
 * <p>
 * RxJava的观察者模式并不是标准的，标准的是一个Observable多个Observer，RxJava是多个Observable一个Observer
 * RxJava更像是发布订阅模式，中间多了一层发射器，Observable->发射器emitter->Observer。耦合度更低
 * <p>
 * Created on 2022/3/16.
 *
 * @author harry
 */
public class Observe {

    private void test() {
        Observable
                // 研究Observable的创建流程
                .create(
                        // create()创建Observable时，传入了ObservableOnSubscribe作参数
                        new ObservableOnSubscribe<String>() {
                            @Override
                            // subscribeActual()就会带着发射器emitter，走到该方法
                            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                                // 发射器的onNext就会调用创建发射器时传入的自定义observer的onNext()
                                emitter.onNext("abc");
                            }
                        })
                // 研究subscribe订阅流程
                .subscribe(
                        // 自定义Observer作参数
                        new Observer<String>() {
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

    // 下面是Observable的subscribe()源码
    // 除了健壮性检查，会走到subscribeActual()，而subscribeActual()是Observable的抽象方法
//    public final void subscribe(@NonNull Observer<? super T> observer) {
//        Objects.requireNonNull(observer, "observer is null");
//        try {
//            observer = RxJavaPlugins.onSubscribe(this, observer);
//
//            Objects.requireNonNull(observer, "The RxJavaPlugins.onSubscribe hook returned a null Observer. Please change the handler provided to RxJavaPlugins.setOnObservableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
//
//            subscribeActual(observer);
//        } catch (NullPointerException e) { // NOPMD
//            throw e;
//        } catch (Throwable e) {
//            Exceptions.throwIfFatal(e);
//            // can't call onError because no way to know if a Disposable has been set or not
//            // can't call onSubscribe because the call might have set a Subscription already
//            RxJavaPlugins.onError(e);
//
//            NullPointerException npe = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
//            npe.initCause(e);
//            throw npe;
//        }
//    }

    // 下面是ObservableCreate（Observable的子类）的subscribeActual()源码
    // 参数就是subscribe时传入的自定义observer
//    protected void subscribeActual(Observer<? super T> observer) {
    // new了发射器，参数就是自定义observer
//        ObservableCreate.CreateEmitter<T> parent = new ObservableCreate.CreateEmitter<>(observer);
    // 先调用了onSubscribe回调
//        observer.onSubscribe(parent);
//
//        try {
    // source就是创建Observable传入的ObservableOnSubscribe，调用source.subscribe()，发射器作参数
//            source.subscribe(parent);
//        } catch (Throwable ex) {
//            Exceptions.throwIfFatal(ex);
//            parent.onError(ex);
//        }
//    }
}
