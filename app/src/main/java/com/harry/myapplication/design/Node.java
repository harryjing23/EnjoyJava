package com.harry.myapplication.design;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2022/3/24.
 *
 * @author harry
 */
public abstract class Node {

    private String addressName;
    private List<Node> mList;

    public Node(String addressName) {
        this.addressName = addressName;
        // 对List初始化
        mList = new ArrayList<>();
    }

    public void addNode(Node node) {
        mList.add(node);
    }

    public void removeNode(Node node) {
        mList.remove(node);
    }

    public List<Node> getNodes() {
        return mList;
    }

    public abstract boolean isLeaf();

}
