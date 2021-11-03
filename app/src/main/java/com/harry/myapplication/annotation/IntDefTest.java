package com.harry.myapplication.annotation;

import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;

import com.harry.myapplication.R;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2021/6/30.
 *
 * @author harry
 */

// 注解还可以用于语法检查，如@DrawableRes。这个检查是IDE的工作，即使报错也可以直接javac编译
public class IntDefTest {

    private static WeekDay mCurrentDay;
    @WekDay
    private static int mCurrentIntDay;

    // 枚举类的每个字段其实都是一个对象，一个空对象（对象头）占用12个字节，很占内存
    // 所以在Android中用常量代替枚举类
    enum WeekDay {
        SUNDAY, MONDAY
    }

    public static final int SUNDAY = 0;
    public static final int MONDAY = 1;

    // 注解多个参数用{}括起来
    // 用@IntDef指明可以使用的值
    @IntDef({SUNDAY, MONDAY})
    @Target({ElementType.PARAMETER, ElementType.FIELD})
    @Retention(RetentionPolicy.SOURCE)
            // 该注解用于检查语法
    @interface WekDay {
    }

    public static void setCurrentDay(WeekDay weekDay) {
        mCurrentDay = weekDay;
    }

    public static void setCurrentDay(@WekDay int weekDay) {
        mCurrentIntDay = weekDay;
    }

    public static void main(String[] args) {
        // 没有加@DrawableRes时，可以直接传int参数
//        setDrawable(123);
        setDrawable(R.drawable.ic_launcher_background);

        setCurrentDay(WeekDay.SUNDAY);

        setCurrentDay(SUNDAY);
    }

    public static void setDrawable(@DrawableRes int id) {
    }

}
