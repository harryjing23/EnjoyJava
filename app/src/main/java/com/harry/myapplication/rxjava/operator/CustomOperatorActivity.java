package com.harry.myapplication.rxjava.operator;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.harry.myapplication.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;

public class CustomOperatorActivity extends AppCompatActivity {
    private static final String TAG = "CustomOperatorActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_operator);

        Button button = findViewById(R.id.btn_show);
        // 自定义防抖
        RxView.clicks(button)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Throwable {
                        Log.d(TAG, "accept: ");
                    }
                });
    }

    private void test() {
        String[] str = {"a", "b"};
        // 遍历数组
        for (String s : str) {
            Log.d(TAG, "test: " + s);
        }

        // 也可以用RxJava遍历数组，但不推荐，性能不好
        Observable.fromArray(str).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.d(TAG, "accept: " + s);
            }
        });
    }
}