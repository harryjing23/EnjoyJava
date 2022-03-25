package com.harry.myapplication.design;

import java.util.Observable;

/**
 * Created on 2022/3/25.
 *
 * @author harry
 */
//public class MangoAttention extends Attention {
public class MangoAttention extends Observable {

    public void perform() {
        // ...到货的其他业务
        // 通知观察者
        setChanged();
        notifyObservers(null);
    }
}
