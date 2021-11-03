package com.harry.myapplication.design;

/**
 * Created on 2021/9/26.
 *
 * @author harry
 */
public class CheckedDecorator extends BagDecorator {
    public CheckedDecorator(Bag bag) {
        super(bag);
    }

    @Override
    public void pack() {
        mBag.pack();
        checked();
    }

    private void checked() {
        // 防伪的业务代码
    }
}
