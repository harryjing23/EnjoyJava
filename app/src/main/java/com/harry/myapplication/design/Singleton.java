package com.harry.myapplication.design;

/**
 * Created on 2021/9/18.
 *
 * @author harry
 */
public class Singleton {
    // volatile在这里起到防止重排序的作用
    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                // 第一次线程已经创建了实例，排队等锁的线程进入后要再次判空
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
