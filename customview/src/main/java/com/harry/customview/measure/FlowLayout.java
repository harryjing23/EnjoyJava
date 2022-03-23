package com.harry.customview.measure;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 流式布局，常用于容纳多个标签。且标签宽度和高度都是不同的
 * Created on 2022/3/10.
 *
 * @author harry
 */
public class FlowLayout extends ViewGroup {

    int horizontalSpace = 10;// 标签之间的横向间隔空间
    int verticalSpace = 20;// 行之间的垂直间隔空间

    int needWidth = 0;
    int needHeight = 0;

    private List<List<View>> layoutAllLines = new ArrayList<>();// 记录所有的标签，按行存储，用于layout
    List<Integer> layoutLineHeights = new ArrayList<>();// 记录每一行的行高，用于layout

    /*
    自定义View必须要实现前三个构造函数
     */

    // 代码new的时候调用
    public FlowLayout(Context context) {
        super(context);
    }

    // xml布局中调用。LayoutInflater会将xml解析成AttributeSet，LayoutInflater的inflate()将xml解析成AttributeSet，通过反射调用该构造
    // xml是一种序列化，用键值对保存了数据。（自定义序列化常用于IoT中的自定义协议中）
    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 自定义style主题
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 自定义属性用的（通常不实现第四个构造）
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void clearMeasureParams() {
//        layoutAllLines = new ArrayList<>();
//        layoutLineHeights = new ArrayList<>();

        // 每次onMeasure都new的话，会不断申请内存空间，产生内存碎片。当申请大的内存空间时，如bitmap，需要连续的内存空间，可能会失败也会产生oom

        layoutAllLines.clear();
        layoutLineHeights.clear();
    }


    /*
    第一步：测量。ViewGroup对自己和子View都要测量，先测量哪个要看算法。（ViewPager是先自己再子，其他大多数是先子再自己）
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // ### onMeasure可能会执行多次，由父View对它measure的次数决定。所以onMeasure中用到的变量，都要先重置
        clearMeasureParams();

        // 父View给的宽和高的参考值
        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);

        List<View> lineViews = new ArrayList<>();// 一行中的所有子View
        int lineWidthUsed = 0;// 记录一行已经使用了多宽
        int lineHeight = 0;// 一行的行高


        // 1.测量子View
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                break;
            }
            // ViewGroup若是match_parent，尺寸受父View影响；若是wrap_content，尺寸受子View影响。所以要递归计算

            // LayoutParams就对应着xml中指定的宽或高（可自定义LayoutParams，如ViewPager）
            // 有3种：match_parent=-1，wrap_content=-2和具体的值>0
            LayoutParams childLayoutParams = child.getLayoutParams();


            // MeasureSpec是View的内部类。是个参考值。int是32位，用高两位表示3种mode，低30位表示size
            // 1. UNSPECIFIED View大小不做限制，系统使用。2. EXACTLY 确切的大小。3. AL_MOST 不超过指定大小
            // getChildMeasureSpec可以得到子View的MeasureSpec的算法
            // 将LayoutParams转换成MeasureSpec
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight(), childLayoutParams.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom(), childLayoutParams.height);

            // 用measure测量子View。measure会触发子View的onMeasure，所以onMeasure的两个参数是父View在measure时传入的
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);

            // 测量完子View，就可以得到子View的尺寸。调用子View的getMeasuredWidth和getMeasuredHeight
            int measuredWidth = child.getMeasuredWidth();
            int measuredHeight = child.getMeasuredHeight();


            // 如果需要换行
            // TODO: 2022/3/11 其实还要考虑子View的margin
            if (lineWidthUsed + measuredWidth + horizontalSpace > selfWidth) {
                // 一旦换行，就保存用于layout的数据
                layoutAllLines.add(lineViews);
                layoutLineHeights.add(lineHeight);


                needWidth = Math.max(needWidth, lineWidthUsed + horizontalSpace);
                needHeight = needHeight + lineHeight + verticalSpace;

                // 记录清零
                lineViews = new ArrayList<>();
                lineWidthUsed = 0;
                lineHeight = 0;
            }

            // ViewGroup的尺寸计算。因为有多行标签，所以记录每行有哪些子View
            lineViews.add(child);
            lineWidthUsed = lineWidthUsed + measuredWidth + horizontalSpace;
            lineHeight = Math.max(lineHeight, measuredHeight);

            // 处理最后一行
            if (i == childCount - 1) {
                layoutAllLines.add(lineViews);
                layoutLineHeights.add(lineHeight);

                needWidth = Math.max(needWidth, lineWidthUsed + horizontalSpace);
                needHeight = needHeight + lineHeight + verticalSpace;
            }
        }


        // 2.测量完子View，该测量自己

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int realWidth = widthMode == MeasureSpec.EXACTLY ? selfWidth : needWidth;
        int realHeight = heightMode == MeasureSpec.EXACTLY ? selfHeight : needHeight;

        // 用setMeasuredDimension设置和保存自己的尺寸
        setMeasuredDimension(needWidth, needHeight);
    }

    /*
    第二步：布局。onLayout是ViewGroup必须override。
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lineCount = layoutAllLines.size();

        int curLeft = getPaddingLeft();
        int curTop = getPaddingTop();


        // TODO: 2022/3/11 layout时其实还要考虑gravity
        for (int i = 0; i < lineCount; i++) {
            List<View> lineViews = layoutAllLines.get(i);
            int lineHeight = layoutLineHeights.get(i);
            for (int j = 0; j < lineViews.size(); j++) {
                View view = lineViews.get(j);
                int left = curLeft;
                int top = curTop;

                // getMeasuredWidth是onMeasure测量后得出的，getWidth是onLayout布局后显示了得出的
                // 所以onLayout中要用getMeasuredWidth，onDraw中要用getWidth
                int right = left + view.getMeasuredWidth();
                int bottom = top + view.getMeasuredHeight();

                view.layout(left, top, right, bottom);

                curLeft = right + horizontalSpace;
            }
            curTop = curTop + lineHeight + verticalSpace;
            curLeft = getPaddingLeft();
        }


        // 用子View的layout，来决定子View在哪个位置

    }

    /*
    第三步：绘制。
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
