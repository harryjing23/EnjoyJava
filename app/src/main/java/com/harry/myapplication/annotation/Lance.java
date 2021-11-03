package com.harry.myapplication.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2021/6/25.
 *
 * @author harry
 */

// 元注解：注解上的注解

// @Target说明该注解写在哪里
// TYPE类，FIELD字段，METHOD方法，CONSTRUCTOR构造方法，PARAMETER方法参数，ANNOTATION_TYPE注解（即元注解）
@Target(ElementType.TYPE)
// @Retention保留时。保留该注解到哪个级别。SOURCE只保留在源代码中，CLASS保留在class文件，RUNTIME利用反射保留到运行时
// 包含关系：RUNTIME包括CLASS和SOURCE，CLASS包括SOURCE
// 1. SOURCE常用于APT、语法检查
// 2. CLASS常用于字节码增强/插桩（在.class字节码中写代码，可用于热修复）
// 3. RUNTIME可以利用反射获取注解中的信息
@Retention(RetentionPolicy.SOURCE)
// 使用@interface创建注解
public @interface Lance {
    // 注解中的元素，类似于接口中的方法。还可以用default指定默认值
    int id();

    // 只有一个value元素时，可以在使用时直接传值。其他元素则需要用(key = value)的形式
    String value() default "xxx";

}

// APT的@Retention保留时就是SOURCE。APT注解处理器：annotation processing tool

// 自定义注解处理器
// 1. 需要新建Java Library，因为Android项目的JDK中不包含Annotation Processor相关代码
// 2. 在build.gradle中用annotationProcessor project()配置该library
// 2也可以直接编译：javac -classpath library下build/libs的jar包 要编译的类 注解类
// ps: javac是编译工具，javap则是反编译工具（.class->.java）