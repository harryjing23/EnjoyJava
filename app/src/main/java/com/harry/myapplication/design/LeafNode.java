package com.harry.myapplication.design;

/**
 * Created on 2022/3/24.
 *
 * @author harry
 */
public class LeafNode extends Node {

    public LeafNode(String addressName) {
        super(addressName);
    }

    @Override
    public boolean isLeaf() {
        return true;
    }
}
