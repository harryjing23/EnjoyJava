package com.harry.myapplication.design;

/**
 * Created on 2022/3/25.
 *
 * @author harry
 */
public class Banana implements Fruit {
    int price = 20;

    @Override
    public void accept(Visit visit) {
        visit.sell(this);
    }
}
