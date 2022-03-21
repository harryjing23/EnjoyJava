package com.harry.myapplication.rxjava.operator;

import android.os.Looper;
import android.view.View;

import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Created on 2022/3/21.
 *
 * @author harry
 */
public class ViewClickObservable extends Observable<Object> {

    private final View mView;
    private static final Object EVENT = new Object();

    public ViewClickObservable(View view) {
        mView = view;
    }

    @Override
    protected void subscribeActual(@NonNull Observer<? super Object> observer) {
        // 按照RxJava源码的惯例，先包装一层
        MyListener myListener = new MyListener(mView, observer);
        // 先调用onSubscribe
        observer.onSubscribe(myListener);

        mView.setOnClickListener(myListener);
    }


    // 实现Disposable是为了可中断
    static final class MyListener implements View.OnClickListener, Disposable {
        private View mView;
        private Observer<Object> mObserver;// 保存下一层事件
        private final AtomicBoolean isDisposable = new AtomicBoolean();

        public MyListener(View view, Observer<Object> observer) {
            mView = view;
            mObserver = observer;
        }

        @Override
        public void onClick(View v) {
            if (!isDisposed()) {
                mObserver.onNext(EVENT);
            }
        }

        // 实现dispose中断
        @Override
        public void dispose() {
            if (isDisposable.compareAndSet(false, true)) {// 如果没有被中断
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    mView.setOnClickListener(null);
                } else {
                    // 利用源码来切换到主线程
                    AndroidSchedulers.mainThread().scheduleDirect(new Runnable() {
                        @Override
                        public void run() {
                            mView.setOnClickListener(null);
                        }
                    });
                }
            }
        }

        @Override
        public boolean isDisposed() {
            return isDisposable.get();
        }
    }
}
