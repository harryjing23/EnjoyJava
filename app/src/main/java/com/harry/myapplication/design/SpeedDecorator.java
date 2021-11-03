package com.harry.myapplication.design;

/**
 * Created on 2021/9/26.
 *
 * @author harry
 */
public class SpeedDecorator extends BagDecorator {
    public SpeedDecorator(Bag bag) {
        super(bag);
    }

    @Override
    public void pack() {
        mBag.pack();
        speed();
    }

    private void speed() {
        // 加急的业务代码
    }
}
