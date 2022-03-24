package com.harry.myapplication.design;

/**
 * Created on 2022/3/24.
 *
 * @author harry
 */
public class TreeNode extends Node {

    public TreeNode(String addressName) {
        super(addressName);
    }

    public boolean isLeaf() {
        return getNodes().size() == 0;
    }
}
