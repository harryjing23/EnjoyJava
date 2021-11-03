package com.harry.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.harry.myapplication.inject.InjectUtils;
import com.harry.myapplication.inject.InjectView;

public class MainActivity extends AppCompatActivity {

    int i;
    int j;
    // 因为注解的元素有语法检查，直接传int报错
//    @InjectView(1)
    @InjectView(idValue = R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InjectUtils.injectView(this);
        tv.setText("inject view test");

    }
}