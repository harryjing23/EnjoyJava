package com.harry.compiler;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * 自定义注解处理器
 * 做法类似于Activity：
 * 1.继承AbstractProcessor
 * 2.在配置文件中注册该类（全类名配置在src/main/resources/META-INF/services/javax.annotation.processing.Processor）
 */

// 需要指定要处理的注解的全类名。不指定则不处理
@SupportedAnnotationTypes("com.harry.myapplication.annotation.Lance")
public class LanceProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // Message.printMessage和Log类似，需要指定打印级别
        // 在javac时就会打印出来。gradle中则是在task:compileDebugJavaWithJavac中
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "========================");

        return false;
    }
}