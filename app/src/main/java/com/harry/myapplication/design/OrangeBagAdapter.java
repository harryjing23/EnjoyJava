package com.harry.myapplication.design;

/**
 * Created on 2021/9/25.
 *
 * @author harry
 */
public class OrangeBagAdapter extends OrangeBag {
    private AppleBag mAppleBag;

    public OrangeBagAdapter(AppleBag appleBag) {
        mAppleBag = appleBag;
    }

    @Override
    public void pack() {
        mAppleBag.pack();
    }
}
