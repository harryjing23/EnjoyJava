package com.harry.myapplication.rxjava.operator;

import android.view.View;

import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Created on 2022/3/23.
 *
 * @author harry
 */
public class RxShake {

    public static Observable<View> click(View view) {
        ObservableClick observableClick = new ObservableClick(view);

        return observableClick;
    }


    public static class ObservableClick extends Observable<View> {

        private final View mView;

        public ObservableClick(View view) {
            mView = view;
        }

        @Override
        protected void subscribeActual(@NonNull Observer<? super View> observer) {
            Emitter emitter = new Emitter(mView, observer);
            observer.onSubscribe(emitter);
            mView.setOnClickListener(emitter);
        }
    }

    public static class Emitter implements Disposable, View.OnClickListener {

        private final View mView;
        private final Observer<? super View> mObserver;
        private final AtomicBoolean isDisposed = new AtomicBoolean();

        public Emitter(View view, Observer<? super View> observer) {
            mView = view;
            mObserver = observer;
        }

        @Override
        public void onClick(View v) {
            if (!isDisposed()) {
                mObserver.onNext(v);
            }
        }

        @Override
        public void dispose() {
            if (isDisposed.compareAndSet(false, true)) {
                mView.setOnClickListener(null);
            }
        }

        @Override
        public boolean isDisposed() {
            return isDisposed.get();
        }
    }
}
