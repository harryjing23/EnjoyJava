package com.harry.customview.measure;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.harry.customview.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

/**
 * 自定义View
 * 布局：onLayout onMeasure layout(ViewGroup)
 * 显示：onDraw
 * 交互：onTouchEvent
 * <p>
 * 执行流程：（搞清楚先后顺序，避免取值时机错误）
 * 1.构造函数（View初始化 2.onMeasure（测量View大小 3.onSizeChanged（确定View大小
 * 4.onLayout（确定子View布局 5.onDraw（绘制内容 6.视图状态改变会invalidate()并重新onDraw
 * <p>
 * 自定义ViewGroup最重要是实现onMeasure和onLayout
 * 自定义View最重要是实现onMeasure和onDraw
 * <p>
 * ViewPager设置wrap_content无效。因为ViewPager在onMeasure中，先setMeasuredDimension设置了自己的宽高，没有根据子View的尺寸
 * 网上博客也是错误的。因为在测量子View尺寸是都没有用到子View的LayoutParams，没有真正去测量子View的尺寸
 */

// TODO: 2022/3/11 作业：1.用Kotlin手写FlowLayout 2.学习FlexboxLayout 3.阅读FrameLayout和LinearLayout源码