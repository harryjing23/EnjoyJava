package com.harry.myapplication.design;

/**
 * Created on 2021/9/17.
 *
 * @author harry
 */
public class OrangeFactory implements FruitFactory {
    @Override
    public Fruit getFruit() {
        return new Orange();
    }

    @Override
    public Bag getBag() {
        return new OrangeBag();
    }
}
