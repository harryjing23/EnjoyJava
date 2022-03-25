package com.harry.myapplication.design;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2022/3/25.
 *
 * @author harry
 */
public class VisitClient {

    private List<Fruit> mList = new ArrayList<>();
    private Visit mVisit = new Visit();

    // 添加要甩卖的水果
    {
        mList.add(new Apple());
        mList.add(new Orange());
        mList.add(new Banana());
    }

    // 清仓甩卖
    private void clearOut() {
        for (Fruit fruit : mList) {
            fruit.accept(mVisit);
        }
    }
}
