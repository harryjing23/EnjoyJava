package com.harry.myapplication.inject;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created on 2021/6/30.
 *
 * @author harry
 */
public class InjectUtils {

    // 实现一个类似于ButterKnife的注入方法
    public static void injectView(Activity activity) {

        // 反射是基于Class的，所以要先得到Class对象
        Class<? extends Activity> cls = activity.getClass();
        // 获取成员：getFields()和getDeclaredFields()，getMethods()和getDeclaredMethods()
        // ### 前者获取自己+父类的所有public成员，后者获取自己的所有成员
        // 获取父类的private，可以先getSuperClass()，再获取

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            // ### isAnnotationPresent()该字段是否有InjectView注解
            if (field.isAnnotationPresent(InjectView.class)) {
                // 获取注解的实例，才能获取其中的元素
                InjectView annotation = field.getAnnotation(InjectView.class);
                // 获取注解的元素值
                int id = annotation.idValue();

                View view = activity.findViewById(id);
                // 使private成员可以访问
                field.setAccessible(true);
                try {
                    // 给该字段赋值。参1代表要赋值的对象，参2代表要赋值的字段值
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
