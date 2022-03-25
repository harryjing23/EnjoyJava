package com.harry.myapplication.design;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2022/3/25.
 *
 * @author harry
 */
public abstract class Attention {

    private List<Observer> mObservers = new ArrayList<>();

    // 通知所有观察者，执行观察者的update()
    public void notifyObservers() {
        for (Observer observer : mObservers) {
            observer.update();
        }
    }

    // 添加观察者
    public void addObserver(Observer observer) {
        mObservers.add(observer);
    }

    // 移除观察者
    public void removeObserver(Observer observer) {
        mObservers.remove(observer);
    }
}
