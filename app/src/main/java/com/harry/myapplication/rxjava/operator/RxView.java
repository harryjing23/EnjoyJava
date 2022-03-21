package com.harry.myapplication.rxjava.operator;

import android.view.View;

import io.reactivex.rxjava3.core.Observable;

/**
 * Created on 2022/3/21.
 *
 * @author harry
 */
public class RxView {
    private static final String TAG = "RxView";


    // 自定义操作符，即函数
    public static Observable<Object> clicks(View view) {
        return new ViewClickObservable(view);
    }


}
