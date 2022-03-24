package com.harry.myapplication.design;

/**
 * Created on 2022/3/24.
 *
 * @author harry
 */
public interface SendService {

    void send();

    // 实现类
    class SendServiceImpl implements SendService {
        // ...其他字段和方法

        @Override
        public void send() {
        }
    }
}
