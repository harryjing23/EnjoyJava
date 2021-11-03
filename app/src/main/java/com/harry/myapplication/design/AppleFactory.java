package com.harry.myapplication.design;

/**
 * Created on 2021/9/17.
 *
 * @author harry
 */
public class AppleFactory implements FruitFactory {
    @Override
    public Fruit getFruit() {
        return new Apple();
    }

    @Override
    public Bag getBag() {
        return new AppleBag();
    }
}
