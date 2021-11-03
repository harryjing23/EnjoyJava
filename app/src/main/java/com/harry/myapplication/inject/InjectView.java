package com.harry.myapplication.inject;

import androidx.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2021/7/1.
 *
 * @author harry
 */

@Target(ElementType.FIELD)
// 用RUNTIME是因为要在运行时利用反射使用它
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectView {

    // @IdRes语法检查。注解中的元素也可以使用注解
    @IdRes int idValue();
}
