package com.harry.myapplication.design;

/**
 * Created on 2021/9/26.
 *
 * @author harry
 */
public class ReinforceDecorator extends BagDecorator {
    public ReinforceDecorator(Bag bag) {
        super(bag);
    }

    @Override
    public void pack() {
        mBag.pack();
        reinforce();
    }

    private void reinforce() {
        // 加固的业务代码
    }
}
