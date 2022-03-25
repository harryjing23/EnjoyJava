package com.harry.myapplication.design;

/**
 * Created on 2021/9/17.
 *
 * @author harry
 */
public class Orange implements Fruit {
    int price = 80;

    @Override
    public void accept(Visit visit) {
        visit.sell(this);
    }
}
